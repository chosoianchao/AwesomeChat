package com.rikkei.awesomechat.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rikkei.awesomechat.base.BaseViewModel
import com.rikkei.awesomechat.data.model.User
import com.rikkei.awesomechat.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun validateUser() {
        viewModelScope.launch(Dispatchers.Main + exceptionHandler) {
            repository.validateUser()
        }
    }

    fun setUser(name: String, email: String, password: String) {
        _user.value = User(name = name, email = email, password = password)
        println(email)
    }

    fun register() {
        viewModelScope.launch(Dispatchers.Main + exceptionHandler) {
            repository.signUpWithEmailAndPassword(_user)
        }
    }
}