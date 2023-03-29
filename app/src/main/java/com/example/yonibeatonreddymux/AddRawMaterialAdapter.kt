package com.example.yonibeatonreddymux

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AddRawMaterialAdapter(var cList: ArrayList<AddRawMaterialDataClass>) :
    RecyclerView.Adapter<AddRawMaterialAdapter.ViewHolder>() {

    private lateinit var cListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        cListener = listener
    }

    class ViewHolder(itemView: View,cListener:onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val hsCode = itemView.findViewById<TextView>(R.id.tvProductHSCodeValue)
        val materialName = itemView.findViewById<TextView>(R.id.tvProductNameValue)
        val unit = itemView.findViewById<TextView>(R.id.tvProductUnitValue)
        val value = itemView.findViewById<TextView>(R.id.tvProductValueValue)
        val quantity = itemView.findViewById<TextView>(R.id.tvProductQuantityValue)
        val deleteBtn=itemView.findViewById<ImageView>(R.id.ivDelete)
        val editBtn=itemView.findViewById<ImageView>(R.id.ivEdit)
        init {
            editBtn.setOnClickListener(){
                cListener.onItemClick(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.add_rawmaterial, parent, false)
        return ViewHolder(inflater,cListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = cList[position]
        holder.hsCode.text = listItem.hsCode
        holder.materialName.text = listItem.materialName
        holder.unit.text = listItem.unit
        holder.value.text = listItem.value
        holder.quantity.text = listItem.Quantity
        holder.deleteBtn.setOnClickListener {
            cList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,cList.size)
        }

    }

    override fun getItemCount(): Int {
        return cList.size
    }

}