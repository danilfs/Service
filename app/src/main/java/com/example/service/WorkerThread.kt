package com.example.service

import android.os.Handler
import android.os.Looper
import kotlin.concurrent.thread

typealias Task = () -> Unit

class WorkerThread(private val name: String) {

    private var isStarted = false
    private val taskQueue = mutableListOf<Task>()

    fun post(task: Task) {
        taskQueue.add(task)
    }

    fun post(delay: Long, task: Task) {
        Handler(Looper.getMainLooper())
            .postDelayed({ post(task) }, delay)
    }

    fun start() {
        isStarted = true
        thread(name = name) {
            while (isStarted) {
                val task = taskQueue.removeFirstOrNull()
                task?.invoke()
            }
        }
    }

    fun stop() {
        isStarted = false
    }
}