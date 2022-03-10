package com.example.lecture5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    private var fragmentManager: FragmentManager = supportFragmentManager

    private var studentsInfo = ArrayList<StudentInfo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentsInfo = StudentInfoTester.createRandomStudents(10)

/*
       val textView = findViewById<FrameLayout>(R.id.textView)
*/
    }

    fun switchFragment(v: View) {

        fragmentManager = supportFragmentManager

        if (Integer.parseInt(v.getTag().toString()) == 1) {
            fragmentManager.beginTransaction().replace(R.id.main, Fragment1(), "Fragment1")
                .commit()
        } else {
            fragmentManager.beginTransaction()
                .replace(R.id.main, Fragment2(studentsInfo), "Fragment2").commit()
        }
    }

    fun submit(view: View) {
        var nameViewText =
            (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).nameView.text.toString()
        var surnameView =
            (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).surnameView.text.toString()

        val newStudent = StudentInfo(nameViewText, surnameView)
        studentsInfo.add(newStudent)

        Toast.makeText(this, "Added new student", Toast.LENGTH_SHORT).show()
    }

}