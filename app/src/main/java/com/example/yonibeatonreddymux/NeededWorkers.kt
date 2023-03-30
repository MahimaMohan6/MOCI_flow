package com.example.yonibeatonreddymux

import NationalityAdapter
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
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yonibeatonreddymux.databinding.FragmentNeededWorkersBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.w3c.dom.Text

class NeededWorkers : Fragment() {
    lateinit var binding: FragmentNeededWorkersBinding
    lateinit var recyclerViewNation: RecyclerView
    lateinit var btnNext:TextView
    lateinit var btnBack:TextView
    var listNation = ArrayList<NationalityDataClass>()
    lateinit var recyclerviewAddWorkers: RecyclerView
    var listAddWorkers = ArrayList<AddNeededWorkersDataClass>()
    lateinit var recyclerViewJobTitle: RecyclerView
    var listJob = ArrayList<JobTitleDataClass>()
    lateinit var adapterNeededWorkers: AddNeededWorkersAdapter

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNeededWorkersBinding.inflate(inflater, container, false)
        val view = binding.root
        val addView = layoutInflater.inflate(R.layout.add_neededworkers, null)
        btnBack=requireActivity().findViewById(R.id.btnBack)
        btnNext=requireActivity().findViewById(R.id.btnNext)
        //return inflater.inflate(R.layout.fragment_needed_workers, container, false)
        recyclerviewAddWorkers = binding.recyclerViewAddNeededWorkers
        recyclerviewAddWorkers.layoutManager = LinearLayoutManager(requireContext())
        adapterNeededWorkers = AddNeededWorkersAdapter().apply {
            this.cList = listAddWorkers
        }

        if (lang == "English") {
            listNation.add(NationalityDataClass("India"))
            listNation.add(NationalityDataClass("China"))
            listNation.add(NationalityDataClass("America"))
            listNation.add(NationalityDataClass("Pakistan"))
            listNation.add(NationalityDataClass("Iran"))
            listNation.add(NationalityDataClass("Qatar"))
            listNation.add(NationalityDataClass("Canada"))

            listJob.add(JobTitleDataClass("Doctor"))
            listJob.add(JobTitleDataClass("Software Engineer"))
            listJob.add(JobTitleDataClass("Hardware Engineer"))
            listJob.add(JobTitleDataClass("Developer"))
            listJob.add(JobTitleDataClass("Teacher"))
            listJob.add(JobTitleDataClass("Nurse"))
            listJob.add(JobTitleDataClass("Police"))
        }
        if (lang == "Arabic") {
            listNation.add(NationalityDataClass("الهند"))
            listNation.add(NationalityDataClass("الصين"))
            listNation.add(NationalityDataClass("أمريكا"))
            listNation.add(NationalityDataClass("باكستان"))
            listNation.add(NationalityDataClass("إيران"))
            listNation.add(NationalityDataClass("دولة قطر"))
            listNation.add(NationalityDataClass("كندا"))

            listJob.add(JobTitleDataClass("طبيب"))
            listJob.add(JobTitleDataClass("مهندس برمجيات"))
            listJob.add(JobTitleDataClass("مهندس الأجهزة"))
            listJob.add(JobTitleDataClass("مطور"))
            listJob.add(JobTitleDataClass("مدرس"))
            listJob.add(JobTitleDataClass("ممرضة"))
            listJob.add(JobTitleDataClass("شرطة"))
        }

        binding.tvAddProduct.setOnClickListener {
            AddBottomSheet()
        }
        binding.tvNextAddProduct.setOnClickListener {
            AddBottomSheet()
        }
        adapterNeededWorkers.setOnItemClickListener(object :
            AddNeededWorkersAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                EditNeededWorkers(position)
            }

        })
        btnNext.setOnClickListener {
            if (!listAddWorkers.isNullOrEmpty()) {
                val currentIndex = selectedLiveData.value
                val nextIndex = currentIndex!! + 1
                selectedLiveData.value = nextIndex
                Session.neededWorkersList = listAddWorkers
                tabSelect.add("5")
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.add_atleast_one_worker,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
        addView.findViewById<ImageView>(R.id.ivEdit).setOnClickListener {
            AddBottomSheet()
        }
        btnBack.setOnClickListener {
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
                addView.findViewById<ConstraintLayout>(R.id.constraintDetails).visibility =
                    View.GONE
            }

        })
        var alert: AlertDialog
        addView.findViewById<ImageView>(R.id.ivDelete).setOnClickListener {
            alert = alertBuilder.create()
            alert.show()
        }
        binding.tvAddProduct.setText(R.string.add_btn)
        binding.tvNextAddProduct.setText(R.string.add_btn)
        addView.findViewById<TextView>(R.id.tvNationality).setText(R.string.owner_nationality)
        addView.findViewById<TextView>(R.id.tvJobTitle).setText(R.string.job_title)
        addView.findViewById<TextView>(R.id.tvQuantity).setText(R.string.quantity)
        btnNext.setText(R.string.next_btn)
        btnBack.setText(R.string.back_btn)
        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardViewNeededWorkers.visibility = View.VISIBLE
        binding.nextAdd.visibility = View.VISIBLE
        listAddWorkers.addAll(Session.neededWorkersList)
        recyclerviewAddWorkers.adapter = adapterNeededWorkers
        if(!listAddWorkers.isNullOrEmpty()){
            binding.constraintAddProduct.visibility = View.INVISIBLE
        }

    }



    private fun EditNeededWorkers(position: Int) {
        val addView = layoutInflater.inflate(R.layout.add_neededworkers, null)
        val dialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        val view = layoutInflater.inflate(R.layout.needed_workers_bottomsheet, null)
        val bottomsheetNation =
            BottomSheetBehavior.from(view.findViewById(R.id.cardViewNation))
        bottomsheetNation.state = BottomSheetBehavior.STATE_HIDDEN
        val bottomsheetJob =
            BottomSheetBehavior.from(view.findViewById(R.id.cardViewJobTitle))
        bottomsheetJob.state = BottomSheetBehavior.STATE_HIDDEN
        binding.fragmentContainerProducts.setOnClickListener {
            dialog.cancel()
        }
        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()
        val btnAdd = view.findViewById<TextView>(R.id.btnAdd)
        val btnCancel = view.findViewById<TextView>(R.id.btnCancel)
        val etNation = view.findViewById<EditText>(R.id.etNationality)
        val etJobTitle = view.findViewById<EditText>(R.id.etJobTitles)
        val etQuantitySheet = view.findViewById<EditText>(R.id.etQuantitySheet)
        val etQuantity = view.findViewById<EditText>(R.id.etQuantity)
        val tvErrorQuantity = view.findViewById<TextView>(R.id.tvErrorQuantity)
        val tvErrorQuantitySheet = view.findViewById<TextView>(R.id.tvErrorQuantitySheet)
        val tvErrorNation = view.findViewById<TextView>(R.id.tvErrorNation)
        val tvErrorJobtitle = view.findViewById<TextView>(R.id.tvErrorJobtitle)

        view.findViewById<TextView>(R.id.tvNationality).setText(R.string.owner_nationality)
        view.findViewById<EditText>(R.id.etNationality).setHint(R.string.owner_nationality)
        view.findViewById<TextView>(R.id.tvJobTitles).setText(R.string.job_title)
        view.findViewById<EditText>(R.id.etJobTitles).setHint(R.string.job_title)
        view.findViewById<TextView>(R.id.tvQuantitySheet).setText(R.string.quantity)
        view.findViewById<EditText>(R.id.etQuantitySheet).setHint(R.string.quantity)
        view.findViewById<TextView>(R.id.tvAnnualSalary).setText(R.string.annual_salaries_QAR)
        view.findViewById<EditText>(R.id.etQuantity).setHint(R.string.quantity)
        view.findViewById<TextView>(R.id.btnCancel).setText(R.string.cancel_btn)
        view.findViewById<TextView>(R.id.btnAdd).setText(R.string.add_btn)
        view.findViewById<TextView>(R.id.chooseNation).setText(R.string.choose_nation)
        view.findViewById<TextView>(R.id.chooseJobTitle).setText(R.string.choose_job_title)
        etNation.setText(listAddWorkers[position].nationality.toString())
        etJobTitle.setText(listAddWorkers[position].jobTitle.toString())
        etQuantitySheet.setText(listAddWorkers[position].quantity.toString())
        etQuantity.setText(listAddWorkers[position].annualSalary.toString())

        recyclerViewNation = view.findViewById(R.id.recyclerNation)
        recyclerViewNation.layoutManager = LinearLayoutManager(requireContext())
        view.findViewById<ImageView>(R.id.ivNation).setOnClickListener {
            val adapterNation = NationalityAdapter(listNation)
            recyclerViewNation.adapter = adapterNation
            bottomsheetNation.state = BottomSheetBehavior.STATE_EXPANDED
            adapterNation.setOnItemClickListener(object : NationalityAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    val etNation = view.findViewById<EditText>(R.id.etNationality)
                    etNation.setText(listNation[position].nation)
                    bottomsheetNation.state = BottomSheetBehavior.STATE_HIDDEN
                }
            })
        }

        recyclerViewJobTitle = view.findViewById(R.id.recyclerViewJobTitle)
        recyclerViewJobTitle.layoutManager = LinearLayoutManager(requireContext())
        view.findViewById<ImageView>(R.id.ivJobTitle).setOnClickListener {
            val adapterJob = JobTitleAdapter(listJob)
            recyclerViewJobTitle.adapter = adapterJob
            bottomsheetJob.state = BottomSheetBehavior.STATE_EXPANDED
            adapterJob.setOnItemClickListener(object : JobTitleAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    val etJob = view.findViewById<EditText>(R.id.etJobTitles)
                    etJob.setText(listJob[position].job)
                    bottomsheetJob.state = BottomSheetBehavior.STATE_HIDDEN
                }
            })
        }


        btnAdd.setOnClickListener {
            if (!etNation.text.isNullOrBlank() && !etJobTitle.text.isNullOrBlank() && !etQuantitySheet.text.isNullOrBlank() && !etQuantity.text.isNullOrBlank()) {
                tvErrorQuantity.text = ""
                tvErrorQuantitySheet.text = ""
                tvErrorNation.text = ""
                tvErrorJobtitle.text = ""
                binding.cardViewNeededWorkers.visibility = View.VISIBLE
                binding.nextAdd.visibility = View.VISIBLE
                binding.constraintAddProduct.visibility = View.INVISIBLE
                listAddWorkers[position].run {
                    nationality = view.findViewById<EditText>(R.id.etNationality).text.toString()
                    jobTitle = view.findViewById<EditText>(R.id.etJobTitles).text.toString()
                    quantity = view.findViewById<EditText>(R.id.etQuantitySheet).text.toString()
                    annualSalary = view.findViewById<EditText>(R.id.etQuantity).text.toString()
                }
                adapterNeededWorkers.notifyDataSetChanged()
                dialog.cancel()

                /*binding.tvNationalityValue.text = etNation.text.toString()
                binding.tvJobTitleValue.text = etJobTitle.text.toString()
                binding.tvQuantityValue.text = etQuantitySheet.text
                binding.tvAnnualSalary.text = etQuantity.text*/
            } else {
                tvErrorQuantitySheet.setText(R.string.required)
                tvErrorQuantity.setText(R.string.required)
                tvErrorNation.setText(R.string.required)
                tvErrorJobtitle.setText(R.string.required)
            }
        }
        btnCancel.setOnClickListener {
            dialog.cancel()
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun AddBottomSheet() {
        val addView = layoutInflater.inflate(R.layout.add_neededworkers, null)
        val dialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        val view = layoutInflater.inflate(R.layout.needed_workers_bottomsheet, null)
        val bottomsheetNation =
            BottomSheetBehavior.from(view.findViewById(R.id.cardViewNation))
        bottomsheetNation.state = BottomSheetBehavior.STATE_HIDDEN
        val bottomsheetJob =
            BottomSheetBehavior.from(view.findViewById(R.id.cardViewJobTitle))
        bottomsheetJob.state = BottomSheetBehavior.STATE_HIDDEN
        binding.fragmentContainerProducts.setOnClickListener {
            dialog.cancel()
        }
        dialog.setCancelable(false)
        dialog.setContentView(view)
        dialog.show()
        val btnAdd = view.findViewById<TextView>(R.id.btnAdd)
        val btnCancel = view.findViewById<TextView>(R.id.btnCancel)
        val etNation = view.findViewById<EditText>(R.id.etNationality)
        val etJobTitle = view.findViewById<EditText>(R.id.etJobTitles)
        val etQuantitySheet = view.findViewById<EditText>(R.id.etQuantitySheet)
        val etQuantity = view.findViewById<EditText>(R.id.etQuantity)
        val tvErrorQuantity = view.findViewById<TextView>(R.id.tvErrorQuantity)
        val tvErrorQuantitySheet = view.findViewById<TextView>(R.id.tvErrorQuantitySheet)
        val tvErrorNation = view.findViewById<TextView>(R.id.tvErrorNation)
        val tvErrorJobtitle = view.findViewById<TextView>(R.id.tvErrorJobtitle)

        view.findViewById<TextView>(R.id.tvNationality).setText(R.string.owner_nationality)
        view.findViewById<EditText>(R.id.etNationality).setHint(R.string.owner_nationality)
        view.findViewById<TextView>(R.id.tvJobTitles).setText(R.string.job_title)
        view.findViewById<EditText>(R.id.etJobTitles).setHint(R.string.job_title)
        view.findViewById<TextView>(R.id.tvQuantitySheet).setText(R.string.quantity)
        view.findViewById<EditText>(R.id.etQuantitySheet).setHint(R.string.quantity)
        view.findViewById<TextView>(R.id.tvAnnualSalary).setText(R.string.annual_salaries_QAR)
        view.findViewById<EditText>(R.id.etQuantity).setHint(R.string.quantity)
        view.findViewById<TextView>(R.id.btnCancel).setText(R.string.cancel_btn)
        view.findViewById<TextView>(R.id.btnAdd).setText(R.string.add_btn)
        view.findViewById<TextView>(R.id.chooseNation).setText(R.string.choose_nation)
        view.findViewById<TextView>(R.id.chooseJobTitle).setText(R.string.choose_job_title)

        etNation.setText(addView.findViewById<TextView>(R.id.tvNationalityValue).text.toString())
        etJobTitle.setText(addView.findViewById<TextView>(R.id.tvJobTitleValue).text.toString())
        etQuantitySheet.setText(addView.findViewById<TextView>(R.id.tvQuantityValue).text.toString())
        etQuantity.setText(addView.findViewById<TextView>(R.id.tvAnnualSalary).text.toString())
        recyclerViewNation = view.findViewById(R.id.recyclerNation)
        recyclerViewNation.layoutManager = LinearLayoutManager(requireContext())
        view.findViewById<ImageView>(R.id.ivNation).setOnClickListener {
            val adapterNation = NationalityAdapter(listNation)
            recyclerViewNation.adapter = adapterNation
            bottomsheetNation.state = BottomSheetBehavior.STATE_EXPANDED
            adapterNation.setOnItemClickListener(object : NationalityAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    val etNation = view.findViewById<EditText>(R.id.etNationality)
                    etNation.setText(listNation[position].nation)
                    bottomsheetNation.state = BottomSheetBehavior.STATE_HIDDEN
                }
            })
        }

        recyclerViewJobTitle = view.findViewById(R.id.recyclerViewJobTitle)
        recyclerViewJobTitle.layoutManager = LinearLayoutManager(requireContext())
        view.findViewById<ImageView>(R.id.ivJobTitle).setOnClickListener {
            val adapterJob = JobTitleAdapter(listJob)
            recyclerViewJobTitle.adapter = adapterJob
            bottomsheetJob.state = BottomSheetBehavior.STATE_EXPANDED
            adapterJob.setOnItemClickListener(object : JobTitleAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    val etJob = view.findViewById<EditText>(R.id.etJobTitles)
                    etJob.setText(listJob[position].job)
                    bottomsheetJob.state = BottomSheetBehavior.STATE_HIDDEN
                }
            })
        }


        btnAdd.setOnClickListener {
            if (!etNation.text.isNullOrBlank() && !etJobTitle.text.isNullOrBlank() && !etQuantitySheet.text.isNullOrBlank() && !etQuantity.text.isNullOrBlank()) {
                tvErrorQuantity.text = ""
                tvErrorQuantitySheet.text = ""
                tvErrorNation.text = ""
                tvErrorJobtitle.text = ""
                binding.cardViewNeededWorkers.visibility = View.VISIBLE
                binding.nextAdd.visibility = View.VISIBLE
                binding.constraintAddProduct.visibility = View.INVISIBLE
                listAddWorkers.add(
                    AddNeededWorkersDataClass(
                        "${view.findViewById<EditText>(R.id.etNationality).text.toString()}",
                        "${view.findViewById<EditText>(R.id.etJobTitles).text.toString()}",
                        "${view.findViewById<EditText>(R.id.etQuantitySheet).text.toString()}",
                        "${view.findViewById<EditText>(R.id.etQuantity).text.toString()}"
                    )
                )
                recyclerviewAddWorkers.adapter = adapterNeededWorkers
                dialog.cancel()

                /*binding.tvNationalityValue.text = etNation.text.toString()
                binding.tvJobTitleValue.text = etJobTitle.text.toString()
                binding.tvQuantityValue.text = etQuantitySheet.text
                binding.tvAnnualSalary.text = etQuantity.text*/
            } else {
                tvErrorQuantitySheet.setText(R.string.required)
                tvErrorQuantity.setText(R.string.required)
                tvErrorNation.setText(R.string.required)
                tvErrorJobtitle.setText(R.string.required)
            }
        }
        btnCancel.setOnClickListener {
            dialog.cancel()
        }
    }
}