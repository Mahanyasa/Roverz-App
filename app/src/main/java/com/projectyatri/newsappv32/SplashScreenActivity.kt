package com.projectyatri.newsappv32

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBar

@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_layout)


        Handler().postDelayed({
            val intent = Intent(this@SplashScreenActivity, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}