package com.example.yonibeatonreddymux

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ProofAdapter(private val list: List<ProofRecyclerData>) :
    RecyclerView.Adapter<ProofAdapter.ViewHolder>() {
    private lateinit var cListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        cListener = listener
    }

    class ViewHolder(itemView: View,cListener:onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val items = itemView.findViewById<TextView>(R.id.proofItems)
        init {
            itemView.setOnClickListener() {
                cListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_proof, parent, false)
        return ViewHolder(inflater,cListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = list[position]
        holder.items.text = listItem.proof
        if(lang=="English"){
            SetLocal("en",holder.items)
        }else{
            SetLocal("ar",holder.items)
        }
    }

    private fun SetLocal(langCode: String, items: TextView?) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
       /* when(items!!.text){
            "PR1001"->
            "PR1002"->R.string.
            "PR1003"->
            "PR1004"->
            "PR1005"->
        }*/
    }

    override fun getItemCount(): Int {
        return list.size
    }
}