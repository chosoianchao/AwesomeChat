package com.rikkei.awesomechat.ui.forgotpassword

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rikkei.awesomechat.R
import com.rikkei.awesomechat.base.BaseFragment
import com.rikkei.awesomechat.databinding.FragmentForgetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgetPasswordBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_forget_password

    override val viewModel: ForgotPasswordViewModel by viewModels()

    override fun initViews() {
    }

    override fun initData() {
        with(viewBinding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@ForgotPasswordFragment.viewModel
        }
    }

    override fun initEvents() {
        viewBinding.tvConfirm.setOnClickListener {
            confirmForgot()
        }
        viewBinding.imageBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun confirmForgot() {
        viewModel.apply {
            setEmail(viewBinding.editEmail.text.toString().trim())
            forgot()
        }

    }
}