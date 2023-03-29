package com.example.yonibeatonreddymux

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import javax.sql.RowSetListener

class HSCodeAdapter(private val cList: List<HSCodeDataClass>) :
    RecyclerView.Adapter<HSCodeAdapter.ViewHolder>() {
    private lateinit var cListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        cListener = listener
    }

    class ViewHolder(itemView: View,cListener:onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val hsCode = itemView.findViewById<TextView>(R.id.hsCode)
        init {
            itemView.setOnClickListener(){
                cListener.onItemClick(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.hs_code_recyclerview, parent, false)
        return ViewHolder(inflater,cListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = cList[position]
        holder.hsCode.text = listItem.hsCode.toString()
       /* if (lang=="English"){
            SetLocal("en",holder.hsCode)
        }else{
            SetLocal("ar",holder.hsCode)
        }*/
    }

    /*private fun SetLocal(langCode: String, hsCode: TextView?) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        when(hsCode!!.text){
            "HS1111"->hsCode.setText(R.string.hs1111)
            "HS1121"->hsCode.setText(R.string.HS1121)
            "HS1131"->hsCode.setText(R.string.HS1131)
            "HS1141"->hsCode.setText(R.string.HS1141)
            "HS1151"->hsCode.setText(R.string.HS1151)
            "HS1161"->hsCode.setText(R.string.HS1161)
            "HS1171"->hsCode.setText(R.string.HS1171)
            "HS1181"->hsCode.setText(R.string.HS1181)
            "HS1191"->hsCode.setText(R.string.HS1191)
        }
    }*/

    override fun getItemCount(): Int {
        return cList.size
    }
}