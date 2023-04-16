package com.vashu.swiftflow.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.vashu.swiftflow.R
import com.vashu.swiftflow.databinding.ActivitySignInBinding


class SignInActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding: ActivitySignInBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = FirebaseAuth.getInstance()


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


        setupActionBar()

        binding.btnSignIn.setOnClickListener{
            signInRegisteredUser()
        }




    }


    private fun setupActionBar(){

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Set the toolbar as the ActionBar
        setSupportActionBar(binding.toolbarSignInActivity)


        val actionBar = supportActionBar
        if(actionBar!= null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        }

        binding.toolbarSignInActivity.setNavigationOnClickListener { onBackPressed() }


    }


    private fun signInRegisteredUser(){

        val email:String = this.binding.etEmailSignIn.text.toString().trim{it<=' '}
        val password:String = this.binding.etPasswordSignIn.text.toString().trim{it<=' '}



        if (validateForm(email, password)){

            showProgressDialog(resources.getString(R.string.please_wait))



            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                        task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {

                        Log.d("sign_in", "signInWithEmail:success")
                        val user = auth.currentUser

                        startActivity(Intent(this,MainActivity::class.java))


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("error_signin", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }}}


    private fun validateForm(email: String, password: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            showErrorSnackBar("Please enter email.")
            false
        } else if (TextUtils.isEmpty(password)) {
            showErrorSnackBar("Please enter password.")
            false
        } else {
            true
        }
    }


}