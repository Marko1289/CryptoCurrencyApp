package com.example.cryptocurrencyapp.activities

import android.icu.text.IDNA
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.fragments.DiagramFragment
import com.example.cryptocurrencyapp.fragments.InfoFragment
import com.example.cryptocurrencyapp.models.CryptoCurrencyModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private const val NUM_PAGES = 2

class InfoAndDiagramActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var coinModel: CryptoCurrencyModel
    private lateinit var viewPagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_and_diagram_activity)

        coinModel = intent.getSerializableExtra("coinModel") as CryptoCurrencyModel
        setViews()
        setAdapter()
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()

        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    fun setViews() {
        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)

    }

    fun setAdapter() {

        viewPager.adapter = PagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Info"
                1 -> tab.text = "Diagram"
                else -> {
                    tab.text = "Info"
                }
            }
        }.attach()
    }

    private inner class PagerAdapter(fa: AppCompatActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> InfoFragment(coinModel)
                1 -> DiagramFragment()
                else -> {
                    InfoFragment(coinModel)
                }
            }
        }
    }
}