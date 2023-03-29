package com.example.yonibeatonreddymux

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AddNeededWorkersAdapter() :
    RecyclerView.Adapter<AddNeededWorkersAdapter.ViewHolder>() {
    var cList=ArrayList<AddNeededWorkersDataClass>()
    private lateinit var cListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        cListener = listener
    }
    class ViewHolder(itemView: View,cListener:onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val nationality = itemView.findViewById<TextView>(R.id.tvNationalityValue)
        val jobTitle = itemView.findViewById<TextView>(R.id.tvJobTitleValue)
        val quantity = itemView.findViewById<TextView>(R.id.tvQuantityValue)
        val annualSalary = itemView.findViewById<TextView>(R.id.tvAnnualSalary)
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
            LayoutInflater.from(parent.context).inflate(R.layout.add_neededworkers, parent, false)
        return ViewHolder(inflater,cListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = cList[position]
        holder.nationality.text = listItem.nationality
        holder.jobTitle.text = listItem.jobTitle
        holder.quantity.text = listItem.quantity
        holder.annualSalary.text = listItem.annualSalary
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