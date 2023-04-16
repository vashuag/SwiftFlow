package com.vashu.swiftflow.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import androidx.appcompat.app.AppCompatActivity
import com.vashu.swiftflow.R


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Use the new API for Android 11 and higher
            val insetsController = window.insetsController
            insetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        } else {
            // Use the old deprecated way for pre-Android 11
            window.setFlags(
                FLAG_FULLSCREEN,
                FLAG_FULLSCREEN
            )
        }


//        val typeFace: Typeface = Typeface.createFromAsset((assets,""))
//
//        tv_app_name.typeface =typeFace

        Handler().postDelayed({
                              startActivity(Intent(this, IntroActivity::class.java))
            finish()


        },2000)



    }
}