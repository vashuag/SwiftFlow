package com.vashu.swiftflow

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        window.setFlags(
            FLAG_FULLSCREEN,
            FLAG_FULLSCREEN

        )

//        val typeFace: Typeface = Typeface.createFromAsset((assets,""))
//
//        tv_app_name.typeface =typeFace

    }
}