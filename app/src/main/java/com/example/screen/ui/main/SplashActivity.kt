package com.example.screen.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.screen.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() { // geri gelince countdowntimer çalışması için
        var animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        imWeather.animation = animation
        object :
            CountDownTimer(5000, 1000) { //animasyon devam ederken süre sayıp main activity geçecek
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                var intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)

            }

        }.start()
        super.onResume()
    }
}