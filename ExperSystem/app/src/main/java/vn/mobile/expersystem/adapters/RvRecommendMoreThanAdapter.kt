package vn.mobile.expersystem.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vn.mobile.expersystem.R

class RvRecommendMoreThanAdapter (private var list: MutableList<String>): RecyclerView.Adapter<RvRecommendMoreThanAdapter.ItemViewHolder>() {
    class ItemViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvResult:TextView
        init {
            tvResult = view.findViewById(R.id.tv_result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recommend_more,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.tvResult.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}