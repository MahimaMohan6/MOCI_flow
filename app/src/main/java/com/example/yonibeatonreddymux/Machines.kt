package com.example.yonibeatonreddymux

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yonibeatonreddymux.databinding.FragmentMachinesBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class Machines : Fragment() {
    lateinit var binding: FragmentMachinesBinding
    lateinit var recyclerViewHsCode: RecyclerView
    var listHsCode = ArrayList<HSCodeDataClass>()
    lateinit var recyclerViewUnit: RecyclerView
    var listUnit = ArrayList<UnitDataClass>()
    lateinit var btnNext:TextView
    lateinit var btnBack:TextView
    var listMachines = ArrayList<AddMachineAndSpare>()
    lateinit var recyclerViewMachines: RecyclerView

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMachinesBinding.inflate(inflater, container, false)
        val view = binding.root
        //val view= inflater.inflate(R.layout.fragment_machines, container, false)
        val addView = layoutInflater.inflate(R.layout.add_machine_and_spare, null)
        btnNext=requireActivity().findViewById(R.id.btnNext)
        btnBack=requireActivity().findViewById(R.id.btnBack)
        recyclerViewMachines = binding.recyclerViewAddMachines
        recyclerViewMachines.layoutManager = LinearLayoutManager(requireContext())
        var count = 0
        val bundle = arguments
        if (bundle != null) {
            addView.findViewById<TextView>(R.id.tvProductHSCodeValue).text =
                bundle.getString("HsCode")
            addView.findViewById<TextView>(R.id.tvProductUnitValue).text = bundle.getString("Unit")
            addView.findViewById<TextView>(R.id.tvProductValueValue).text =
                bundle.getString("Value")
            addView.findViewById<TextView>(R.id.tvProductQuantityValue).text =
                bundle.getString("Quantity")
        }

        binding.ivExpandSpare.setOnClickListener {
            count += 1
            binding.cardViewMachines.visibility = View.GONE
            binding.ivExpandSpare.setImageResource(R.drawable.img_expandable_arrow)
            if (count > 1) {
                binding.cardViewMachines.visibility = View.VISIBLE
                binding.ivExpandSpare.setImageResource(R.drawable.img_up_arrow)
                count = 0
            }
        }


        listHsCode.add(HSCodeDataClass("HS1111"))
        listHsCode.add(HSCodeDataClass("HS1121"))
        listHsCode.add(HSCodeDataClass("HS1131"))
        listHsCode.add(HSCodeDataClass("HS1141"))
        listHsCode.add(HSCodeDataClass("HS1151"))
        listHsCode.add(HSCodeDataClass("HS1161"))
        listHsCode.add(HSCodeDataClass("HS1171"))
        listHsCode.add(HSCodeDataClass("HS1181"))
        listHsCode.add(HSCodeDataClass("HS1191"))

        listUnit.add(UnitDataClass("10"))
        listUnit.add(UnitDataClass("20"))
        listUnit.add(UnitDataClass("30"))
        listUnit.add(UnitDataClass("40"))
        listUnit.add(UnitDataClass("50"))
        listUnit.add(UnitDataClass("60"))
        listUnit.add(UnitDataClass("70"))
        listUnit.add(UnitDataClass("80"))
        listUnit.add(UnitDataClass("90"))
        /*if(listMachines.isNullOrEmpty()) {
            listMachines.add(
                AddMachineAndSpare(
                    "74199120",
                    "Metal Works",
                    "SQ-Meter",
                    "12 QAR",
                    "SQ-Meter"
                )
            )
        }*/
        binding.tvAdd.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
            val view = layoutInflater.inflate(R.layout.machines_materials_bottomsheet, null)
            val bottomsheetHSCode =
                BottomSheetBehavior.from(view.findViewById(R.id.cardViewHsCodeProducts))
            bottomsheetHSCode.state = BottomSheetBehavior.STATE_HIDDEN
            val bottomSheetUnit =
                BottomSheetBehavior.from(view.findViewById(R.id.cardViewUnitProducts))
            bottomSheetUnit.state = BottomSheetBehavior.STATE_HIDDEN
            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
            val btnAdd = view.findViewById<TextView>(R.id.btnAdd)
            val btnCancel = view.findViewById<TextView>(R.id.btnCancel)
            val etHSCode = view.findViewById<EditText>(R.id.etHSCode)
            val etUnit = view.findViewById<EditText>(R.id.etUnit)
            val etValue = view.findViewById<EditText>(R.id.etValue)
            val etQuantity = view.findViewById<EditText>(R.id.etQuantity)
            val tvErrorQuantity = view.findViewById<TextView>(R.id.tvErrorQuantity)
            val tvErrorValue = view.findViewById<TextView>(R.id.tvErrorValue)
            view.findViewById<TextView>(R.id.tvHSCode).setText(R.string.hs_code)
            view.findViewById<EditText>(R.id.etHSCode).setHint(R.string.hs_code)
            view.findViewById<TextView>(R.id.tvUnit).setText(R.string.unit)
            view.findViewById<EditText>(R.id.etUnit).setHint(R.string.unit)
            view.findViewById<TextView>(R.id.tvValue).setText(R.string.value)
            view.findViewById<EditText>(R.id.etValue).setHint(R.string.value)
            view.findViewById<TextView>(R.id.tvQuantity).setText(R.string.quantity)
            view.findViewById<EditText>(R.id.etQuantity).setHint(R.string.quantity)
            view.findViewById<TextView>(R.id.btnCancel).setText(R.string.cancel_btn)
            view.findViewById<TextView>(R.id.btnAdd).setText(R.string.add_btn)
            view.findViewById<TextView>(R.id.tvChooseHsCode).setText(R.string.choose_hs_code)
            view.findViewById<TextView>(R.id.tvChooseUnit).setText(R.string.choose_unit)
            recyclerViewHsCode = view.findViewById<RecyclerView>(R.id.recyclerViewHSProducts)
            recyclerViewHsCode.layoutManager = LinearLayoutManager(requireContext())
            view.findViewById<ImageView>(R.id.ivHsCodeMachine).setOnClickListener {
                val adapterHsCode = HSCodeAdapter(listHsCode)
                recyclerViewHsCode.adapter = adapterHsCode
                bottomsheetHSCode.state = BottomSheetBehavior.STATE_EXPANDED
                adapterHsCode.setOnItemClickListener(object : HSCodeAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        val etHSCode = view.findViewById<EditText>(R.id.etHSCode)
                        etHSCode.setText(listHsCode[position].hsCode)
                        bottomsheetHSCode.state = BottomSheetBehavior.STATE_HIDDEN
                    }
                })
            }

            recyclerViewUnit = view.findViewById(R.id.recyclerViewUnitProducts)
            recyclerViewUnit.layoutManager = LinearLayoutManager(requireContext())
            view.findViewById<ImageView>(R.id.ivUnitMachine).setOnClickListener {
                val adapterUnit = UnitAdapter(listUnit)
                recyclerViewUnit.adapter = adapterUnit
                bottomSheetUnit.state = BottomSheetBehavior.STATE_EXPANDED
                adapterUnit.setOnItemClickListener(object : UnitAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        val etUnit = view.findViewById<EditText>(R.id.etUnit)
                        etUnit.setText(listUnit[position].unit)
                        bottomSheetUnit.state = BottomSheetBehavior.STATE_HIDDEN
                    }

                })
            }

            btnAdd.setOnClickListener {
                if (!etValue.text.isNullOrBlank() && !etQuantity.text.isNullOrBlank()) {
                    tvErrorQuantity.text = ""
                    tvErrorValue.text = ""
                    addView.findViewById<TextView>(R.id.tvProductHSCodeValue).text =
                        etHSCode.text.toString()
                    addView.findViewById<TextView>(R.id.tvProductUnitValue).text =
                        etUnit.text.toString()
                    addView.findViewById<TextView>(R.id.tvProductValueValue).text =
                        etValue.text.toString()
                    addView.findViewById<TextView>(R.id.tvProductQuantityValue).text =
                        etQuantity.text.toString()
                    listMachines.add(
                        AddMachineAndSpare(
                            "${view.findViewById<EditText>(R.id.etHSCode).text.toString()}",
                            "Metal Works",
                            "${view.findViewById<EditText>(R.id.etUnit).text}",
                            "${view.findViewById<EditText>(R.id.etValue).text}",
                            "${view.findViewById<EditText>(R.id.etQuantity).text}"
                        )
                    )
                    val adapterMachines = AddMachineAndSpareAdapter(listMachines)
                    recyclerViewMachines.adapter = adapterMachines
                    dialog.cancel()
                } else {
                    tvErrorQuantity.setText(R.string.required)
                    tvErrorValue.setText(R.string.required)
                }
            }
            btnCancel.setOnClickListener {
                dialog.cancel()
            }
        }
        tabSelect.add("3")

        btnNext.setOnClickListener {
            val currentIndex = selectedLiveData.value
            val nextIndex = currentIndex!! + 1
            selectedLiveData.value = nextIndex
            Session.machineList = listMachines
        }
        btnBack.setOnClickListener {
            val currentIndex = selectedLiveData.value
            val previousIndex = currentIndex!! - 1
            selectedLiveData.value = previousIndex
        }
        binding.tvMachineEquipments.setText(R.string.machine_and_the_equipment)
        binding.tvSpareParts.setText(R.string.spare_parts)
        addView.findViewById<TextView>(R.id.tvProductHSCode).setText(R.string.hs_code)
        addView.findViewById<TextView>(R.id.tvProductNameValue).setText(R.string.metal_works)
        addView.findViewById<TextView>(R.id.tvProductName).setText(R.string.material_name)
        addView.findViewById<TextView>(R.id.tvProductUnit).setText(R.string.unit)
        addView.findViewById<TextView>(R.id.tvProductValue).setText(R.string.value)
        addView.findViewById<TextView>(R.id.tvProductQuantity).setText(R.string.quantity)
        btnBack.setText(R.string.back_btn)
        btnNext.setText(R.string.next_btn)
        binding.tvAdd.setText(R.string.add_btn)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listMachines = Session.machineList
        val adapterMachines = AddMachineAndSpareAdapter(listMachines)
        recyclerViewMachines.adapter = adapterMachines
        super.onViewCreated(view, savedInstanceState)
    }
}