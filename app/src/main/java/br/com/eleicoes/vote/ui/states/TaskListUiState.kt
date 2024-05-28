package br.com.eleicoes.vote.ui.states

import br.com.eleicoes.vote.models.Task

data class TasksListUiState(
    val tasks: List<Task> = emptyList(),
    val onTaskDoneChange: (Task) -> Unit = {},
    val user: String? = null
)
