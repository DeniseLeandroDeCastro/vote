package br.com.eleicoes.vote.models

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val isDone: Boolean = false
)
