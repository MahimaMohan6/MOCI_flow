package com.example.yonibeatonreddymux

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
import androidx.core.view.updateLayoutParams
import com.example.yonibeatonreddymux.databinding.FragmentAnnualUtilityBinding

class AnnualUtility : Fragment() {
    lateinit var binding: FragmentAnnualUtilityBinding
    lateinit var btnNext:TextView
    lateinit var btnBack:TextView
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnnualUtilityBinding.inflate(inflater, container, false)
        val view = binding.root
        btnBack=requireActivity().findViewById(R.id.btnBack)
        btnNext=requireActivity().findViewById(R.id.btnNext)
        // return inflater.inflate(R.layout.fragment_annual_utility, container, false)
        btnNext.setOnClickListener {
            val currentIndex= selectedLiveData.value
            val nextIndex= currentIndex!! +1
            selectedLiveData.value=nextIndex
            Session.totalInvest=binding.etTotalInvest.text.toString()
            Session.area=binding.etArea.text.toString()
            Session.electricity=binding.etElectricity.text.toString()
            Session.water=binding.etWater.text.toString()
            Session.gas=binding.etGas.text.toString()
            Session.manufacturing=binding.etManufacturing.text.toString()
        }
        tabSelect.add("6")
        btnBack.setOnClickListener {
            val currentIndex= selectedLiveData.value
            val previousIndex= currentIndex!! -1
            selectedLiveData.value=previousIndex
        }

        binding.tvPleaseFill.setText(R.string.please_fill_in_the_following)
        binding.tvTotalInvest.setText(R.string.total_invest_capital_QAR)
        binding.etTotalInvest.setHint(R.string.total_invest_capital_QAR)
        binding.tvArea.setText(R.string.area)
        binding.etArea.setHint(R.string.area)
        binding.tvElectricity.setText(R.string.electricity)
        binding.etElectricity.setHint(R.string.electricity)
        binding.tvWater.setText(R.string.water)
        binding.etWater.setHint(R.string.water)
        binding.tvGas.setText(R.string.gas_and_diesel)
        binding.etGas.setHint(R.string.gas_and_diesel)
        binding.tvManufacturing.setText(R.string.manufacturing_stages)
        binding.etManufacturing.setHint(R.string.manufacturing_stages)
        btnNext.setText(R.string.next_btn)
        btnBack.setText(R.string.back_btn)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.etTotalInvest.setText(Session.totalInvest)
        binding.etArea.setText(Session.area)
        binding.etElectricity.setText(Session.electricity)
        binding.etWater.setText(Session.water)
        binding.etGas.setText(Session.gas)
        binding.etManufacturing.setText(Session.manufacturing)
        super.onViewCreated(view, savedInstanceState)
    }

}