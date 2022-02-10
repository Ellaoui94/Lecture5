package com.example.lecture5

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class Fragment1: Fragment() {
    lateinit var nameView: EditText
    lateinit var surnameView: EditText



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment1,container, false)

        view.setBackgroundColor(Color.WHITE)

        nameView = view.findViewById(R.id.text)
        surnameView = view.findViewById(R.id.text2)

        return view
    }

}