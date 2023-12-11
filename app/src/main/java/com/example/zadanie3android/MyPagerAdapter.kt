package com.example.zadanie3android

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

val PAGE_COUNT = 3

class MyPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentTab1.newInstance("f1", "Page # 1")
            1 -> FragmentTab2.newInstance("f2", "Page # 2")
            2 -> FragmentTab3.newInstance("f3", "Page # 3")
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
    override fun getItemCount(): Int {
        return PAGE_COUNT
    }
}