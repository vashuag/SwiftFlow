package com.vashu.swiftflow.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.vashu.swiftflow.R

import com.vashu.swiftflow.databinding.DialogProgressBinding


open class BaseActivity : AppCompatActivity() {
    private lateinit var binding: DialogProgressBinding


    private var doubleBackToExitPressedOnce = false

    private lateinit var mProgressDialog:Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        binding = DialogProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mProgressDialog = Dialog(this)
        mProgressDialog.setContentView(R.layout.dialog_progress)
    }

    fun showProgressDialog(text:String){


        binding.tvProgressText.text = text
        mProgressDialog.show()
    }

    fun hideProgressDialog(){

        mProgressDialog.dismiss()
    }


    fun getCurrentUserID():String{

        return FirebaseAuth.getInstance().currentUser!!.uid
    }

    fun doubleBackToExit(){
        if(doubleBackToExitPressedOnce){
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this,
        resources.getString(R.string.please_click_back_again_to_exit),
        Toast.LENGTH_SHORT).show()

        Handler().postDelayed({
            doubleBackToExitPressedOnce = false
        },2000)
    }

    fun showErrorSnackBar(message:String){
        val snackBar = Snackbar.make(findViewById(android.R.id.content),

            message,Snackbar.LENGTH_LONG)

        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(this,R.color.snackBar_error_color))

        snackBar.show()
    }


}