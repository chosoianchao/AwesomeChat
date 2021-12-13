package com.rikkei.awesomechat.data.repository

import androidx.lifecycle.MutableLiveData
import com.rikkei.awesomechat.data.model.User
import com.rikkei.awesomechat.data.source.AuthDataSource

class AuthRepositoryImpl(
    private val remote: AuthDataSource.Remote
) : AuthRepository {

    override suspend fun validateUser() =
        remote.validateUser()

    override suspend fun getCurrentUser() =
        remote.getCurrentUser()

    override suspend fun signInWithEmailAndPassword(user: MutableLiveData<User>) =
        remote.signInWithEmailAndPassword(user)

    override suspend fun signUpWithEmailAndPassword(user: MutableLiveData<User>) =
        remote.signUpWithEmailAndPassWord(user)

    override suspend fun forgotPassword(user: MutableLiveData<User>) {
        remote.forgotPassword(user)
    }
}
