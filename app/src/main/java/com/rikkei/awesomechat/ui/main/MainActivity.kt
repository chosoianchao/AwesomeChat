package com.rikkei.awesomechat.ui.main

import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.rikkei.awesomechat.R
import com.rikkei.awesomechat.base.BaseActivity
import com.rikkei.awesomechat.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResource get() = R.layout.activity_main
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
    }

    private val navController by lazy { navHostFragment.navController }

    override fun initViews() {
        viewBinding.bottomNavigation.apply {
            setupWithNavController(navController)
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.mainChatFragment) {
                viewBinding.groupBottomNav.visibility = View.VISIBLE
            }
        }
    }
}
