package vn.mobile.expersystem.adapters

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import vn.mobile.expersystem.R

class RvImageAdapter(private var list:List<String>):RecyclerView.Adapter<RvImageAdapter.ItemViewHolder>() {

     class ItemViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = View.inflate(parent.context,R.layout.viewhoder_image,null)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.apply {
            val image = holder.itemView.findViewById<ShapeableImageView>(R.id.image)
            Log.d("list",list[position])
            Glide.with(holder.itemView.context).load(list[position]).into(image)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}