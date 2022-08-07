package com.example.service

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast

class MyService : MyIntentService("MyService") {

    override fun onHandleIntent(intent: Intent?) {
        val msg = "Work on thread ${Thread.currentThread().name}"
        Log.d("MyService", msg)

        Thread.sleep(2_000)

        Handler(Looper.getMainLooper()).post {
            Toast.makeText(this.applicationContext, msg, Toast.LENGTH_SHORT).show()
        }
    }

}