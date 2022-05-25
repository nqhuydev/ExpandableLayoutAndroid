package com.nqhuy.expandablelayout.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nqhuy.expandablelayout.databinding.FragmentMainBinding
import com.nqhuy.expandablelayout.ui.main.horizotal.ItemExpandedAdapter


class HorizontalExpandableFragment : Fragment() {


    companion object {
        @JvmStatic
        fun newInstance(): HorizontalExpandableFragment {
            return HorizontalExpandableFragment()
        }
    }


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var listData : List<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val huy = arrayOf("Huy","Huy","Huy", "Huy")
        val chang = arrayOf("Chang","Chang","Chang")
        val ong = arrayOf("Ong","Ong","Ong", "Ong")
        val gau = arrayOf("Gau","Gau","Gau", "Gau")

        listData = arrayListOf(huy, chang, ong, gau,huy, chang, ong, gau,huy, chang, ong, gau)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root

        val recyclerView = binding.recyclerView
        val linearLayoutManager =LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = linearLayoutManager

        val itemExpandableAdapter = ItemExpandedAdapter(context, listData, recyclerView)
        recyclerView.adapter = itemExpandableAdapter

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
