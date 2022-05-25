package com.nqhuy.expandablelayout.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nqhuy.expandablelayout.R


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager, private val list: ArrayList<FragmentModel>) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return list[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].javaClass.simpleName
    }

    override fun getCount(): Int {
        return list.size
    }
}