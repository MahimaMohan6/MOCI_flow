package com.example.yonibeatonreddymux

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yonibeatonreddymux.databinding.FragmentProductsBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList


@Suppress("DEPRECATION")
class Products : Fragment() {
    lateinit var binding: FragmentProductsBinding
    lateinit var dialog: BottomSheetDialog
    lateinit var recyclerViewHsCode: RecyclerView
    var listHsCode = ArrayList<HSCodeDataClass>()
    lateinit var recyclerviewAddProduct: RecyclerView
    var listAddProduct = kotlin.collections.ArrayList<AddProductDataClass>()
    lateinit var recyclerViewUnit: RecyclerView
    var listUnit = ArrayList<UnitDataClass>()

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        //val view= inflater.inflate(R.layout.fragment_products, container, false)
        val viewSheet = layoutInflater.inflate(R.layout.products_bottomsheet, null)
        var count = 0
        recyclerviewAddProduct = binding.recyclerViewAddProducts
        recyclerviewAddProduct.layoutManager = LinearLayoutManager(requireContext())
        binding.tvAddProduct.setOnClickListener {
            count += 1
            AddBottomSheet()
            val addview = layoutInflater.inflate(R.layout.add_products, null)
            val bundle = Bundle()
            val tvHsCode =
                addview.findViewById<TextView>(R.id.tvProductHSCodeValue).text.toString()
            bundle.putString("HsCode", tvHsCode)
            bundle.putString(
                "Unit",
                addview.findViewById<TextView>(R.id.tvProductUnitValue).text.toString()
            )
            bundle.putString(
                "Value",
                addview.findViewById<TextView>(R.id.tvProductSellingPriceValue).text.toString()
            )
            bundle.putString(
                "Quantity",
                addview.findViewById<TextView>(R.id.tvProductUnitValue).text.toString()
            )
            Machines().arguments = bundle
        }
        binding.tvNextAddProduct.setOnClickListener {
            AddBottomSheet()
        }
        binding.btnConstraint.setOnApplyWindowInsetsListener { v, windowInsets ->
            val insets=windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<MarginLayoutParams> {bottomMargin=insets.bottom  }
            windowInsets
        }
        binding.btnNext.setOnClickListener {
            if (!listAddProduct.isNullOrEmpty()) {
                val currentIndex = selectedLiveData.value
                val nextIndex = currentIndex!! + 1
                selectedLiveData.value = nextIndex
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainer, Machines())
                transaction.commit()
                /*Session.productAdapter=AddProductAdapter().apply {
                    this.cList=listAddProduct
                }*/
                Session.productlist = listAddProduct
                tabSelect.add("2")
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.add_atleast_one_products,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
        binding.btnBack.setOnClickListener {
            val currentIndex = selectedLiveData.value
            val previousIndex = currentIndex!! - 1
            selectedLiveData.value = previousIndex
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
        val addView = layoutInflater.inflate(R.layout.add_products, null)
        var alert: AlertDialog
        addView.findViewById<ImageView>(R.id.ivDelete).setOnClickListener {
            alert = alertBuilder.create()
            alert.show()
        }
        if (lang == "English") {
            setLocal("en")
            binding.constraintProducts.layoutDirection = View.LAYOUT_DIRECTION_LTR
        } else if (lang == "Arabic") {
            setLocal("ar")
            binding.constraintProducts.layoutDirection = View.LAYOUT_DIRECTION_RTL
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
        binding.cardViewProducts.visibility = View.VISIBLE
        binding.nextAdd.visibility = View.VISIBLE
        listAddProduct = Session.productlist
        val adapterAddProduct = AddProductAdapter().apply {
            this.cList = listAddProduct
        }
        recyclerviewAddProduct.adapter = adapterAddProduct
        if (!listAddProduct.isNullOrEmpty()) {
            binding.constraintAddProduct.visibility = View.INVISIBLE
        }
        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    private fun setLocal(langCode: String) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        val addProductView = layoutInflater.inflate(R.layout.add_products, null)
        resources.configuration.setLocale(locale)
        resources.configuration.setLayoutDirection(locale)
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
        binding.tvAddProduct.setText(R.string.add_products)
        binding.tvNextAddProduct.setText(R.string.add_products)
        addProductView.findViewById<TextView>(R.id.tvProductHSCode).setText(R.string.hs_code)
        addProductView.findViewById<TextView>(R.id.tvProductName).setText(R.string.product_name)
        addProductView.findViewById<TextView>(R.id.tvProductNameValue).setText(R.string.metal_works)
        addProductView.findViewById<TextView>(R.id.tvProductUnit).setText(R.string.unit)
        addProductView.findViewById<TextView>(R.id.tvProductSellingPrice)
            .setText(R.string.selling_price_QAR)
        addProductView.findViewById<TextView>(R.id.tvAnnualProductionCapacity)
            .setText(R.string.annual_production_capacity)
        addProductView.findViewById<TextView>(R.id.tvAnnualExportQuantity)
            .setText(R.string.annual_export_quantity)
        if (lang == "English") {
            addProductView.findViewById<TextView>(R.id.tvProductHSCodeValue).layoutDirection =
                View.LAYOUT_DIRECTION_LTR
        } else if (lang == "Arabic") {
            addProductView.findViewById<TextView>(R.id.tvProductHSCodeValue).layoutDirection =
                View.LAYOUT_DIRECTION_RTL
        }
        binding.btnNext.setText(R.string.next_btn)
        binding.btnBack.setText(R.string.back_btn)
    }

    @SuppressLint("MissingInflatedId")
    private fun AddBottomSheet() {
        val viewSheet = layoutInflater.inflate(R.layout.products_bottomsheet, null)
        val bottomsheetHSCode =
            BottomSheetBehavior.from(viewSheet.findViewById(R.id.cardViewHsCodeProducts))
        bottomsheetHSCode.state = BottomSheetBehavior.STATE_HIDDEN
        val bottomSheetUnit =
            BottomSheetBehavior.from(viewSheet.findViewById(R.id.cardViewUnitProducts))
        bottomSheetUnit.state = BottomSheetBehavior.STATE_HIDDEN
        dialog.setCancelable(false)
        dialog.setContentView(viewSheet)
        dialog.show()
        val btnAdd = viewSheet.findViewById<TextView>(R.id.btnAdd)
        val btnCancel = viewSheet.findViewById<TextView>(R.id.btnCancel)
        val etHSCode = viewSheet.findViewById<EditText>(R.id.etHSCode)

        val etUnit = viewSheet.findViewById<EditText>(R.id.etUnit)
        val etSellingPrice = viewSheet.findViewById<EditText>(R.id.etSellingPrice)
        val etAnnualProduction = viewSheet.findViewById<EditText>(R.id.etAnnualProduction)
        val etAnnualExport = viewSheet.findViewById<EditText>(R.id.etAnnualExportQuantity)
        val tvErrorAnnualProduction = viewSheet.findViewById<TextView>(R.id.tvErrorAnnualProduction)
        viewSheet.findViewById<TextView>(R.id.tvHSCode).setText(R.string.hs_code)
        viewSheet.findViewById<EditText>(R.id.etHSCode).setHint(R.string.hs_code)
        viewSheet.findViewById<TextView>(R.id.tvUnit).setText(R.string.unit)
        viewSheet.findViewById<EditText>(R.id.etUnit).setHint(R.string.unit)
        viewSheet.findViewById<TextView>(R.id.tvSellingPrice).setText(R.string.selling_price_QAR)
        viewSheet.findViewById<EditText>(R.id.etSellingPrice).setHint(R.string.selling_price_QAR)
        viewSheet.findViewById<TextView>(R.id.tvAnnualProductionCapacity)
            .setText(R.string.annual_production_capacity)
        viewSheet.findViewById<TextView>(R.id.tvDesignCapacity).setText(R.string.design_capacity)
        viewSheet.findViewById<EditText>(R.id.etAnnualProduction)
            .setHint(R.string.annual_production_capacity)
        viewSheet.findViewById<TextView>(R.id.tvAnnualExportQuantity)
            .setText(R.string.annual_export_quantity)
        viewSheet.findViewById<EditText>(R.id.etAnnualExportQuantity)
            .setHint(R.string.annual_export_quantity)
        viewSheet.findViewById<TextView>(R.id.btnAdd).setHint(R.string.add_btn)
        viewSheet.findViewById<TextView>(R.id.btnCancel).setHint(R.string.cancel_btn)
        viewSheet.findViewById<TextView>(R.id.tvChooseHsCode).setText(R.string.choose_hs_code)
        viewSheet.findViewById<TextView>(R.id.tvChooseUnit).setText(R.string.choose_unit)
        recyclerViewHsCode = viewSheet.findViewById(R.id.recyclerViewHSProducts)
        recyclerViewHsCode.layoutManager = LinearLayoutManager(requireContext())
        viewSheet.findViewById<ImageView>(R.id.ivHsProducts).setOnClickListener {
            val adapterHsCode = HSCodeAdapter(listHsCode)
            recyclerViewHsCode.adapter = adapterHsCode
            bottomsheetHSCode.state = BottomSheetBehavior.STATE_EXPANDED
            adapterHsCode.setOnItemClickListener(object : HSCodeAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    val etHSCode = viewSheet.findViewById<EditText>(R.id.etHSCode)
                    etHSCode.setText(listHsCode[position].hsCode)
                    bottomsheetHSCode.state = BottomSheetBehavior.STATE_HIDDEN
                }
            })
        }

        recyclerViewUnit = viewSheet.findViewById(R.id.recyclerViewUnitProducts)
        recyclerViewUnit.layoutManager = LinearLayoutManager(requireContext())
        viewSheet.findViewById<ImageView>(R.id.ivUnitProducts).setOnClickListener {
            val adapterUnit = UnitAdapter(listUnit)
            recyclerViewUnit.adapter = adapterUnit
            bottomSheetUnit.state = BottomSheetBehavior.STATE_EXPANDED
            adapterUnit.setOnItemClickListener(object : UnitAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    val etUnit = viewSheet.findViewById<EditText>(R.id.etUnit)
                    etUnit.setText(listUnit[position].unit)
                    bottomSheetUnit.state = BottomSheetBehavior.STATE_HIDDEN
                }

            })
        }

        btnAdd.setOnClickListener {
            if (!etAnnualProduction.text.isNullOrBlank()) {
                tvErrorAnnualProduction.text = ""
                binding.cardViewProducts.visibility = View.VISIBLE
                binding.nextAdd.visibility = View.VISIBLE
                dialog.cancel()
                binding.constraintAddProduct.visibility = View.INVISIBLE
                listAddProduct.add(
                    AddProductDataClass(
                        "${viewSheet.findViewById<EditText>(R.id.etHSCode).text.toString()}",
                        "Metal Works",
                        "${viewSheet.findViewById<EditText>(R.id.etUnit).text.toString()}",
                        "${viewSheet.findViewById<EditText>(R.id.etSellingPrice).text.toString()}",
                        "${viewSheet.findViewById<EditText>(R.id.etAnnualProduction).text.toString()}",
                        "${viewSheet.findViewById<EditText>(R.id.etAnnualExportQuantity).text.toString()}"
                    )
                )
                val adapterAddProduct = AddProductAdapter().apply {
                    this.cList = listAddProduct
                }
                recyclerviewAddProduct.adapter = adapterAddProduct
                /*requireActivity().findViewById<TextView>(R.id.tvProductHSCodeValue).text = etHSCode.text.toString()
                    requireActivity().findViewById<TextView>(R.id.tvProductUnitValue).text = etUnit.text
                    requireActivity().findViewById<TextView>(R.id.tvProductSellingPriceValue).text = etSellingPrice.text
                    requireActivity().findViewById<TextView>(R.id.tvAnnualProductionCapacityValue).text = etAnnualProduction.text
                    requireActivity().findViewById<TextView>(R.id.tvAnnualExportQuantityValue).text = etAnnualExport.text*/
            } else {
                tvErrorAnnualProduction.setText(R.string.required)
            }
        }
        btnCancel.setOnClickListener {
            dialog.cancel()
        }
    }

}