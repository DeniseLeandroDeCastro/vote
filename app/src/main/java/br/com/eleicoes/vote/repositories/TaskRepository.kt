package br.com.eleicoes.vote.repositories

import br.com.eleicoes.vote.database.dao.TaskDao
import br.com.eleicoes.vote.database.entities.TaskEntity
import br.com.eleicoes.vote.models.Task
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class TasksRepository(
    private val dao: TaskDao
) {

    val tasks get() = dao.findAll()

    suspend fun save(task: Task) = withContext(IO) {
        dao.save(task.toTaskEntity())
    }

    suspend fun toggleIsDone(task: Task) = withContext(IO) {
        val entity = task.copy(isDone = !task.isDone)
            .toTaskEntity()
        dao.save(entity)
    }

    suspend fun delete(id: String) {
        dao.delete(
            TaskEntity(id = id, title = "")
        )
    }

    fun findById(id: String) = dao.findById(id)
}

fun Task.toTaskEntity() = TaskEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    isDone = this.isDone
)

fun TaskEntity.toTask() = Task(
    id = this.id,
    title = this.title,
    description = this.description,
    isDone = this.isDone
)