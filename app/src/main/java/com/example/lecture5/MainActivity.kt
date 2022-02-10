package com.example.lecture5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    private var fragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*
       val textView = findViewById<FrameLayout>(R.id.textView)
*/
    }

    fun switchFragment(v: View) {
        if (Integer.parseInt(v.getTag().toString()) == 1){
            fragmentManager.beginTransaction().replace(R.id.textView, Fragment1(), "Fragment1").commit()
        }else {
            fragmentManager.beginTransaction().replace(R.id.textView, Fragment2(), "Fragment2").commit()
        }
    }

    fun submit(view: View){
        var nameViewText = (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).nameView.text
        var surnameView = (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).surnameView.text

        val newStudent : StudentInfo = StudentInfo()
    }
}