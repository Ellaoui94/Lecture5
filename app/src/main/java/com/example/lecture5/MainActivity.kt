package com.example.lecture5

import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private var fragmentManager: FragmentManager = supportFragmentManager

    private var animalList = ArrayList<StudentInfo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*
        studentsInfo = StudentInfoTester.createRandomStudents(10)
*/

      //  val result:String = StudentInfoTester.getAnimalData("https://zoo-animal-api.herokuapp.com/animals/rand/2")

/*
       val textView = findViewById<FrameLayout>(R.id.textView)
*/

        val url = URL("https://zoo-animal-api.herokuapp.com/animals/rand/10")

        thread {
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "GET"

                inputStream.bufferedReader().lines().forEach {
                    //  Log.i(Globals.TAG, it)

                    val json : JSONArray = JSONArray(it)

                    //        val url = URL("https://zoo-animal-api.herokuapp.com/animals/rand/10").readText()
                    for (index in 0 until json.length()){
                        val name : String = (json.get(index) as JSONObject).get("name").toString()
                        val id : Int = Integer.parseInt((json.get(index) as JSONObject).get("id").toString())
                        val animalType : String = (json.get(index) as JSONObject).get("animal_type").toString()
                        val image : String = (json.get(index) as JSONObject).get("image_link").toString()

                        Log.i(Globals.TAG, "$name $animalType $image ")

                        animalList.add(StudentInfo(
                            name,
                            animalType,
                            image,
                            0,0,0,0,0, 0
                        )
                        )
                    }
                }
            }
        }
    }

    fun switchFragment(v: View) {

        fragmentManager = supportFragmentManager

        if (Integer.parseInt(v.getTag().toString()) == 1) {
            fragmentManager.beginTransaction().replace(R.id.main, Fragment1(), "Fragment1")
                .commit()
        } else {
            fragmentManager.beginTransaction()
                .replace(R.id.main, Fragment2(animalList), "Fragment2").commit()
        }
    }

    fun submit(view: View) {
        var nameViewText =
            (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).nameView.text.toString()
        var surnameView =
            (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).surnameView.text.toString()
        var imageUri = (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).imageUri.toString()

        var imageRect =  (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).actualCropRect!!
        var imgW = (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).imageView.width
        var imgH = (fragmentManager.findFragmentByTag("Fragment1") as Fragment1).imageView.height

        val newStudent = StudentInfo(nameViewText, surnameView, imageUri, imageRect.left.toInt(), imageRect.top.toInt(), imageRect.right.toInt(), imageRect.bottom.toInt(), imgH.toInt(), imgW.toInt() )
        animalList.add(newStudent)

        Toast.makeText(this, "Added new student", Toast.LENGTH_SHORT).show()
    }


}