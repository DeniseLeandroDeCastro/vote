package br.com.eleicoes.vote

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import br.com.eleicoes.vote.ui.navigation.authGraph
import br.com.eleicoes.vote.ui.navigation.authGraphRoute
import br.com.eleicoes.vote.ui.navigation.homeGraph
import br.com.eleicoes.vote.ui.navigation.navigateToAuthGraph
import br.com.eleicoes.vote.ui.navigation.navigateToEditTaskForm
import br.com.eleicoes.vote.ui.navigation.navigateToHomeGraph
import br.com.eleicoes.vote.ui.navigation.navigateToNewTaskForm
import br.com.eleicoes.vote.ui.navigation.navigateToSignIn
import br.com.eleicoes.vote.ui.navigation.navigateToSignUp
import br.com.eleicoes.vote.ui.theme.VoteTheme
import br.com.eleicoes.vote.ui.viewmodels.AppState
import br.com.eleicoes.vote.ui.viewmodels.AppViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VoteTheme {
                val navController = rememberNavController()
                val appViewModel = koinViewModel<AppViewModel>()
                val appState by appViewModel.state.collectAsState(initial = AppState())
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentParentRoute = currentBackStack?.destination?.parent?.route

                LaunchedEffect(appState) {
                    if(appState.isInitLoading) {
                        return@LaunchedEffect
                    }
                    appState.user?.let {
                        navController.navigateToHomeGraph(navOptions = navOptions {
                            currentParentRoute?.let { parentRoute ->
                                popUpTo(parentRoute) {
                                    inclusive = true
                                }
                            } ?: popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        })
                    } ?: navController.navigateToAuthGraph(navOptions {
                        currentParentRoute?.let {parentRoute ->
                            popUpTo(parentRoute) {
                                inclusive = true
                            }
                        }
                    })
                }
                NavHost(
                    navController = navController,
                    startDestination = "splashscreen"
                ) {
                    composable("splashscreen") {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                    authGraph(
                        onNavigateToSignIn = {
                            navController.navigateToSignIn(it)
                        },
                        onNavigateToSignUp = {
                            navController.navigateToSignUp()
                        }
                    )
                    homeGraph(
                        onNavigateToNewTaskForm = {
                            navController.navigateToNewTaskForm()
                        }, onNavigateToEditTaskForm = { task ->
                            navController.navigateToEditTaskForm(task)
                        }, onPopBackStack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}