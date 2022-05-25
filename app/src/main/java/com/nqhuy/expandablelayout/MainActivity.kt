package com.nqhuy.expandablelayout

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.nqhuy.expandablelayout.ui.main.SectionsPagerAdapter
import com.nqhuy.expandablelayout.databinding.ActivityMainBinding
import com.nqhuy.expandablelayout.ui.main.FragmentModel
import com.nqhuy.expandablelayout.ui.main.HorizontalExpandableFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ArrayList<FragmentModel>()
        list.add(FragmentModel("Horizontal", HorizontalExpandableFragment()))
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, list)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = binding.fab

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onBackPressed() {

    }
}
