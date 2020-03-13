package com.example.aulamvvm.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

//Estudar conceito de thread
class AppExecutors(val mainThreadExecutor: Executor = MainThreadExecutor(), val roomIOThreadExecutor: Executor = RoomIOThreadExecutor())

//Cria uma thread para executar qualquer coisa que precisa ser feito em background
private class RoomIOThreadExecutor: Executor {
    private val executor = Executors.newSingleThreadExecutor()

    override fun execute(command: Runnable) {
        executor.execute(command)
    }
}

//Handler: Classe que gerencia os processos
private class MainThreadExecutor: Executor {
    private val handler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        handler.post(command)
    }
}