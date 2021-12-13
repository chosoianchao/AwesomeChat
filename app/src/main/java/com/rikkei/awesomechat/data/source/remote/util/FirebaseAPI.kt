package com.rikkei.awesomechat.data.source.remote.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.rikkei.awesomechat.data.model.User

object FirebaseAPI {
    val TAG: String = FirebaseAPI::class.java.name
    fun validateUser() = FirebaseAuth.getInstance().currentUser != null

    fun getCurrentUser() = FirebaseAuth.getInstance().currentUser

    fun signInWithEmailAndPassword(user: MutableLiveData<User>) {
        val newUser = user.value?.copy()
        user.value?.let { it ->
            FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(it.email, it.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        newUser?.uid = getCurrentUser()?.uid ?: ""
                    } else {
                        newUser?.uid = ""
                    }
                    user.value = newUser
                }
                .addOnFailureListener {
                    user.value = newUser
                }
        }
    }

    fun signUpWithEmailAndPassword(user: MutableLiveData<User>) {
        val newUser = user.value?.copy()
        user.value?.let { it ->
            FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(it.email, it.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        newUser?.uid = getCurrentUser()?.uid ?: ""
                    } else {
                        newUser?.uid = ""
                    }
                    user.value = newUser
                }
                .addOnFailureListener {
                    user.value = newUser
                    it.printStackTrace()
                }
        }
    }

    fun forgotPassword(user: MutableLiveData<User>) {
        user.value?.let {
            FirebaseAuth.getInstance().sendPasswordResetEmail(it.email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "forgotPassword: ${task.result}")
                    }
                }.addOnFailureListener { exception ->
                    Log.d(TAG, "forgotPassword: "+  exception

                        .printStackTrace())
                }
        }
    }
}
