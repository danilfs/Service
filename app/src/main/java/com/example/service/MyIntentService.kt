package com.example.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import androidx.annotation.WorkerThread

abstract class MyIntentService(name: String): Service() {

    private val serviceThread = WorkerThread(name)

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        serviceThread.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceThread.stop()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        serviceThread.post {
            onHandleIntent(intent)
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    abstract fun onHandleIntent(intent: Intent?)

}