package com.rikkei.awesomechat.ui.mainchat

import com.rikkei.awesomechat.base.BaseViewModel
import com.rikkei.awesomechat.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainChatViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel() {
    
}