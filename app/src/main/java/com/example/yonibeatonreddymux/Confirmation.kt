package com.example.yonibeatonreddymux

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.yonibeatonreddymux.databinding.FragmentConfirmationBinding

class Confirmation : Fragment() {
lateinit var binding:FragmentConfirmationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentConfirmationBinding.inflate(inflater,container,false)
        val view=binding.root
        val canvas=binding.drawCanvas
        //return inflater.inflate(R.layout.fragment_confirmation, container, false)
        if(lang=="English"){
            binding.constraintConfirmation.layoutDirection=View.LAYOUT_DIRECTION_LTR
        }else if(lang=="Arabic"){
            binding.constraintConfirmation.layoutDirection=View.LAYOUT_DIRECTION_RTL
        }
        binding.ivBackBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_confirmation_to_mainDataRecycler)
        }
        return view
    }

}