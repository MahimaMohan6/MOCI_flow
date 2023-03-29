package com.example.yonibeatonreddymux

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginBottom
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.*
import com.example.yonibeatonreddymux.databinding.FragmentMainDataBinding
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

var tabSelect = kotlin.collections.HashSet<String>()

@Suppress("DEPRECATION")
class MainData : Fragment() {
    lateinit var binding: FragmentMainDataBinding

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainDataBinding.inflate(inflater, container, false)
        val view = binding.root
        val btnBack = requireActivity().findViewById<TextView>(R.id.btnBack)
        // val view= inflater.inflate(R.layout.fragment_main_data, container, false)
        var count = 0
        binding.ivExpand1.setOnClickListener {
            count += 1
            binding.clHiddenView.visibility = View.VISIBLE
            binding.ivExpand1.setImageResource(R.drawable.img_up_arrow)
            if (count > 1) {
                binding.clHiddenView.visibility = View.GONE
                binding.ivExpand1.setImageResource(R.drawable.img_expandable_arrow)
                count = 0
            }
        }
        binding.ivExapandAct1.setOnClickListener {
            count += 1
            binding.clActHiddenView.visibility = View.VISIBLE
            binding.ivExapandAct1.setImageResource(R.drawable.img_up_arrow)
            if (count > 1) {
                binding.clActHiddenView.visibility = View.GONE
                binding.ivExapandAct1.setImageResource(R.drawable.img_expandable_arrow)
                count = 0
            }
        }

        binding.btnConstraint.setOnApplyWindowInsetsListener { v, insets ->
            val windowInset = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<MarginLayoutParams> { bottomMargin = windowInset.bottom }
            insets
        }
        /*binding.btnConstraint.setOnApplyWindowInsetsListener { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<MarginLayoutParams> { bottomMargin = insets.bottom }
            windowInsets
        }*/
        tabSelect.add("0")
        binding.btnNext.setOnClickListener {
            val currentIndex = selectedLiveData.value
            val nextIndex = currentIndex!! + 1
            selectedLiveData.value = nextIndex
        }
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        if (lang == "English") {
            setLocal("en")
            binding.constraintMainData.layoutDirection = View.LAYOUT_DIRECTION_LTR
        } else if (lang == "Arabic") {
            setLocal("ar")
            binding.constraintMainData.layoutDirection = View.LAYOUT_DIRECTION_RTL
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
        binding.tvProjectOwner.setText(R.string.project_owners)
        binding.tvOwnerName1.setText(R.string.owner_name1)
        binding.tvOwnerName2.setText(R.string.owner_name2)
        binding.tvActivity1.setText(R.string.activity_1)
        binding.tvActivity2.setText(R.string.activity_2)
        binding.tvName.setText(R.string.owner_name)
        binding.tvTextIndustrial.setText(R.string.industrial_activities)
        binding.tvNameValue.setText(R.string.owner_name_value)
        binding.tvIdentificationType.setText(R.string.owner_identification_type)
        binding.tvIdentificationTypeValue.setText(R.string.owner_identification_type_value)
        binding.tvIdentificationNumber.setText(R.string.owner_identification_number)
        binding.tvIdentificationNumberValue.setText(R.string.owner_identification_number_value)
        binding.tvNationality.setText(R.string.owner_nationality)
        binding.tvNationalityValue.setText(R.string.owner_nationality_value)
        binding.tvOwnershipRate.setText(R.string.owner_ownership_rate)
        binding.tvOwnershipRateValue.setText(R.string.owner_ownership_rate_value)
        binding.tvActivityCode.setText(R.string.activity_code)
        binding.tvActivityCodeValue.setText(R.string.activity_code_value)
        binding.tvActivityDescription.setText(R.string.activity_description)
        binding.tvActivityDescriptionValue.setText(R.string.activity_description_value)
        binding.tvActivityType.setText(R.string.activity_type)
        binding.tvActivityTypeValue.setText(R.string.activity_type_value)
        binding.btnBack.setText(R.string.back_btn)
        binding.btnNext.setText(R.string.next_btn)
    }
}