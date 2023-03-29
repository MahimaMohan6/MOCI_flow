package com.example.yonibeatonreddymux

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginBottom
import androidx.core.view.updateLayoutParams
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yonibeatonreddymux.databinding.FragmentProjectsiteDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.lang.ref.SoftReference
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Suppress("COMPATIBILITY_WARNING", "DEPRECATION")
class ProjectSiteData : Fragment() {
    lateinit var binding: FragmentProjectsiteDataBinding
    lateinit var recyclerViewProof: RecyclerView
    lateinit var recyclerViewEnvPermit: RecyclerView
    var listPermit = ArrayList<EnvPermitData>()
    var listProof = kotlin.collections.ArrayList<ProofRecyclerData>()

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectsiteDataBinding.inflate(inflater, container, false)
        val view = binding.root
        val bottomSheetBehaviorProof = BottomSheetBehavior.from(binding.cardViewPoof)
        bottomSheetBehaviorProof.state = BottomSheetBehavior.STATE_HIDDEN
        val bottomSheetBehaviorEnvPermit =
            BottomSheetBehavior.from(binding.cardViewEnvironmentalPermit)
        bottomSheetBehaviorEnvPermit.state = BottomSheetBehavior.STATE_HIDDEN
        //val view= inflater.inflate(R.layout.fragment_projectsite_data, container, false)
        //val btnNext = requireActivity().findViewById<TextView>(R.id.btnNext)
        //val btnBack = requireActivity().findViewById<TextView>(R.id.btnBack)
        var count = 0
        binding.ivExapand1.setOnClickListener {
            count += 1
            binding.clHiddenView.visibility = View.GONE
            binding.ivExapand1.setImageResource(R.drawable.img_expandable_arrow)
            if (count > 1) {
                binding.clHiddenView.visibility = View.VISIBLE
                binding.ivExapand1.setImageResource(R.drawable.img_up_arrow)
                count = 0
            }
        }
        recyclerViewProof = binding.recyclerViewProof
        recyclerViewProof.layoutManager = LinearLayoutManager(requireContext())
        if (lang == "English") {
            listProof.add(ProofRecyclerData("Vehicles"))
            listProof.add(ProofRecyclerData("Land"))
            listProof.add(ProofRecyclerData("Ornaments"))
            listProof.add(ProofRecyclerData("Money"))
        }
        if (lang == "Arabic") {
            listProof.add(ProofRecyclerData("مركبات"))
            listProof.add(ProofRecyclerData("أرض"))
            listProof.add(ProofRecyclerData("الحلي"))
            listProof.add(ProofRecyclerData("مال"))
        }
        binding.ivProof.setOnClickListener {
            val adapterProof = ProofAdapter(listProof)
            recyclerViewProof.adapter = adapterProof
            binding.cardViewPoof.visibility=View.VISIBLE
            bottomSheetBehaviorProof.state = BottomSheetBehavior.STATE_EXPANDED
            adapterProof.setOnItemClickListener(object : ProofAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    binding.etProofOfUsufruct.setText(listProof[position].proof)
                    bottomSheetBehaviorProof.state = BottomSheetBehavior.STATE_HIDDEN
                }
            })
        }
        recyclerViewEnvPermit = binding.recyclerViewEnvPermit
        recyclerViewEnvPermit.layoutManager = LinearLayoutManager(requireContext())
        listPermit.add(EnvPermitData("PR1001"))
        listPermit.add(EnvPermitData("Pr1002"))
        listPermit.add(EnvPermitData("Pr1003"))
        listPermit.add(EnvPermitData("Pr1004"))
        listPermit.add(EnvPermitData("Pr1005"))
        binding.ivEnvPermit.setOnClickListener {
            val adapterEnv = EnvPermitAdapter(listPermit)
            recyclerViewEnvPermit.adapter = adapterEnv
            binding.cardViewEnvironmentalPermit.visibility=View.VISIBLE
            bottomSheetBehaviorEnvPermit.state = BottomSheetBehavior.STATE_EXPANDED
            adapterEnv.setOnItemClickListener(object : EnvPermitAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    binding.etEnvironmentNumber.setText(listPermit[position].number)
                    bottomSheetBehaviorEnvPermit.state = BottomSheetBehavior.STATE_HIDDEN
                }
            })

        }
        TextEnableFalse()
        /* binding.etPinNumber.addTextChangedListener(object : TextWatcher {
             override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                 binding.tvVerify.background.setTint(Color.parseColor("#F7F7F7"))
             }

             @SuppressLint("ResourceAsColor")
             override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                 binding.tvVerify.background.setTint(Color.parseColor("#8AE197"))
             }

             override fun afterTextChanged(s: Editable?) {
                 if (binding.etPinNumber.text.length == 6 && !binding.etPinNumber.text.isNullOrBlank() && binding.etPinNumber.text.matches(
                         "-?\\d+(\\.\\d+)?".toRegex()
                     )
                 ) {
                     binding.tvVerify.visibility = View.INVISIBLE
                     binding.ivTickmark.visibility = View.VISIBLE
                     binding.tvErrorPin.text = ""
                     TextEnableTrue()
                 } else {
                     binding.tvVerify.visibility = View.VISIBLE
                     binding.ivTickmark.visibility = View.INVISIBLE

                 }
             }
         })*/
        binding.etPinNumber.addTextChangedListener {
            if (!binding.etPinNumber.text.isNullOrBlank()) {
                binding.tvVerify.background.setTint(Color.parseColor("#8AE197"))
                if (binding.etPinNumber.text.length == 6 && !binding.etPinNumber.text.isNullOrBlank() && binding.etPinNumber.text.matches(
                        "-?\\d+(\\.\\d+)?".toRegex()
                    )
                ) {
                    binding.tvVerify.visibility = View.INVISIBLE
                    binding.ivTickmark.visibility = View.VISIBLE
                    binding.tvErrorPin.text = ""
                    TextEnableTrue()
                } else {
                    binding.tvVerify.visibility = View.VISIBLE
                    binding.ivTickmark.visibility = View.INVISIBLE
                    binding.tvErrorPin.setText(R.string.pin_number_must_be_valid)
                }
            }
        }


        /*binding.tvVerifyPin.setOnClickListener {
            println("haiii")
            println(binding.etHiddenPin.text)
            binding.tvVerifyPin.visibility=View.GONE
            binding.ivTickmark.visibility=View.VISIBLE
        }*/

        binding.ivExpandRel.setOnClickListener {
            count += 1
            binding.clHiddenViewRelation.visibility = View.VISIBLE
            binding.ivExpandRel.setImageResource(R.drawable.img_up_arrow)
            if (count > 1) {
                binding.clHiddenViewRelation.visibility = View.GONE
                bottomSheetBehaviorProof.state = BottomSheetBehavior.STATE_HIDDEN
                binding.ivExpandRel.setImageResource(R.drawable.img_expandable_arrow)
                count = 0
            }
        }
        binding.ivExpandEnv.setOnClickListener {
            count += 1
            binding.clHiddenViewEnv.visibility = View.VISIBLE
            binding.ivExpandEnv.setImageResource(R.drawable.img_up_arrow)
            if (count > 1) {
                binding.clHiddenViewEnv.visibility = View.GONE
                binding.ivExpandEnv.setImageResource(R.drawable.img_expandable_arrow)
                count = 0
            }
        }
        val calender = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calender.set(Calendar.YEAR, year)
            calender.set(Calendar.MONTH, monthOfYear)
            calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val dateFormat = "MM/dd/yyyy"
            val sdf = SimpleDateFormat(dateFormat, Locale.US)
            binding.etEnvironmentIssueDate.setText(sdf.format(calender.time))
        }
        val cal = Calendar.getInstance()
        val dateListener = DatePickerDialog.OnDateSetListener { view, year, month, date ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, date)
            val myFormat = "MM/dd/yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.etEnvironmentExpiryDate.setText(sdf.format(cal.time))
        }
        binding.ivExpiryDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                dateListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        binding.ivIssueDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                date,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
         /*binding.scrollView.setOnApplyWindowInsetsListener { v, windowInsets ->
             val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
             view.updateLayoutParams<MarginLayoutParams> { bottomMargin =insets.bottom}
             windowInsets
         }*/
        /*ViewCompat.setOnApplyWindowInsetsListener(view) { v, windowInsets ->
            val insets=windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<MarginLayoutParams> {bottomMargin=insets.bottom  }
            windowInsets
        }*/
        binding.btnNext.setOnClickListener {
            if (!binding.etPinNumber.text.isNullOrBlank()) {
                binding.tvErrorPin.text = ""
                if (!binding.etReleaseDate.text.isNullOrBlank() && !binding.etEnvironmentNumber.text.isNullOrBlank() && !binding.etReleaseDate.text.isNullOrBlank() && !binding.etEnvironmentExpiryDate.text.isNullOrBlank()) {
                    binding.tvErrorRelease.text = ""
                    binding.tvErrorPermitNo.text = ""
                    binding.tvErrorPIssueDate.text = ""
                    binding.tvErrorPExpireDate.text = ""
                    val currentIndex = selectedLiveData.value
                    val nextIndex = currentIndex!! + 1
                    selectedLiveData.value = nextIndex

                    Session.pinNumber = binding.etPinNumber.text.toString()
                    Session.locationName = binding.etLocationName.text.toString()
                    Session.ZoneCode = binding.etZoneCode.text.toString()
                    Session.districtNumber = binding.etDistrictNumber.text.toString()
                    Session.street = binding.etStreet.text.toString()
                    Session.buildingNumber = binding.etBuildingNumber.text.toString()
                    Session.proof = binding.etProofOfUsufruct.text.toString()
                    Session.lessor = binding.etLessor.text.toString()
                    Session.releaseDate = binding.etReleaseDate.text.toString()
                    Session.organisers = binding.etOrganisers.text.toString()
                    Session.permitNumber = binding.etEnvironmentNumber.text.toString()
                    Session.permitIssueDate = binding.etEnvironmentIssueDate.text.toString()
                    Session.permitExpiryDate = binding.etEnvironmentExpiryDate.text.toString()
                    tabSelect.add("1")
                } else {
                    Toast.makeText(
                        requireContext(),
                        R.string.please_provide_details,
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.tvErrorRelease.setText(R.string.required)
                    binding.tvErrorPIssueDate.setText(R.string.required)
                    binding.tvErrorPermitNo.setText(R.string.required)
                    binding.tvErrorPExpireDate.setText(R.string.required)
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.please_verify_pin_number,
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
        if (lang == "English") {
            setLocal("en")
            binding.constraintProjectSiteData.layoutDirection = View.LAYOUT_DIRECTION_LTR
        } else if (lang == "Arabic") {
            setLocal("ar")
            binding.constraintProjectSiteData.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }

        binding.btnConstraint.setOnApplyWindowInsetsListener { _, windowInsets ->
            val insets=windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<MarginLayoutParams> {bottomMargin=insets.bottom  }
            windowInsets
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.etPinNumber.setText(Session.pinNumber)
        binding.etLocationName.setText(Session.locationName)
        binding.etZoneCode.setText(Session.ZoneCode)
        binding.etDistrictNumber.setText(Session.districtNumber)
        binding.etStreet.setText(Session.street)
        binding.etBuildingNumber.setText(Session.buildingNumber)
        binding.etProofOfUsufruct.setText(Session.proof)
        binding.etReleaseDate.setText(Session.releaseDate)
        binding.etLessor.setText(Session.lessor)
        binding.etOrganisers.setText(Session.organisers)
        binding.etEnvironmentNumber.setText(Session.permitNumber)
        binding.etEnvironmentIssueDate.setText(Session.permitIssueDate)
        binding.etEnvironmentExpiryDate.setText(Session.permitExpiryDate)
        super.onViewCreated(view, savedInstanceState)
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
        binding.etPinNumber.textLocale = locale
        binding.etLocationName.textLocale = locale
        binding.tvLocationData.setText(R.string.location_data)
        binding.tvPinNumber.setText(R.string.pin_number)
        binding.etPinNumber.setHint(R.string.pin_number)
        binding.tvLocationName.setText(R.string.location_name)
        binding.etLocationName.setHint(R.string.location_name)
        binding.tvZoneCode.setText(R.string.zone_code)
        binding.etZoneCode.setHint(R.string.zone_code)
        binding.tvDistrictNumber.setText(R.string.district_number)
        binding.etDistrictNumber.setHint(R.string.district_number)
        binding.tvStreet.setText(R.string.street)
        binding.etStreet.setHint(R.string.street)
        binding.tvVerify.setText(R.string.verify)
        binding.tvBuildingNumber.setText(R.string.building_number)
        binding.etBuildingNumber.setHint(R.string.building_number)
        binding.tvRelation.setText(R.string.relation_type)
        binding.tvProofOfUsufruct.setText(R.string.proof_of_usufruct)
        binding.etProofOfUsufruct.setHint(R.string.proof_of_usufruct)
        binding.tvReleaseDate.setText(R.string.release_date)
        binding.etReleaseDate.setHint(R.string.release_date)
        binding.tvlessor.setText(R.string.lessor)
        binding.etLessor.setHint(R.string.lessor)
        binding.tvOrganisers.setText(R.string.organisers)
        binding.etOrganisers.setHint(R.string.organisers)
        binding.tvEnvironment.setText(R.string.env_permit_data)
        binding.tvEnvironmentNumber.setText(R.string.env_permit_number)
        binding.etEnvironmentNumber.setHint(R.string.env_permit_number)
        binding.tvEnvironmentIssueDate.setText(R.string.env_permit_issue_date)
        binding.etEnvironmentIssueDate.setHint(R.string.date_format)
        binding.tvEnvironmentExpiryDate.setText(R.string.env_permit_expiry_date)
        binding.etEnvironmentExpiryDate.setHint(R.string.date_format)
        binding.btnNext.setText(R.string.next_btn)
        binding.btnBack.setText(R.string.back_btn)
        binding.tvChoosePermit.setText(R.string.choose_permit_number)
        binding.tvChooseProof.setText(R.string.choose_proof_of_usufruct_rent_ownership)
    }

    fun TextEnableFalse() {
        binding.tvLocationName.isEnabled = false
        binding.tvLocationName.setTextColor(Color.parseColor("#8E8E8E"))
        binding.etLocationName.isEnabled = false
        binding.etLocationName.setTextColor(Color.parseColor("#8E8E8E"))
        binding.tvZoneCode.isEnabled = false
        binding.tvZoneCode.setTextColor(Color.parseColor("#8E8E8E"))
        binding.etZoneCode.isEnabled = false
        binding.etZoneCode.setTextColor(Color.parseColor("#8E8E8E"))
        binding.tvDistrictNumber.isEnabled = false
        binding.tvDistrictNumber.setTextColor(Color.parseColor("#8E8E8E"))
        binding.etDistrictNumber.isEnabled = false
        binding.etDistrictNumber.setTextColor(Color.parseColor("#8E8E8E"))
        binding.etStreet.isEnabled = false
        binding.etStreet.setTextColor(Color.parseColor("#8E8E8E"))
        binding.tvStreet.isEnabled = false
        binding.tvStreet.setTextColor(Color.parseColor("#8E8E8E"))
        binding.tvBuildingNumber.isEnabled = false
        binding.tvBuildingNumber.setTextColor(Color.parseColor("#8E8E8E"))
        binding.etBuildingNumber.isEnabled = false
        binding.etBuildingNumber.setTextColor(Color.parseColor("#8E8E8E"))
        binding.clMainRelationType.isEnabled = false
        binding.tvRelation.isEnabled = false
        binding.ivExpandRel.isEnabled = false
        binding.ivExpandEnv.isEnabled = false
        binding.tvRelation.setTextColor(Color.parseColor("#8E8E8E"))
        binding.clMainEnv.isEnabled = false
        binding.tvEnvironment.isEnabled = false
        binding.tvEnvironment.setTextColor(Color.parseColor("#8E8E8E"))
        binding.btnNext.setBackgroundResource(R.drawable.bg_border_disabledbtn)
        binding.btnNext.backgroundTintMode = null
        binding.btnNext.setTextColor(Color.parseColor("#8E8E8E"))
    }

    @SuppressLint("ResourceAsColor")
    fun TextEnableTrue() {
        binding.tvLocationName.isEnabled = true
        binding.tvLocationName.setTextColor(Color.BLACK)
        binding.etLocationName.isEnabled = true
        binding.etLocationName.setTextColor(Color.parseColor("#343434"))
        binding.tvZoneCode.isEnabled = true
        binding.tvZoneCode.setTextColor(Color.BLACK)
        binding.etZoneCode.isEnabled = true
        binding.etZoneCode.setTextColor(Color.parseColor("#343434"))
        binding.tvDistrictNumber.isEnabled = true
        binding.tvDistrictNumber.setTextColor(Color.BLACK)
        binding.etDistrictNumber.isEnabled = true
        binding.etDistrictNumber.setTextColor(Color.parseColor("#343434"))
        binding.etStreet.isEnabled = true
        binding.etStreet.setTextColor(Color.parseColor("#343434"))
        binding.tvStreet.isEnabled = true
        binding.tvStreet.setTextColor(Color.BLACK)
        binding.tvBuildingNumber.isEnabled = true
        binding.tvBuildingNumber.setTextColor(Color.BLACK)
        binding.etBuildingNumber.isEnabled = true
        binding.etBuildingNumber.setTextColor(Color.parseColor("#343434"))
        binding.clMainRelationType.isEnabled = true
        binding.tvRelation.isEnabled = true
        binding.ivExpandRel.isEnabled = true
        binding.ivExpandEnv.isEnabled = true
        binding.tvRelation.setTextColor(Color.BLACK)
        binding.clMainEnv.isEnabled = true
        binding.tvEnvironment.isEnabled = true
        binding.tvEnvironment.setTextColor(Color.BLACK)
        binding.btnNext.setTextColor(Color.WHITE)
        binding.btnNext.setBackgroundResource(R.drawable.bg_btn_next)
    }

}