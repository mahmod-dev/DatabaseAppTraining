package com.mahmouddev.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.mahmouddev.database.database.DatabaseHelper
import com.mahmouddev.database.databinding.ActivityAddBinding
import com.mahmouddev.database.model.Student

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    var graduate = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dbHelper = DatabaseHelper(this)

        val student = intent.getParcelableExtra<Student>("std")




        binding.apply {

            if (student != null) {
                // update
                tvAdd.text = "تعديل طالب"
                btnAdd.text = "تعديل"

                etName.setText(student.name)
                etAge.setText(student.age.toString())
                etRate.setText(student.rate.toString())

                if (student.isGraduated == 1) {
                    cbGraduate.isChecked = true
                } else {
                    cbGraduate.isChecked = false

                }

            }

            btnAdd.setOnClickListener {
                val name = etName.text.toString()
                val age = etAge.text.toString().toInt()
                val rate = etRate.text.toString().toDouble()

                if (cbGraduate.isChecked) {
                    graduate = 1
                }

                if (student != null) {
                    //update
                    val std = Student(name, age, rate, graduate,student.id)

                    Log.e("TAG", "onCreate id: ${std.id}", )

                    if (dbHelper.update(std)) {
                        finish()
                    } else {
                        Toast.makeText(this@AddActivity, "حدث خطأ ما في عملية التعديل", Toast.LENGTH_LONG).show()
                    }

                } else {
                    // insert
                    val std = Student(name, age, rate, graduate)

                    if (dbHelper.insert(std)) {
                        finish()
                    } else {
                        Toast.makeText(this@AddActivity, "حدث خطأ ما في عملية الاضافة", Toast.LENGTH_LONG).show()
                    }

                }


            }
        }

    }
}