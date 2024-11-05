package vn.mobile.expersystem.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.util.Consumer
import androidx.recyclerview.widget.RecyclerView
import vn.mobile.expersystem.R
import vn.mobile.expersystem.models.Nhom

class RvNhomAdapter(private val onClick:Consumer<Nhom>) : RecyclerView.Adapter<RvNhomAdapter.ItemViewHolder>() {
    private var list:MutableList<Nhom> = mutableListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun setData(lst:List<Nhom>){
        list.clear()
        this.list.addAll(lst)
        notifyDataSetChanged()
    }

    fun updateItem(nhom: Nhom) {
        val index = list.indexOfFirst { it.id == nhom.id }
        if (index != -1) {
            list[index] = nhom
            notifyItemChanged(index)
        }
    }

    fun removeItem(nhom: Nhom) {
        val index = list.indexOf(nhom)
        if (index != -1) { // Kiểm tra nếu item tồn tại trong danh sách
            list.removeAt(index)
            notifyItemRemoved(index)
            notifyItemRangeChanged(index, list.size) // Cập nhật lại các chỉ mục còn lại
        }
    }

    fun addItem(nhom: Nhom) {
        list.add(nhom)
        notifyItemInserted(list.size - 1) // Thông báo cho RecyclerView về vị trí đã thêm
    }

    class ItemViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val name:TextView
        val description:TextView
        var tvId:TextView
        init {
            name = view.findViewById(R.id.tv_name)
            description = view.findViewById(R.id.tv_description)
            tvId = view.findViewById(R.id.tv_id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_group,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.apply {
            holder.name.text = list[position].name
            holder.description.text = list[position].description
            holder.tvId.text = list[position].id


            holder.itemView.setOnClickListener {
                onClick.accept(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }
}