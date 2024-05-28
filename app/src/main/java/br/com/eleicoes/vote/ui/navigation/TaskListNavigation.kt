package br.com.eleicoes.vote.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.eleicoes.vote.models.Task
import br.com.eleicoes.vote.ui.screens.TasksListScreen
import br.com.eleicoes.vote.ui.states.TasksListUiState
import br.com.eleicoes.vote.ui.viewmodels.TasksListViewModel
import org.koin.androidx.compose.koinViewModel

const val tasksListRoute = "tasksList"

fun NavGraphBuilder.tasksListScreen(
    onNavigateToNewTaskForm: () -> Unit,
    onNavigateToEditTaskForm: (Task) -> Unit,
) {
    composable(tasksListRoute) {
        val viewModel = koinViewModel<TasksListViewModel>()
        val uiState by viewModel.uiState
            .collectAsState(TasksListUiState())
        TasksListScreen(
            uiState = uiState,
            onNewTaskClick = onNavigateToNewTaskForm,
            onTaskClick = onNavigateToEditTaskForm,
            onExitToAppClick = {
                viewModel.signOut()
            }
        )
    }
}

fun NavHostController.navigateToTasksList() {
    navigate(tasksListRoute)
}