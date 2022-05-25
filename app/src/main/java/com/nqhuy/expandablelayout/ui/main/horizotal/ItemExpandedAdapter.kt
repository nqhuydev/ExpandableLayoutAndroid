package com.nqhuy.expandablelayout.ui.main.horizotal

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.nqhuy.expandablelayout.databinding.ItemExpandedViewAdapterBinding
import com.nqhuydev.expandablelayout.ExpandableLayout
import com.nqhuydev.expandablelayout.ExpandableLayout.OnExpansionUpdateListener


class ItemExpandedAdapter(var context: Context?, private var list: List<Array<String>>, var recyclerView: RecyclerView) : RecyclerView.Adapter<ItemExpandedAdapter.ViewHolder>() {
    private var selectedItem = UNSELECTED

    companion object {
        private const val UNSELECTED = -1
    }

    private var _binding : ItemExpandedViewAdapterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        _binding = ItemExpandedViewAdapterBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(binding: ItemExpandedViewAdapterBinding) : RecyclerView.ViewHolder(binding.root), OnExpansionUpdateListener, View.OnClickListener {

        private val expandButton  = binding.expandButton
        val expandableLayout  = binding.expandableLayout
        private val rcDemo  = binding.rcDemo
        private val tvPos = binding.tvPos
        fun bind(item: Array<String>) {
            val position = adapterPosition
            val isSelected = position == selectedItem
            expandButton.isSelected = isSelected
            expandButton.rotation = 180f
            expandButton.setOnClickListener(this)
            expandableLayout.setOnExpansionUpdateListener(this)
            expandableLayout.setExpanded(false, false)
            tvPos.text = "$position"

            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            rcDemo.layoutManager = linearLayoutManager
            val itemAdapter = ItemAdapter(context, item)
            rcDemo.adapter = itemAdapter

        }

        override fun onExpansionUpdate(expansionFraction: Float, state: Int) {
//            Log.e("onExpansionUpdate", "state= $state")
            if (state == ExpandableLayout.State.EXPANDED) {
//                (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(adapterPosition,0)
                //----------------
//                val smoothScroller: SmoothScroller = object : LinearSmoothScroller(context) {
//                    override fun getHorizontalSnapPreference(): Int {
//                        return SNAP_TO_START
//                    }
//                }
//                smoothScroller.targetPosition = adapterPosition
//                (recyclerView.layoutManager as LinearLayoutManager).startSmoothScroll(smoothScroller)
                //----------------
                recyclerView.smoothSnapToPosition(adapterPosition, LinearSmoothScroller.SNAP_TO_START)
            }
            expandButton.alpha = expansionFraction * 1
        }

        override fun onClick(v: View) {
            val holder = recyclerView.findViewHolderForAdapterPosition(selectedItem) as ViewHolder?
            if (holder != null) {
                holder.expandButton.isSelected = false
                holder.expandableLayout.collapse()
            }
            val position = adapterPosition
            if (position == selectedItem) {
                selectedItem = UNSELECTED
            } else {
                selectedItem = position
                expandButton.isSelected = true
                expandableLayout.expand()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun RecyclerView.smoothSnapToPosition(position: Int, snapMode: Int = LinearSmoothScroller.SNAP_TO_ANY) {
        val smoothScroller = object : LinearSmoothScroller(this.context) {
            override fun getVerticalSnapPreference(): Int = snapMode
            override fun getHorizontalSnapPreference(): Int = snapMode
        }
        smoothScroller.targetPosition = position
        layoutManager?.startSmoothScroll(smoothScroller)
    }
}