package com.example.yonibeatonreddymux

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
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
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yonibeatonreddymux.databinding.FragmentRawMaterialBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class RawMaterial : Fragment() {
    lateinit var binding: FragmentRawMaterialBinding
    lateinit var recyclerViewHsCode: RecyclerView
    var listHsCode = ArrayList<HSCodeDataClass>()
    lateinit var recyclerviewRawMaterial: RecyclerView
    var listRawMaterial = ArrayList<AddRawMaterialDataClass>()
    lateinit var recyclerViewUnit: RecyclerView
    var listUnit = ArrayList<UnitDataClass>()
    var adapterAddRawMaterial =AddRawMaterialAdapter(listRawMaterial)

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRawMaterialBinding.inflate(inflater, container, false)
        val view = binding.root
        val addView = layoutInflater.inflate(R.layout.add_rawmaterial, null)
        recyclerviewRawMaterial = binding.recyclerViewAddRawMaterial
        recyclerviewRawMaterial.layoutManager = LinearLayoutManager(requireContext())
        adapterAddRawMaterial = AddRawMaterialAdapter(listRawMaterial)
        binding.tvAddProduct.setOnClickListener {
            AddBottomSheet()
        }

        binding.tvNextAddProduct.setOnClickListener {
            AddBottomSheet()
        }
        adapterAddRawMaterial.setOnItemClickListener(object :
            AddRawMaterialAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                EditRawMaterials(position)
            }
        })
        binding.btnNext.setOnClickListener {
            if (!listRawMaterial.isNullOrEmpty()) {
                val currentIndex = selectedLiveData.value
                val nextIndex = currentIndex!! + 1
                selectedLiveData.value = nextIndex
                Session.rawMaterialList=listRawMaterial
                tabSelect.add("4")
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.add_atleast_one_raw_material,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        if (lang == "English") {
            setLocal("en")
            binding.constraintRawMaterial.layoutDirection = View.LAYOUT_DIRECTION_LTR
        } else if (lang == "Arabic") {
            setLocal("ar")
            binding.constraintRawMaterial.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }

        binding.btnBack.setOnClickListener {
            val currentIndex = selectedLiveData.value
            val previousIndex = currentIndex!! - 1
            selectedLiveData.value = previousIndex
        }

        binding.btnConstraint.setOnApplyWindowInsetsListener { v, windowInsets ->
            val insets=windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<MarginLayoutParams> {bottomMargin=insets.bottom  }
            windowInsets
        }
        val alertBuilder = AlertDialog.Builder(requireContext())
        alertBuilder.setMessage(R.string.do_you_want_to_delete)
        alertBuilder.setTitle(R.string.delete)
        alertBuilder.setCancelable(false)
        alertBuilder.setNegativeButton(
            R.string.cancel_btn,
            object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog!!.cancel()
                }
            })
        alertBuilder.setPositiveButton(R.string.delete, object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                requireActivity().findViewById<ConstraintLayout>(R.id.constraintDetails).visibility =
                    View.GONE
            }

        })
        var alert: AlertDialog
        addView.findViewById<ImageView>(R.id.ivDelete).setOnClickListener {
            alert = alertBuilder.create()
            alert.show()
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


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.cardViewRaw.visibility = View.VISIBLE
        binding.nextAdd.visibility = View.VISIBLE
        listRawMaterial.addAll(Session.rawMaterialList)
        recyclerviewRawMaterial.adapter = adapterAddRawMaterial
        if(!listRawMaterial.isNullOrEmpty()){
            binding.constraintAddProduct.visibility = View.INVISIBLE
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun EditRawMaterials(position: Int) {
        val dialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        val view = layoutInflater.inflate(R.layout.rawmaterial_bottomsheet, null)
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
        val etValue = view.findViewById<EditText>(R.id.etValue)
        val etQuantity = view.findViewById<EditText>(R.id.etQuantity)
        val etHSCode = view.findViewById<EditText>(R.id.etHSCode)
        val etUnit = view.findViewById<EditText>(R.id.etUnit)
        val tvErrorQuantity = view.findViewById<TextView>(R.id.tvErrorQuantity)
        val tvErrorValue = view.findViewById<TextView>(R.id.tvErrorValue)
        view.findViewById<TextView>(R.id.tvHSCode).setText(R.string.hs_code)
        view.findViewById<TextView>(R.id.tvUnit).setText(R.string.unit)
        view.findViewById<TextView>(R.id.tvValue).setText(R.string.value)
        view.findViewById<TextView>(R.id.tvQuantity).setText(R.string.quantity)
        view.findViewById<EditText>(R.id.etHSCode).setHint(R.string.hs_code)
        view.findViewById<TextView>(R.id.btnAdd).setText(R.string.add_btn)
        view.findViewById<TextView>(R.id.btnCancel).setText(R.string.cancel_btn)
        view.findViewById<TextView>(R.id.tvChooseHsCode).setText(R.string.choose_hs_code)
        view.findViewById<TextView>(R.id.tvChooseUnit).setText(R.string.choose_unit)
        etUnit.setHint(R.string.unit)
        etValue.setHint(R.string.value)
        etQuantity.setHint(R.string.quantity)
        etHSCode.setText(listRawMaterial[position].hsCode.toString())
        etUnit.setText(listRawMaterial[position].unit.toString())
        etValue.setText(listRawMaterial[position].value.toString())
        etQuantity.setText(listRawMaterial[position].Quantity.toString())
        // etUnit.setText(binding.tvProductUnit.text.toString())
        //etValue.setText(binding.tvProductValue.text.toString())
        //etQuantity.setText(binding.tvProductQuantity.text.toString())
        recyclerViewHsCode = view.findViewById(R.id.recyclerViewHSProducts)
        recyclerViewHsCode.layoutManager = LinearLayoutManager(requireContext())
        view.findViewById<ImageView>(R.id.ivHsProducts).setOnClickListener {
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
        view.findViewById<ImageView>(R.id.ivUnitProducts).setOnClickListener {
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
                binding.cardViewRaw.visibility = View.VISIBLE
                binding.nextAdd.visibility = View.VISIBLE
                binding.constraintAddProduct.visibility = View.INVISIBLE
                listRawMaterial[position].run {
                    hsCode = view.findViewById<EditText>(R.id.etHSCode).text.toString()
                    unit=view.findViewById<EditText>(R.id.etUnit).text.toString()
                    value=view.findViewById<EditText>(R.id.etValue).text.toString()
                    Quantity=view.findViewById<EditText>(R.id.etQuantity).text.toString()
                }
                adapterAddRawMaterial.notifyDataSetChanged()
                //bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                dialog.cancel()
                /*requireActivity().findViewById<TextView>(R.id.tvProductHSCodeValue).text = etHSCode.text.toString()
                requireActivity().findViewById<TextView>(R.id.tvProductUnitValue).text = etUnit.text
                requireActivity().findViewById<TextView>(R.id.tvProductValueValue).text = etValue.text
                requireActivity().findViewById<TextView>(R.id.tvProductQuantityValue).text = etQuantity.text*/
            } else {
                tvErrorValue.setText(R.string.required)
                tvErrorQuantity.setText(R.string.required)
            }
        }
        btnCancel.setOnClickListener {
            dialog.cancel()
        }
    }

    private fun setLocal(langCode: String) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        val addProductView = layoutInflater.inflate(R.layout.add_rawmaterial, null)
        resources.configuration.setLocale(locale)
        resources.configuration.setLayoutDirection(locale)
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
        binding.tvAddProduct.setText(R.string.add_btn)
        binding.tvNextAddProduct.setText(R.string.add_btn)
        binding.btnNext.setText(R.string.next_btn)
        binding.btnBack.setText(R.string.back_btn)
        addProductView.findViewById<TextView>(R.id.tvProductHSCode).setText(R.string.hs_code)
        addProductView.findViewById<TextView>(R.id.tvProductName).setText(R.string.material_name)
        addProductView.findViewById<TextView>(R.id.tvProductNameValue).setText(R.string.metal_works)
        addProductView.findViewById<TextView>(R.id.tvProductUnit).setText(R.string.unit)
        addProductView.findViewById<TextView>(R.id.tvProductValue).setText(R.string.value)
        addProductView.findViewById<TextView>(R.id.tvProductQuantity).setText(R.string.quantity)
    }

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    private fun AddBottomSheet() {
        val dialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        val view = layoutInflater.inflate(R.layout.rawmaterial_bottomsheet, null)
        val addView = layoutInflater.inflate(R.layout.add_rawmaterial, null)
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
        val etValue = view.findViewById<EditText>(R.id.etValue)
        val etQuantity = view.findViewById<EditText>(R.id.etQuantity)
        val etHSCode = view.findViewById<EditText>(R.id.etHSCode)
        val etUnit = view.findViewById<EditText>(R.id.etUnit)
        val tvErrorQuantity = view.findViewById<TextView>(R.id.tvErrorQuantity)
        val tvErrorValue = view.findViewById<TextView>(R.id.tvErrorValue)
        view.findViewById<TextView>(R.id.tvHSCode).setText(R.string.hs_code)
        view.findViewById<TextView>(R.id.tvUnit).setText(R.string.unit)
        view.findViewById<TextView>(R.id.tvValue).setText(R.string.value)
        view.findViewById<TextView>(R.id.tvQuantity).setText(R.string.quantity)
        view.findViewById<EditText>(R.id.etHSCode).setHint(R.string.hs_code)
        view.findViewById<TextView>(R.id.btnAdd).setText(R.string.add_btn)
        view.findViewById<TextView>(R.id.btnCancel).setText(R.string.cancel_btn)
        view.findViewById<TextView>(R.id.tvChooseHsCode).setText(R.string.choose_hs_code)
        view.findViewById<TextView>(R.id.tvChooseUnit).setText(R.string.choose_unit)
        etUnit.setHint(R.string.unit)
        etValue.setHint(R.string.value)
        etQuantity.setHint(R.string.quantity)
        etHSCode.setText(addView.findViewById<TextView>(R.id.tvProductHSCodeValue).text.toString())
        // etUnit.setText(binding.tvProductUnit.text.toString())
        //etValue.setText(binding.tvProductValue.text.toString())
        //etQuantity.setText(binding.tvProductQuantity.text.toString())
        recyclerViewHsCode = view.findViewById(R.id.recyclerViewHSProducts)
        recyclerViewHsCode.layoutManager = LinearLayoutManager(requireContext())
        view.findViewById<ImageView>(R.id.ivHsProducts).setOnClickListener {
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
        view.findViewById<ImageView>(R.id.ivUnitProducts).setOnClickListener {
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
                binding.cardViewRaw.visibility = View.VISIBLE
                binding.nextAdd.visibility = View.VISIBLE
                binding.constraintAddProduct.visibility = View.INVISIBLE
                listRawMaterial.add(
                    AddRawMaterialDataClass(
                        "${view.findViewById<EditText>(R.id.etHSCode).text.toString()}",
                        "Metal Works",
                        "${view.findViewById<EditText>(R.id.etUnit).text.toString()}",
                        "${view.findViewById<EditText>(R.id.etValue).text.toString()}",
                        "${view.findViewById<EditText>(R.id.etQuantity).text.toString()}"
                    )
                )
                recyclerviewRawMaterial.adapter = adapterAddRawMaterial

                //bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                dialog.cancel()
                /*requireActivity().findViewById<TextView>(R.id.tvProductHSCodeValue).text = etHSCode.text.toString()
                requireActivity().findViewById<TextView>(R.id.tvProductUnitValue).text = etUnit.text
                requireActivity().findViewById<TextView>(R.id.tvProductValueValue).text = etValue.text
                requireActivity().findViewById<TextView>(R.id.tvProductQuantityValue).text = etQuantity.text*/
            } else {
                tvErrorValue.setText(R.string.required)
                tvErrorQuantity.setText(R.string.required)
            }
        }
        btnCancel.setOnClickListener {
            dialog.cancel()
        }
    }

}