package br.com.eleicoes.vote.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.eleicoes.vote.database.dao.TaskDao
import br.com.eleicoes.vote.database.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class MinhasTarefasDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}