package com.vashu.swiftflow.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.vashu.swiftflow.R
import com.vashu.swiftflow.databinding.ActivitySignInBinding

import com.vashu.swiftflow.databinding.ActivitySignUpBinding



class SignUpActivity : BaseActivity() {

    private lateinit var binding : ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        setupActionBar()

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





    }

    private fun setupActionBar(){


        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the toolbar as the ActionBar
        setSupportActionBar(this.binding.toolbarSignUpActivity)


        val actionBar = supportActionBar
        if(actionBar!= null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
        }

        this.binding.toolbarSignUpActivity.setNavigationOnClickListener { onBackPressed() }

        binding.btnSignUp.setOnClickListener{
            registerUser()
        }


    }

    private fun registerUser(){

        val name:String = this.binding.etName.text.toString().trim{it<=' '}
        val email:String = this.binding.etEmail.text.toString().trim{it<=' '}
//        val pas:String = et_name.text.toString().trim{it<=' '}

        val password:String = binding.etPassword.text.toString()

        if (validateForm(name,email, password)){

           showProgressDialog(resources.getString(R.string.please_wait))

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                    task->
                    hideProgressDialog()
                    if(task.isSuccessful){
                        val firebaseuser:FirebaseUser = task.result!!.user!!
                        val registeredEmail = firebaseuser.email!!
                        Toast.makeText(this,"$name you have successfully registered the email address $registeredEmail",
                        Toast.LENGTH_LONG).show()

                        FirebaseAuth.getInstance().signOut()
                        finish()
                    }

                    else{
                        Toast.makeText(this,"Registeration Failed",Toast.LENGTH_LONG).show()
                    }
                }

//            Toast.makeText(this@SignUpActivity,
//            "Now we can register", Toast.LENGTH_SHORT).show()

        }

    }


    private fun validateForm(name:String,email:String,password:String): Boolean {

        return when{
            TextUtils.isEmpty(name)->{
                showErrorSnackBar("Please enter a name")
                false
            }
            TextUtils.isEmpty(email)->{
                showErrorSnackBar("Please enter email")
                false
            }

            TextUtils.isEmpty(password)->{
                showErrorSnackBar("Please enter a password")
                false
            }
            else->{
                true
            }
        }
    }



}