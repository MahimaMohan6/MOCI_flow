package com.example.yonibeatonreddymux

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yonibeatonreddymux.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    lateinit var binding:FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentInfoBinding.inflate(inflater,container,false)
        val view=binding.root
       // return inflater.inflate(R.layout.fragment_info, container, false)

        return view
    }

}