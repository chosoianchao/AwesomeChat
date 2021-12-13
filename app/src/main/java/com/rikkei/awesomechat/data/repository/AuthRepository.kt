package com.rikkei.awesomechat.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.rikkei.awesomechat.data.model.User

interface AuthRepository {
    suspend fun validateUser(): Boolean
    suspend fun getCurrentUser(): FirebaseUser?
    suspend fun signInWithEmailAndPassword(user: MutableLiveData<User>)
    suspend fun signUpWithEmailAndPassword(user: MutableLiveData<User>)
    suspend fun forgotPassword(user: MutableLiveData<User>)
}
