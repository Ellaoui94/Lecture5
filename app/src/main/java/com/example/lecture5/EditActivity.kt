package com.example.lecture5

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.edmodo.cropper.CropImageView
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment1)

        val oldSelectedStudent : StudentInfo = (intent.getSerializableExtra("selected_student") as StudentInfo)

        val nameView: EditText = findViewById<EditText>(R.id.textOne)
        nameView.setText(oldSelectedStudent.name)

        val surnameView: EditText = findViewById<EditText>(R.id.textTwo)
        surnameView.setText(oldSelectedStudent.surname)

        val imageView: CropImageView1 = findViewById(R.id.imageView)
        imageView.isEnabled = false;

        thread {
            var image : Bitmap? = null

            if (oldSelectedStudent.url.toString().startsWith("http")){
                with(URL(oldSelectedStudent.url).openConnection() as HttpURLConnection){
                    requestMethod = "GET"

                    setRequestProperty(
                        "User-Agent",
                        "Mozilla/5.0 (Macintosh; U; Intel Mac OS X; ja-JP-mac; rv:1.8.1.6) Gecko/20070725 Firefox/2.0.0.6"
                    )

                    val bm : Bitmap = BitmapFactory.decodeStream(inputStream)

                    imageView.post {imageView.setImageBitmap(bm)}
                }
            } else {
                image = if (oldSelectedStudent.url != null)
                    getBitmap(
                this,
                null,
                oldSelectedStudent.url,
                ::UriToBitmap)
                else getBitmap(
                    this,
                    R.drawable.ic_launcher_foreground,
                    null,
                    ::VectorDrawableToBitmap
                )

                image = Bitmap.createScaledBitmap(
                    image,
                    oldSelectedStudent.imageH,
                    oldSelectedStudent.imageW,
                    false
                )

                if (oldSelectedStudent.url != null){
                    image = Bitmap.createBitmap(
                        image,
                        oldSelectedStudent.x,
                        oldSelectedStudent.y,
                        oldSelectedStudent.w,
                        oldSelectedStudent.h
                    )

                    image = Bitmap.createScaledBitmap(
                        image,
                        (resources.displayMetrics.density * 200).toInt(),
                        (resources.displayMetrics.density * 200).toInt(),
                        false
                    )
                }

                imageView.post{imageView.setImageBitmap(image)}
            }

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