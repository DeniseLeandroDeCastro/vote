package br.com.eleicoes.vote

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
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

                LaunchedEffect(appState) {
                    if(appState.isInitLoading) {
                        return@LaunchedEffect
                    }
                    appState.user?.let {
                        navController.navigateToHomeGraph()
                    } ?: navController.navigateToAuthGraph()
                }
                NavHost(
                    navController = navController,
                    startDestination = authGraphRoute
                ) {

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