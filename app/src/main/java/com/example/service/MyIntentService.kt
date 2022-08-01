package com.example.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder

abstract class MyIntentService(name: String): Service() {

    private val serviceThread = HandlerThread(name)

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        serviceThread.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceThread.looper.quit()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Handler(serviceThread.looper).post {
            onHandleIntent(intent)
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    abstract fun onHandleIntent(intent: Intent?)

}