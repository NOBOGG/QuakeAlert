package com.owen.quakealert_owen.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.owen.quakealert_owen.R
import com.owen.quakealert_owen.databinding.ActivitySplashBinding
import com.owen.quakealert_owen.viewmodel.GempaViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: GempaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()
        supportActionBar?.hide()
        viewModel = ViewModelProvider(this).get(GempaViewModel::class.java)
        viewModel.getGempaTerkini()
        var dateTime = ""
        var dateTimeNow = LocalDateTime.now().toString()

        viewModel.gempaTerkini.observe(this, Observer {
                response->
            dateTime = response.gempa.DateTime

                sendNotification(response.gempa.Wilayah)

        })

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)
    }
    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Earthquake Alert"
            val descriptionText = "Notification for Earthquake Alert"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("Earthquake Alert",name,importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(Text:String){
        val builder = NotificationCompat.Builder(this,"Earthquake Alert")
            .setSmallIcon(R.drawable.home_logo)
            .setContentTitle("Earthquake Alert")
            .setContentText(Text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        with(NotificationManagerCompat.from(this)){
            notify(1,builder.build())
        }

    }

}