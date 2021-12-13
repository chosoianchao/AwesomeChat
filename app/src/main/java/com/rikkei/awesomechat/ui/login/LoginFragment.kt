package com.rikkei.awesomechat.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rikkei.awesomechat.R
import com.rikkei.awesomechat.base.BaseFragment
import com.rikkei.awesomechat.databinding.FragmentLoginBinding
import com.rikkei.awesomechat.utils.showToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth
    private val TAG: String = LoginFragment::class.java.name

    override val layoutResource get() = R.layout.fragment_login
    override val viewModel: LoginViewModel by viewModels()

    override fun initViews() {
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val action = LoginFragmentDirections.actionLoginFragmentToMainChatFragment()
            findNavController().navigate(action)
        }
        getBackStackData("bundleKey", ::onResult)
    }

    override fun initData() {
        with(viewBinding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@LoginFragment.viewModel
        }
    }

    private fun onResult(bundle: Bundle) {
        bundle.getString("email")
        bundle.getString("password")
        viewBinding.editEmail.setText(bundle.getString("email"))
        viewBinding.editPassword.setText(bundle.getString("password"))
    }

    private fun <T : Any> Fragment.getBackStackData(key: String, result: (T) -> (Unit)) {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
            ?.observe(viewLifecycleOwner) {
                result(it)
            }
    }

    override fun initEvents() {
        with(viewBinding) {
            listOf(
                buttonLogin,
                textForgetPassword,
                textSignupNow,
            ).forEach {
                it.setOnClickListener(this@LoginFragment)
            }
            handleStateButton(viewBinding)
        }
        viewModel.user.observe(viewLifecycleOwner) {
            checkUser(uid = it.uid)
        }
        viewModel.validateUser()
    }

    //bug
    private fun checkUser(uid: String?) {
        Log.d("thang", "checkUser: ")
        uid.let {
            if (it != null) {
                if (it.isNullOrBlank().not()) {
                    Log.d(TAG, "checkUser: success")
                    loginSuccess()
                } else {
                    Log.d(TAG, "checkUser: error")
                    context?.showToast(getString(R.string.error_something_wrong))
                }
            }
        }
    }

    private fun handleStateButton(viewBinding: FragmentLoginBinding) {
        viewBinding.editPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (!p0.isNullOrBlank() && !viewBinding.editEmail.text.isNullOrBlank()) {
                    changeButtonStatus(true)
                } else {
                    changeButtonStatus(false)
                }
            }
        })
        viewBinding.editEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (!p0.isNullOrBlank() && !viewBinding.editPassword.text.isNullOrBlank()) {
                    changeButtonStatus(true)
                } else {
                    changeButtonStatus(false)
                }
            }
        })
    }

    override fun onClick(v: View?): Unit = with(viewBinding) {
        when (v) {
            buttonLogin -> login()
            textForgetPassword -> openForgotPasswordFragment()
            textSignupNow -> openRegisterFragment()
        }
    }

    private fun openRegisterFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(action)
    }

    private fun changeButtonStatus(isEnable: Boolean) = with(viewBinding) {
        if (isEnable) {
            buttonLogin.isEnabled = true
            context?.getColor(R.color.san_marino)?.let { buttonLogin.background.setTint(it) }
        } else {
            buttonLogin.isEnabled = false
            context?.getColor(R.color.silver)?.let { buttonLogin.background.setTint(it) }
        }
    }

    private fun login() {
        viewModel.apply {
            setUser(
                viewBinding.editEmail.text.toString(),
                viewBinding.editPassword.text.toString()
            )
            login()
        }
    }

    private fun openForgotPasswordFragment() {
        val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
        findNavController().navigate(action)
    }

    private fun loginSuccess() {
        context?.showToast(getString(R.string.success_login))
        val action = LoginFragmentDirections.actionLoginFragmentToMainChatFragment()
        findNavController().navigate(action)
    }
}

