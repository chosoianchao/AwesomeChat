package com.rikkei.awesomechat.data.source

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.rikkei.awesomechat.data.model.User

interface AuthDataSource {

    interface Remote {
        suspend fun validateUser(): Boolean
        suspend fun getCurrentUser(): FirebaseUser?
        suspend fun signInWithEmailAndPassword(user: MutableLiveData<User>)
        suspend fun signUpWithEmailAndPassWord(user: MutableLiveData<User>)
        suspend fun forgotPassword(user: MutableLiveData<User>)
    }
}
