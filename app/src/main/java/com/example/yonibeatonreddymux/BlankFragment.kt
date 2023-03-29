package com.example.yonibeatonreddymux

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.yonibeatonreddymux.databinding.FragmentBlankBinding
import com.example.yonibeatonreddymux.databinding.FragmentInfoBinding
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider.OnSliderTouchListener


class BlankFragment : Fragment() {
    lateinit var binding: FragmentBlankBinding

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor", "ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        val view = binding.root
        //return inflater.inflate(R.layout.fragment_blank, container, false)
        val toPx = resources.displayMetrics.density
        /* binding.circle.setOnTouchListener(object : OnTouchListener {
             override fun onTouch(v: View?, event: MotionEvent?): Boolean {
 val animation=TranslateAnimation(0f,300f,0f,0f)
                 animation.duration=1000
                 animation.fillAfter=true
                 binding.

                 return true
             }

         })*/
        /* binding.circle.setOnTouchListener(OnTouchListener { v, event ->
             val animation = TranslateAnimation(0f, binding.view.width.toFloat(), 0f, 0f)
             if(event.action==MotionEvent.ACTION_DOWN){
                 Toast.makeText(requireContext(),"button presses",Toast.LENGTH_SHORT).show()
                 animation.duration = 500
                 binding.circle.startAnimation(animation)
             }
             if(event.action==MotionEvent.ACTION_UP){
                 Toast.makeText(requireContext(),"button released",Toast.LENGTH_SHORT).show()
                binding.circle.animate().cancel()
             }
             true
         })*/
        binding.seekBar.setValues(0.toFloat(), 100.toFloat())
       binding.seekBar.setCustomThumbDrawable(R.drawable.ic_baseline_circle_24)
        binding.seekBar.addOnChangeListener { slider, value, fromUser ->
            val values=binding.seekBar.values
            binding.label1.text= values[0].toInt().toString()
            binding.label2.text=values[1].toInt().toString()
        }
        binding.seekBar.trackHeight=6
        binding.seekBar.haloRadius=46

        return view
    }

}