package com.rikkei.awesomechat.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.rikkei.awesomechat.R
import com.rikkei.awesomechat.utils.setVisibility
import com.rikkei.awesomechat.utils.showToast

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    private var _viewBinding: V? = null
    protected val viewBinding: V
        get() = _viewBinding ?: throw Exception(getString(R.string.error_binding))

    @get:LayoutRes
    protected abstract val layoutResource: Int
    abstract val viewModel: BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil
        .inflate<V>(inflater, layoutResource, container, false)
        .apply { _viewBinding = this }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initViews()
        initData()
        initEvents()
    }

    protected abstract fun initViews()

    protected abstract fun initData()

    protected abstract fun initEvents()

    protected open fun observeViewModel() = with(viewModel) {
        message.observe(viewLifecycleOwner) {
            hideLoading()
            it.getContentIfNotHandled()?.let { value ->
                if (getString(value).isNotBlank()) {
                    context?.showToast(getString(value), Toast.LENGTH_SHORT)
                }
            }
        }
    }

    protected fun observeLoading(view: View) {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { value ->
                view.setVisibility(value)
            }
        }
    }

    open fun showLoading() {}

    open fun hideLoading() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}
