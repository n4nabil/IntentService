package com.sample.nabil.intentservice

import android.app.IntentService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.util.*


class MainActivity : AppCompatActivity() {


    private lateinit var textView: TextView
    private val receiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {

            Toast.makeText(this@MainActivity, "Received", Toast.LENGTH_SHORT).show()
            textView.text = "yes"
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.status)
    }


    fun startService(view: View) {

        // use this to start and trigger a service
        val i = Intent(this, DownloadService::class.java)
        // potentially add data to the intent

        startService(i)
    }


    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this).registerReceiver(
            receiver, IntentFilter(
            DownloadService.s
            )
        )
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }
}

class DownloadService : IntentService("DownloadService") {


    companion object {
        const val s: String = "actionName"
    }


    override fun onHandleIntent(intent: Intent?) {
//        do some work here
        SystemClock.sleep(1000)

//        talking to all listeners
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(s))
    }


}