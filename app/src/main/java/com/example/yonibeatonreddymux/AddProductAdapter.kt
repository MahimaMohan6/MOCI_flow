package com.example.yonibeatonreddymux

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class AddProductAdapter() : RecyclerView.Adapter<AddProductAdapter.ViewHolder>() {
    var cList=ArrayList<AddProductDataClass>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hscode = itemView.findViewById<TextView>(R.id.tvProductHSCodeValue)
        val productName = itemView.findViewById<TextView>(R.id.tvProductNameValue)
        val unit = itemView.findViewById<TextView>(R.id.tvProductUnitValue)
        val sellingPrice = itemView.findViewById<TextView>(R.id.tvProductSellingPriceValue)
        val annualProductionCapacity =
            itemView.findViewById<TextView>(R.id.tvAnnualProductionCapacityValue)
        val annualExportQuantity = itemView.findViewById<TextView>(R.id.tvAnnualExportQuantityValue)
        val deleteBtn=itemView.findViewById<ImageView>(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.add_products, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = cList[position]
        holder.hscode.text = listItem.hsCode
        holder.productName.text = listItem.productName
        holder.unit.text = listItem.Unit
        holder.sellingPrice.text = listItem.sellingPrice
        holder.annualProductionCapacity.text = listItem.annualProductionCapacity
        holder.annualExportQuantity.text = listItem.annualExportQuantity
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