package com.rikkei.awesomechat.ui.mainchat

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.rikkei.awesomechat.R
import com.rikkei.awesomechat.base.BaseFragment
import com.rikkei.awesomechat.databinding.FragmentMainChatBinding
import com.rikkei.awesomechat.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainChatFragment : BaseFragment<FragmentMainChatBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_main_chat
    override val viewModel: MainChatViewModel by viewModels()

    override fun initViews() {

    }

    override fun initData() {

    }

    override fun initEvents() {

    }
}