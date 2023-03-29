package com.example.yonibeatonreddymux

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class CustomAdapter() :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
     var cList= ArrayList<RecyclerDataClass>()
    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val dataItem = itemView.findViewById<TextView>(R.id.tvRecyclerItem)
        val viewRecycler = itemView.findViewById<View>(R.id.viewRecycler)
        fun ViewBind() {
                if (selectedLiveData.value == position) {
                    dataItem.setTextColor(Color.parseColor("#8D0034"))
                    viewRecycler.visibility = View.VISIBLE
                } else {
                    viewRecycler.visibility = View.INVISIBLE
                    dataItem.setTextColor(Color.BLACK)
                }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.main_recyclerview, parent, false)
        return ViewHolder(inflater)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listitem = cList[position]
        holder.dataItem.text = listitem.dataItem
        if(lang=="English"){
            setLocal("en",holder.dataItem)
        }
        if(lang=="Arabic"){
            setLocal("ar", holder.dataItem)
        }
            holder.dataItem.setOnClickListener {
              for (i in 0..tabSelect.size){
                  if(i==position) {
                      selectedLiveData.value = position
                  }
              }
            }
        holder.ViewBind()
    }

    private fun setLocal(langCode: String, dataItem: TextView) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        when(dataItem.text){
            "Main Data"->dataItem.setText(R.string.main_data)
            "Project Site Data"->dataItem.setText(R.string.project_site_date)
            "Products"->dataItem.setText(R.string.products)
            "Machines and spare parts"->dataItem.setText(R.string.machines_and_spare_parts)
            "Raw Materials"->dataItem.setText(R.string.raw_materials)
            "Needed Workers"->dataItem.setText(R.string.needed_workers)
            "Annual Utility"->dataItem.setText(R.string.annual_utility)
            "Attachments"->dataItem.setText(R.string.attachments)
        }
    }

    override fun getItemCount(): Int {
        return cList.size
    }


}