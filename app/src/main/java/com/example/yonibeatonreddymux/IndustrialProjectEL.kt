package com.example.yonibeatonreddymux

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.view.WindowInsets
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginBottom
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import com.example.yonibeatonreddymux.databinding.FragmentIndustrialProjectELBinding
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*


var lang = "English"

@Suppress("DEPRECATION")
class IndustrialProjectEL : Fragment() {
    lateinit var binding: FragmentIndustrialProjectELBinding

    @RequiresApi(Build.VERSION_CODES.O)
    var instant: Instant = Instant.now()

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIndustrialProjectELBinding.inflate(inflater, container, false)
        val view = binding.root
        val toPx = resources.displayMetrics.density
        ViewCompat.setOnApplyWindowInsetsListener(view) { _, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<MarginLayoutParams> { bottomMargin = insets.bottom }
            windowInsets
        }
        binding.btnNext.setOnClickListener {
            view.findNavController().navigate(R.id.action_industrialProjectEL_to_mainDataRecycler)
            //view.findNavController().navigate(R.id.action_industrialProjectEL_to_blankFragment)
        }

        binding.ivInfoBtn.setOnClickListener {
            binding.info.visibility = View.VISIBLE
            binding.selectLanguage.visibility = View.GONE
        }
        /*var count=0
        binding.ivInfoBtn.setOnClickListener {
            count+=1
            val anim = ObjectAnimator.ofFloat(0f, 1f)
            anim.addUpdateListener {
                val animatedvalue = it.animatedValue as Float
                binding.constraintMain.translationX = (animatedvalue * -300f) * toPx
                binding.constraintMain.scaleX=(1-(0.2*animatedvalue)).toFloat()
                binding.constraintMain.scaleY=(1-(0.2*animatedvalue)).toFloat()
            }
            anim.duration=1000
            binding.infoMainConstraint.visibility=View.VISIBLE
            anim.start()

            if(count>1){
                val animate=ObjectAnimator.ofFloat(1f,0f)
                animate.addUpdateListener {
                    val animatedValue=it.animatedValue as Float
                    binding.constraintMain.translationX=-(animatedValue*300f)*toPx
                    binding.constraintMain.scaleX=(1-(0.2*animatedValue)).toFloat()
                    binding.constraintMain.scaleY=(1-(0.2*animatedValue)).toFloat()
                }
                animate.duration=1000
                animate.start()
                binding.infoMainConstraint.visibility=View.INVISIBLE
                count=0
            }
        }
        */
        binding.ivSelectLanguage.setOnClickListener {
            binding.selectLanguage.visibility = View.VISIBLE
        }
        binding.constraintIndustrial.setOnClickListener {
            binding.info.visibility = View.INVISIBLE
        }
        binding.tvSelectlanguage.setOnClickListener {
            Log.d("TAG", "onCreateView: ")
            if (binding.tvSelectlanguage.text.toString() == "English") {
                setLocal("en")
                lang = "English"
                binding.tvSelectlanguage.text = "عربي"
                binding.info.visibility = View.GONE
                binding.ivBackBtn.layoutDirection = View.LAYOUT_DIRECTION_LTR
                binding.constraintIndustrial.layoutDirection = View.LAYOUT_DIRECTION_LTR
            } else if (binding.tvSelectlanguage.text.toString() == "عربي") {
                setLocal("ar")
                lang = "Arabic"
                binding.tvSelectlanguage.text = "English"
                binding.info.visibility = View.GONE
                binding.ivBackBtn.layoutDirection = View.LAYOUT_DIRECTION_RTL
                binding.constraintIndustrial.layoutDirection = View.LAYOUT_DIRECTION_RTL
            }

        }
        if (lang == "English") {
            binding.tvSelectlanguage.text = "عربي"
            binding.constraintIndustrial.layoutDirection = View.LAYOUT_DIRECTION_LTR
        } else if (lang == "Arabic") {
            binding.tvSelectlanguage.text = "English"
            binding.constraintIndustrial.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
        //binding.tvTextDate1.text=timeFormat.toString()
        //binding.tvTextDate2.text=timeFormat.toString()
        binding.tvAppName.setText(R.string.app_name)
        binding.tvLanguages.setText(R.string.languages)
        binding.tvIndustrialProject.setText(R.string.indystry_project)
        binding.tvIndustrialProjectName.setText(R.string.sub_industrial_project_name)
        binding.tvTextYoniBeaton.setText(R.string.yoni_beaton_reddy_mux)
        binding.tvTitleInitialApproval.setText(R.string.initial_approval_number)
        binding.tvText827.setText(R.string.initial_approval_number_value)
        binding.tvTitleInitialStartDate.setText(R.string.initial_approval_start_date)
        binding.tvTextDate1.setText(R.string.initial_approval_start_date_value)
        binding.tvTitleInitialExpiryDate.setText(R.string.initial_approval_expiry_date)
        binding.tvTextDate2.setText(R.string.initial_approval_expiry_date_value)
        binding.tvTitleLocation.setText(R.string.location)
        binding.tvValueLocation.setText(R.string.location_value)
        binding.tvTitleZone.setText(R.string.zone_number)
        binding.tvValueZone.setText(R.string.zone_number_value)
        binding.tvTitleStreet.setText(R.string.street)
        binding.tvValueStreet.setText(R.string.street_value)
        binding.tvTitleBuilding.setText(R.string.building_number)
        binding.tvValueBuilding.setText(R.string.building_number_value)
        binding.tvTitleService.setText(R.string.service_fee)
        binding.tvValueService.setText(R.string.service_fee_value)
        binding.tvTitleTimeOfService.setText(R.string.time_of_service)
        binding.tvValueTimeOfService.setText(R.string.time_of_service_value)
        binding.tvTitleLicense.setText(R.string.license_status)
        binding.tvValueLicense.setText(R.string.license_status_value)
        binding.btnNext.setText(R.string.next_btn)
    }
}