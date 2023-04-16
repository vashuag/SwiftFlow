package com.vashu.swiftflow.activities


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

import com.vashu.swiftflow.R
import com.vashu.swiftflow.databinding.ActivityIntroBinding
import com.airbnb.lottie.LottieAnimationView;



class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding


    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_intro)







        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Use the new API for Android 11 and higher
            val insetsController = window.insetsController
            insetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        } else {
            // Use the old deprecated way for pre-Android 11
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        // This is used to get the file from the assets folder and set it to the title textView.
//        val typeface: Typeface =
//            Typeface.createFromAsset(assets, "carbon bl.ttf")
//        tv_app_name_intro.typeface = typeface

        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignUpIntro.setOnClickListener{

            startActivity(Intent(this, SignUpActivity::class.java))
        }


        binding.btnSignInIntro.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))


        }

        val animationView: LottieAnimationView =binding.animationView
       animationView.setAnimation(R.raw.data)
       animationView.playAnimation()




    }
}