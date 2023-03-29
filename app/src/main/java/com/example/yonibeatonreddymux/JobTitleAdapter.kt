package com.example.yonibeatonreddymux

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class JobTitleAdapter(private var cList: List<JobTitleDataClass>) :
    RecyclerView.Adapter<JobTitleAdapter.ViewHolder>() {

    private lateinit var cListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        cListener = listener
    }

    class ViewHolder(itemView: View,cListener:onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val job = itemView.findViewById<TextView>(R.id.jobTitleRecycler)
        init {
            itemView.setOnClickListener{
                cListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.jobtitle_recyclerview, parent, false)
        return ViewHolder(inflater,cListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = cList[position]
        holder.job.text = listItem.job.toString()
        if(lang=="English"){
            setLocal("en",holder.job)
        }else if(lang=="Arabic"){
            setLocal("ar",holder.job)
        }
    }

    private fun setLocal(langCode: String, job: TextView) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        when(job!!.text){
            "Doctor"->job.setText(R.string.doctor)
            "Software Engineer"->job.setText(R.string.software_engineer)
            "Hardware Engineer"->job.setText(R.string.hardware_engineer)
            "Developer"->job.setText(R.string.developer)
            "Teacher"->job.setText(R.string.teacher)
            "Nurse"->job.setText(R.string.nurse)
            "Police"->job.setText(R.string.police)
        }
    }

    override fun getItemCount(): Int {
        return cList.size
    }
}