import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yonibeatonreddymux.HSCodeDataClass
import com.example.yonibeatonreddymux.NationalityDataClass
import com.example.yonibeatonreddymux.R
import com.example.yonibeatonreddymux.lang
import java.util.*

class NationalityAdapter(private val cList: List<NationalityDataClass>) :
    RecyclerView.Adapter<NationalityAdapter.ViewHolder>() {
    private lateinit var cListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        cListener = listener
    }

    class ViewHolder(itemView: View, cListener:onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val nationality = itemView.findViewById<TextView>(R.id.nationalityRecycler)
        init {
            itemView.setOnClickListener(){
                cListener.onItemClick(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.nationality_recyclerview, parent, false)
        return ViewHolder(inflater,cListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = cList[position]
        holder.nationality.text = listItem.nation
        if(lang=="English"){
            setLocal("en",holder.nationality)
        }else if(lang=="Arabic"){
            setLocal("ar",holder.nationality)
        }
    }

    private fun setLocal(langCode: String, nationality: TextView?) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        when(nationality!!.text){
            "India"->nationality.setText(R.string.India)
            "China"->nationality.setText(R.string.china)
            "America"->nationality.setText(R.string.america)
            "Pakistan"->nationality.setText(R.string.pakistan)
            "Iran"->nationality.setText(R.string.iran)
            "Qatar"->nationality.setText(R.string.Qatar)
            "Canada"->nationality.setText(R.string.canada)
        }
    }

    override fun getItemCount(): Int {
        return cList.size
    }
}