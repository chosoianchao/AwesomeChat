package com.rikkei.awesomechat.data.source.remote

import androidx.lifecycle.MutableLiveData
import com.rikkei.awesomechat.data.model.User
import com.rikkei.awesomechat.data.source.AuthDataSource
import com.rikkei.awesomechat.data.source.remote.util.FirebaseAPI

class AuthRemoteDatasource : AuthDataSource.Remote {

    override suspend fun validateUser() =
        FirebaseAPI.validateUser()

    override suspend fun getCurrentUser() =
        FirebaseAPI.getCurrentUser()

    override suspend fun signInWithEmailAndPassword(user: MutableLiveData<User>) =
        FirebaseAPI.signInWithEmailAndPassword(user)

    override suspend fun signUpWithEmailAndPassWord(user: MutableLiveData<User>) =
        FirebaseAPI.signUpWithEmailAndPassword(user)

    override suspend fun forgotPassword(user: MutableLiveData<User>) =
        FirebaseAPI.forgotPassword(user)
}
