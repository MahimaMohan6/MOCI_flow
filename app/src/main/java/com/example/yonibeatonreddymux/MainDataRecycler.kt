package com.example.yonibeatonreddymux

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yonibeatonreddymux.databinding.FragmentMainDataRecyclerBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

var selectedLiveData = MutableLiveData<Int>(0)
object Session{
    var pinNumber:String=""
    var locationName:String=""
    var ZoneCode:String=""
    var districtNumber:String=""
    var street:String=""
    var buildingNumber:String=""
    var proof:String=""
    var releaseDate:String=""
    var lessor:String=""
    var organisers:String=""
    var permitNumber:String=""
    var permitIssueDate:String=""
    var permitExpiryDate:String=""
    var productlist=ArrayList<AddProductDataClass>()
    var neededWorkersList=ArrayList<AddNeededWorkersDataClass>()
    var rawMaterialList=ArrayList<AddRawMaterialDataClass>()
    var machineList=ArrayList<AddMachineAndSpare>()
    var totalInvest:String=""
    var area:String=""
    var electricity:String=""
    var water:String=""
    var gas:String=""
    var manufacturing:String=""
    lateinit var uploadUri1:Uri
    lateinit var uploadUri2:Uri
    lateinit var uploadUri3:Uri
    var uploadText1:String=""
    var uploadText2:String=""
    var uploadText3:String=""
}
@Suppress("DEPRECATION")
class MainDataRecycler : Fragment() {
    lateinit var binding: FragmentMainDataRecyclerBinding
    lateinit var recyclerView: RecyclerView
    var List = ArrayList<RecyclerDataClass>()
    var fragment = Fragment()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainDataRecyclerBinding.inflate(inflater, container, false)
        val view = binding.root
        //inflater.inflate(R.layout.fragment_main_data_recycler, container, false)
        recyclerView = binding.recyclerView
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        List.add(RecyclerDataClass(1, "Main Data"))
        List.add(RecyclerDataClass(2, "Project Site Data"))
        List.add(RecyclerDataClass(3, "Products"))
        List.add(RecyclerDataClass(4, "Machines and spare parts"))
        List.add(RecyclerDataClass(5, "Raw Materials"))
        List.add(RecyclerDataClass(6, "Needed Workers"))
        List.add(RecyclerDataClass(7, "Annual Utility"))
        List.add(RecyclerDataClass(8, "Attachments"))


        val adapter = CustomAdapter().apply {
            this.cList = List
        }
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        fragment = MainData()

        selectedLiveData.observe(viewLifecycleOwner, Observer {
            adapter.notifyDataSetChanged()
            val fm = requireActivity().supportFragmentManager
            val transaction = fm.beginTransaction()
            recyclerView.smoothScrollToPosition(selectedLiveData.value!!)
            when (it) {
                0 -> fragment = MainData()
                1 ->fragment = ProjectSiteData()
                2 -> fragment = Products()
                3 -> fragment = Machines()
                4 -> fragment = RawMaterial()
                5 -> fragment = NeededWorkers()
                6 -> fragment = AnnualUtility()
                7 -> fragment = Attachments()
            }
            transaction.replace(R.id.fragmentContainer, fragment)
            transaction.commit()
        })

        binding.ivBackBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainDataRecycler_to_industrialProjectEL)
        }
        if (lang == "English") {
            setLocal("en")
            binding.mainDataRecycler.layoutDirection = View.LAYOUT_DIRECTION_LTR
        } else if (lang == "Arabic") {
            setLocal("ar")
            binding.mainDataRecycler.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
        return view
    }

    private fun setLocal(langCode: String) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        resources.configuration.setLocale(locale)
        resources.configuration.setLayoutDirection(locale)
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
        /*val z: ZoneId = ZoneId.of("Asia/Kolkata")
        val zdt: ZonedDateTime = instant.atZone(z)
        val f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(locale)
        val timeFormat = zdt.format(f)
        Log.d("time", "setLocal:$timeFormat ")*/
        // time.text = timeFormat.toString()
        binding.tvTitle.setText(R.string.app_name)
        binding.tvLanguages.setText(R.string.languages)
    }
}

