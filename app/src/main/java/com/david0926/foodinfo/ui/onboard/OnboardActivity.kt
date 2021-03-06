package com.david0926.foodinfo.ui.onboard

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.david0926.foodinfo.R
import com.david0926.foodinfo.base.BaseActivity
import com.david0926.foodinfo.data.model.Onboard
import com.david0926.foodinfo.databinding.ActivityOnboardBinding
import com.david0926.foodinfo.ui.all.ViewPagerAdapter
import com.david0926.foodinfo.util.DataStoreUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OnboardActivity : BaseActivity<ActivityOnboardBinding>(R.layout.activity_onboard) {

    private val viewModel by viewModels<OnboardViewModel>()

    @Inject
    lateinit var dataStoreUtil: DataStoreUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        val fragments = initOnboardFragments()

        binding.maxPage = fragments.size
        binding.vpOnboard.adapter = ViewPagerAdapter(this, fragments)
        binding.vpOnboard.offscreenPageLimit = fragments.size - 1

        binding.btOnboardSkip.setOnClickListener { finish() }
        binding.btOnboardFinish.setOnClickListener { finish() }
    }

    override fun onBackPressed() {
        val page = viewModel.currentPage.value!!
        if (page == 0) super.onBackPressed()
        else binding.vpOnboard.currentItem = page - 1
    }

    override fun finish() {
        lifecycleScope.launch {
            dataStoreUtil.afterFirstLaunch(this@OnboardActivity)
            super.finish()
            overridePendingTransition(R.anim.slide_down_enter, R.anim.slide_down_leave)
        }
    }

    private fun initOnboardFragments(): List<Fragment> {
        return listOf(
            OnboardFragment.newInstance(
                Onboard(
                    getString(R.string.lottie_onboard_1),
                    getString(R.string.onboard_title_1),
                    getString(R.string.onboard_description_1)
                )
            ),
            OnboardFragment.newInstance(
                Onboard(
                    getString(R.string.lottie_onboard_2),
                    getString(R.string.onboard_title_2),
                    getString(R.string.onboard_description_2)
                )
            ),
            OnboardFragment.newInstance(
                Onboard(
                    getString(R.string.lottie_onboard_3),
                    getString(R.string.onboard_title_3),
                    getString(R.string.onboard_description_3)
                )
            ),
        )
    }
}