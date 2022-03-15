package com.example.lecture5

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment1)

        val oldSelectedStudent : StudentInfo = (intent.getSerializableExtra("selected_student") as StudentInfo)

        val nameView: EditText = findViewById<EditText>(R.id.textOne)
        nameView.setText(oldSelectedStudent.name)

        val surnameView: EditText = findViewById<EditText>(R.id.textTwo)
        surnameView.setText(oldSelectedStudent.surname)



        val submitButton: Button = findViewById<Button>(R.id.submit)
        submitButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val updatedSelectedStudent: StudentInfo = oldSelectedStudent.copy()
                updatedSelectedStudent.name = nameView.text.toString()
                updatedSelectedStudent.surname = surnameView.text.toString()

                val intent: Intent = Intent()
                intent.putExtra("selected_student", updatedSelectedStudent)
                setResult(RESULT_OK, intent);
                finish()
            }
        })
    }
}