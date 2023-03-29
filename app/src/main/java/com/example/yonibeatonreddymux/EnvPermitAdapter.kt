package com.example.yonibeatonreddymux

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EnvPermitAdapter(private val cList: List<EnvPermitData>) :
    RecyclerView.Adapter<EnvPermitAdapter.ViewHolder>() {

    private lateinit var cListener: onItemClickListener
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener) {
        cListener = listener
    }
    class ViewHolder(itemView: View,cListener:onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val item = itemView.findViewById<TextView>(R.id.permitNumber)
        init {
            itemView.setOnClickListener() {
                cListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_envpermit, parent, false)
        return ViewHolder(inflater,cListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = cList[position]
        holder.item.text = listItem.number
    }

    override fun getItemCount(): Int {
        return cList.size
    }
}