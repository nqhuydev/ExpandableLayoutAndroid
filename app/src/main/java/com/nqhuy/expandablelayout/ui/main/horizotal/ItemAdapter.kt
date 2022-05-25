package com.nqhuy.expandablelayout.ui.main.horizotal

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nqhuy.expandablelayout.databinding.ItemViewAdapterBinding

class ItemAdapter(private val context: Context?, var list: Array<String>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var _binding: ItemViewAdapterBinding? = null
    private val binding get() = _binding!!

    class ViewHolder internal constructor(private val binding: ItemViewAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(string: String) {
            binding.tvName.text = string
            binding.tvName.setOnClickListener {
                Log.e(
                    "ItemAdapter",
                    "$string $adapterPosition"
                )
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        _binding = ItemViewAdapterBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * Here is the key method to apply the animation
     */
    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
    }
}