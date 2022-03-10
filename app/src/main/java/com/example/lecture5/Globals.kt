package com.example.lecture5

import android.text.Editable
import java.io.Serializable
import kotlin.random.Random

object Globals {
    val TAG = "AndroidLifeCycle"
}

data class StudentInfo(var name: String, var surname: String, var position: Int=-1): Serializable {
}

object StudentInfoTester{

    fun createRandomStudents(amount: Int): ArrayList<StudentInfo>{

        val generatedList: ArrayList<StudentInfo> = ArrayList<StudentInfo>()

        repeat(amount){
            var randomName: String = Random.generateRandomString(5..10)
            randomName = ""+randomName[0].toUpperCase() + randomName.subSequence(1,randomName.length)
            var randomSurname: String = Random.generateRandomString(8..15)
            randomSurname = ""+randomSurname[0].toUpperCase() + randomSurname.subSequence(1,randomSurname.length)

            generatedList.add(
                StudentInfo(
                    randomName,
                    randomSurname,
                )
            )
        }

        return generatedList
    }
}

fun Random.generateRandomString(intRange: IntRange): String {
    var randomString: String = ""
    repeat(intRange.random()){ randomString += ('a'..'z').random().toString() }
    return randomString
}