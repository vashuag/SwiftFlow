package com.vashu.swiftflow.firebase

import android.media.session.MediaSessionManager.RemoteUserInfo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.vashu.swiftflow.activities.MainActivity
import com.vashu.swiftflow.activities.SignUpActivity

class FirestoreClass {

    private var mFirestore = FirebaseFirestore.getInstance()

    fun registerUser(activity: SignUpActivity,userInfo: User)
}