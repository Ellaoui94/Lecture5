package com.example.lecture5

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.edmodo.cropper.CropImageView

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment1)

        val oldSelectedStudent : StudentInfo = (intent.getSerializableExtra("selected_student") as StudentInfo)

        val nameView: EditText = findViewById<EditText>(R.id.textOne)
        nameView.setText(oldSelectedStudent.name)

        val surnameView: EditText = findViewById<EditText>(R.id.textTwo)
        surnameView.setText(oldSelectedStudent.surname)


        if (oldSelectedStudent.url != null) {
            val imageView: CropImageView1 = findViewById(R.id.imageView)
            var image: Bitmap = getBitmap(this, null, oldSelectedStudent.url, ::UriToBitmap)
            image = Bitmap.createBitmap(image, oldSelectedStudent.x, oldSelectedStudent.y, oldSelectedStudent.w, oldSelectedStudent.h)
/*
            imageView.setImageBitmap(image)
*/
            imageView.background = BitmapDrawable(image)
        }

        val submitButton: Button = findViewById<Button>(R.id.submit)
        submitButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val updatedSelectedStudent: StudentInfo = oldSelectedStudent.copy()
                updatedSelectedStudent.name = nameView.text.toString()
                updatedSelectedStudent.surname = surnameView.text.toString()

                val intent = Intent()
                intent.putExtra("selected_student", updatedSelectedStudent)
                setResult(RESULT_OK, intent)
                finish()
            }
        })
    }
}