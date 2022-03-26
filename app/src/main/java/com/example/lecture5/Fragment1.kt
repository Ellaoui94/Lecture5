package com.example.lecture5

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
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
import com.edmodo.cropper.CropImageView

class Fragment1 : Fragment() {

    lateinit var nameView: EditText
    lateinit var surnameView: EditText
    lateinit var imageView: CropImageView1
    lateinit var imageUri: String
    lateinit var actualCropRect: RectF

    interface CustomOnClickListener {
        fun onClick(var1: View?)
    }

    interface CustomOnBoundingBoxChangedListener {
        fun onChanged(rec: RectF)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment1, container, false)

        nameView = view.findViewById(R.id.textOne)
        surnameView = view.findViewById(R.id.textTwo)

        imageView = view.findViewById(R.id.imageView)
        imageView.setListeners(object : CustomOnClickListener{
            override fun onClick(var1: View?) {
                val i = Intent()
            i.type = "*/*"
            i.action = Intent.ACTION_GET_CONTENT

            startForResult.launch(i)            }

        },
        object : CustomOnBoundingBoxChangedListener{
            override fun onChanged(rec: RectF) {
                actualCropRect = rec
            }

        })
        return view
    }

    var startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                imageUri = it.data?.data.toString()
                val image: Bitmap = getBitmap(requireContext(), null, imageUri, ::UriToBitmap)

               imageView.layoutParams =  imageView.layoutParams.apply {
                    width = image.width
                    height = image.height
                }
                imageView.setImageBitmap(image)
                imageView.background = BitmapDrawable(image)
            }
        }

}