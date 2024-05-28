package br.com.eleicoes.vote.di

import androidx.room.Room
import br.com.eleicoes.vote.authentication.FirebaseAuthRepository
import br.com.eleicoes.vote.repositories.TasksRepository
import br.com.eleicoes.vote.repositories.UsersRepository
import br.com.eleicoes.vote.ui.viewmodels.SignInViewModel
import br.com.eleicoes.vote.ui.viewmodels.SignUpViewModel
import br.com.eleicoes.vote.ui.viewmodels.TaskFormViewModel
import br.com.eleicoes.vote.ui.viewmodels.TasksListViewModel
import br.com.eleicoes.vote.database.MinhasTarefasDatabase
import br.com.eleicoes.vote.ui.viewmodels.AppViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::TaskFormViewModel)
    viewModelOf(::TasksListViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::AppViewModel)
}

val storageModule = module {
    singleOf(::TasksRepository)
    singleOf(::UsersRepository)
    singleOf(::FirebaseAuthRepository)
    single {
        Room.databaseBuilder(
            androidContext(),
            MinhasTarefasDatabase::class.java, "minhas-tarefas.db"
        ).build()
    }
    single {
        get<MinhasTarefasDatabase>().taskDao()
    }
}

val firebaseModule = module {
    single {
        Firebase.auth
    }
}


