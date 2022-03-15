package com.example.lecture5

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class Fragment1 : Fragment() {

    lateinit var nameView: EditText
    lateinit var surnameView: EditText
    lateinit var imageView: ImageView
    lateinit var imageUri: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment1, container, false)

        nameView = view.findViewById(R.id.textOne)
        surnameView = view.findViewById(R.id.textTwo)

        imageView = view.findViewById(R.id.imageView)
        imageView.setOnClickListener(View.OnClickListener {
            val i = Intent()
            i.type = "*/*"
            i.action = Intent.ACTION_GET_CONTENT

            startForResult.launch(i)
        })
        return view
    }

    var startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                imageUri = it.data?.data.toString()
                val image: Bitmap = getBitmap(requireContext(), null, imageUri, ::UriToBitmap)
                imageView.setImageBitmap(image)
            }
        }

}