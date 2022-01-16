package com.david0926.foodinfo.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.david0926.foodinfo.R
import com.david0926.foodinfo.base.BaseActivity
import com.david0926.foodinfo.databinding.ActivityMainBinding
import com.david0926.foodinfo.ui.onboard.OnboardActivity
import com.david0926.foodinfo.util.DataStoreUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var dataStoreUtil: DataStoreUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        beforeSetContentView = { installSplashScreen() }
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        checkOnboard()

        binding.rvMain.layoutManager = GridLayoutManager(this, 2)
        binding.rvMain.adapter = MainRecyclerAdapter()
    }

    override fun onBackPressed() {
        binding.rvMain.run {
            if (canScrollVertically(-1)) smoothScrollToPosition(0)
            else super.onBackPressed()
        }
    }

    private fun checkOnboard() {
        lifecycleScope.launch {
            dataStoreUtil.isFirstLaunch.asLiveData().observe(this@MainActivity) { isFirst ->
                if (isFirst) startActivity(Intent(this@MainActivity, OnboardActivity::class.java))
            }
        }
    }
}