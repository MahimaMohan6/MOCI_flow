package com.example.yonibeatonreddymux

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class UnitAdapter(private val cList: List<UnitDataClass>) :
    RecyclerView.Adapter<UnitAdapter.ViewHolder>() {

    private lateinit var cListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        cListener = listener
    }

    class ViewHolder(itemView: View,cListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val unit = itemView.findViewById<TextView>(R.id.unit)
        init {
            itemView.setOnClickListener(){
                cListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.unit_recyclerview, parent, false)
        return ViewHolder(inflater,cListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = cList[position]
        holder.unit.text=listItem.unit.toString()
    }

    override fun getItemCount(): Int {
        return cList.size
    }
}