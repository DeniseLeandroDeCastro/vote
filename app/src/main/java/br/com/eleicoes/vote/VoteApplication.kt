package br.com.eleicoes.vote

import android.app.Application
import br.com.eleicoes.vote.di.appModule
import br.com.eleicoes.vote.di.firebaseModule
import br.com.eleicoes.vote.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class VoteApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@VoteApplication)
            modules(
                appModule,
                storageModule,
                firebaseModule
            )
        }
    }
}