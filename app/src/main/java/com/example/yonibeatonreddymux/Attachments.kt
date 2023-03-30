package com.example.yonibeatonreddymux

import android.annotation.SuppressLint
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.navigation.findNavController
import com.example.yonibeatonreddymux.databinding.FragmentAttachmentsBinding
import org.w3c.dom.Text
import java.io.File

class Attachments : Fragment() {
    lateinit var binding: FragmentAttachmentsBinding
    private lateinit var sampleUri: Uri
lateinit var btnNext:TextView
lateinit var btnBack:TextView
    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("Range", "ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAttachmentsBinding.inflate(inflater, container, false)
        val view = binding.root
        btnNext=requireActivity().findViewById(R.id.btnNext)
        btnBack=requireActivity().findViewById(R.id.btnBack)
        //return inflater.inflate(R.layout.fragment_attachments, container, false)
        val uploadeFirst =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                if (uri != null) {
                    sampleUri = uri
                    println("sampleUri.host ${sampleUri.host}")
                    println("sampleUri ${sampleUri.path}")
                    Session.uploadUri1 = sampleUri
                    val fileDescriptor =
                        requireContext().contentResolver.openAssetFileDescriptor(sampleUri, "r")
                    val fileSize = fileDescriptor!!.length / 1024
                    val cursor: Cursor? =
                        requireContext().contentResolver.query(sampleUri, null, null, null, null)
                    if (cursor != null && cursor.moveToFirst()) {
                        val result =
                            cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                        binding.tvUpload1.text =
                            result.toString() + " " + "(${fileSize.toString()} KB)"
                        Session.uploadText1 = binding.tvUpload1.text.toString()
                        if (!binding.tvUpload1.text.isNullOrBlank()) {
                            binding.ivPdf1.visibility = View.VISIBLE
                            binding.ivRemove1.visibility = View.VISIBLE
                            binding.ivUpload1.visibility = View.INVISIBLE
                        } else {
                            binding.ivPdf1.visibility = View.INVISIBLE
                            binding.ivRemove1.visibility = View.INVISIBLE
                            binding.ivUpload1.visibility = View.VISIBLE
                        }
                    }
                }
            }
        val uploadSecond =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                if (uri != null) {
                    sampleUri = uri
                    Session.uploadUri2 = sampleUri
                    val cursor: Cursor? =
                        requireContext().contentResolver.query(sampleUri, null, null, null, null)
                    val fileDescriptor =
                        requireContext().contentResolver.openAssetFileDescriptor(sampleUri, "r")
                    val fileSize = fileDescriptor!!.length / 1024

                    if (cursor != null && cursor.moveToFirst()) {
                        val result =
                            cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                        binding.tvUpload2.text =
                            result.toString() + " " + "(${fileSize.toString()} KB)"
                        Session.uploadText2 = binding.tvUpload2.text.toString()
                        if (!binding.tvUpload2.text.isNullOrBlank()) {
                            binding.ivPdf2.visibility = View.VISIBLE
                            binding.ivRemove2.visibility = View.VISIBLE
                            binding.ivUpload2.visibility = View.INVISIBLE
                        } else {
                            binding.ivPdf2.visibility = View.INVISIBLE
                            binding.ivRemove2.visibility = View.INVISIBLE
                            binding.ivUpload2.visibility = View.VISIBLE
                        }
                    }
                }

            }
        val uploadThird =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                if (uri != null) {
                    sampleUri = uri
                    Session.uploadUri3 = sampleUri
                    val cursor: Cursor? =
                        requireContext().contentResolver.query(sampleUri, null, null, null, null)
                    if (cursor != null && cursor.moveToFirst()) {
                        val result =
                            cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                        val fileDescriptor =
                            requireContext().contentResolver.openAssetFileDescriptor(sampleUri, "r")
                        val fileSize = fileDescriptor!!.length / 1024
                        binding.tvUpload3.text =
                            result.toString() + " " + "(${fileSize.toString()} KB)"
                        Session.uploadText3 = binding.tvUpload3.text.toString()
                        if (!binding.tvUpload3.text.isNullOrBlank()) {
                            binding.ivPdf3.visibility = View.VISIBLE
                            binding.ivRemove3.visibility = View.VISIBLE
                            binding.ivUpload3.visibility = View.GONE
                        } else {
                            binding.ivPdf3.visibility = View.GONE
                            binding.ivRemove3.visibility = View.GONE
                            binding.ivUpload3.visibility = View.VISIBLE
                        }
                    }
                }

            }
        binding.ivRemove1.setOnClickListener {
            binding.tvUpload1.text = ""
            binding.ivPdf1.visibility = View.GONE
            binding.ivRemove1.visibility = View.GONE
            binding.ivUpload1.visibility = View.VISIBLE
        }
        binding.ivRemove2.setOnClickListener {
            binding.tvUpload2.text = ""
            binding.ivPdf2.visibility = View.GONE
            binding.ivRemove2.visibility = View.GONE
            binding.ivUpload2.visibility = View.VISIBLE
        }
        binding.ivRemove3.setOnClickListener {
            binding.tvUpload3.text = ""
            binding.ivPdf3.visibility = View.GONE
            binding.ivRemove3.visibility = View.GONE
            binding.ivUpload3.visibility = View.VISIBLE
        }
        binding.ivUpload1.setOnClickListener {
            selectFromGallery(uploadeFirst)
        }

        binding.ivUpload2.setOnClickListener {
            selectFromGallery(uploadSecond)
        }
        binding.ivUpload3.setOnClickListener {
            selectFromGallery(uploadThird)
        }


        binding.tvAttachFeasibility.setText(R.string.attach_the_feasibility)
        binding.tvAttachLand.setText(R.string.attach_the_land)
        binding.tvAttachProof.setText(R.string.proof_of_usufruct)
        binding.tvUpload1.setHint(R.string.upload_attachment)
        binding.tvUpload2.setHint(R.string.upload_attachment)
        binding.tvUpload3.setHint(R.string.upload_attachment)
        binding.hint1.setText(R.string.mb_max_for_1file)
        binding.hint2.setText(R.string.mb_max_for_1file)
        binding.hint3.setText(R.string.mb_max_for_1file)
        btnBack.setText(R.string.back_btn)
        btnNext.setText(R.string.next_btn)

        btnNext.setOnClickListener {
            if (binding.tvUpload1.text.isNullOrBlank() || binding.tvUpload2.text.isNullOrBlank() || binding.tvUpload3.text.isNullOrBlank()) {
                binding.hint1.setTextColor(Color.parseColor("#E40909"))
                binding.hint2.setTextColor(Color.parseColor("#E40909"))
                binding.hint3.setTextColor(Color.parseColor("#E40909"))

            } else {
                binding.hint1.setTextColor(Color.parseColor("#8E8E8E"))
                binding.hint2.setTextColor(Color.parseColor("#8E8E8E"))
                binding.hint3.setTextColor(Color.parseColor("#8E8E8E"))
                tabSelect.add("7")
                view.findNavController().navigate(R.id.action_mainDataRecycler_to_confirmation)
            }
        }
        btnBack.setOnClickListener {
            val currentIndex = selectedLiveData.value
            val previousIndex = currentIndex!! - 1
            selectedLiveData.value = previousIndex
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvUpload1.text = Session.uploadText1
        if (!binding.tvUpload1.text.isNullOrBlank()) {
            binding.ivPdf1.visibility = View.VISIBLE
            binding.ivRemove1.visibility = View.VISIBLE
            binding.ivUpload1.visibility = View.INVISIBLE
        } else {
            binding.ivPdf1.visibility = View.INVISIBLE
            binding.ivRemove1.visibility = View.INVISIBLE
            binding.ivUpload1.visibility = View.VISIBLE
        }
        binding.tvUpload2.text = Session.uploadText2
        if (!binding.tvUpload2.text.isNullOrBlank()) {
            binding.ivPdf2.visibility = View.VISIBLE
            binding.ivRemove2.visibility = View.VISIBLE
            binding.ivUpload2.visibility = View.INVISIBLE
        } else {
            binding.ivPdf2.visibility = View.INVISIBLE
            binding.ivRemove2.visibility = View.INVISIBLE
            binding.ivUpload2.visibility = View.VISIBLE
        }
        binding.tvUpload3.text = Session.uploadText3
        if (!binding.tvUpload3.text.isNullOrBlank()) {
            binding.ivPdf3.visibility = View.VISIBLE
            binding.ivRemove3.visibility = View.VISIBLE
            binding.ivUpload3.visibility = View.GONE
        } else {
            binding.ivPdf3.visibility = View.GONE
            binding.ivRemove3.visibility = View.GONE
            binding.ivUpload3.visibility = View.VISIBLE
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun selectFromGallery(galleryResult: ActivityResultLauncher<String>) =
        galleryResult.launch("application/pdf")
}