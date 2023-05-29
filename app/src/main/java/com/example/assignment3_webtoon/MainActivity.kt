package com.example.assignment3_webtoon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment3_webtoon.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fragmentList = arrayListOf(
            MondayFragment(),
            TuesdayFragment(),
            WednesdayFragment(),
            ThursdayFragment(),
            FridayFragment(),
            SaturdayFragment(),
            SundayFragment()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "월"
                1 -> tab.text = "화"
                2 -> tab.text = "수"
                3 -> tab.text = "목"
                4 -> tab.text = "금"
                5 -> tab.text = "토"
                6 -> tab.text = "일"
            }
        }.attach()

        binding.searchbtn.setOnClickListener {
            val text = binding.searchtext.text.toString()
            val current = fragmentList[binding.viewPager.currentItem]
            when(current){
                is MondayFragment -> current.search(text)
                is TuesdayFragment -> current.search(text)
                is WednesdayFragment -> current.search(text)
                is ThursdayFragment -> current.search(text)
                is FridayFragment -> current.search(text)
                is SaturdayFragment -> current.search(text)
                is SundayFragment -> current.search(text)
            }
            binding.searchtext.text.clear()
        }
    }
}