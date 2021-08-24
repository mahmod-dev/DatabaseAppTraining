package com.mahmouddev.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mahmouddev.database.adapter.StudentAdapter
import com.mahmouddev.database.database.DatabaseHelper
import com.mahmouddev.database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    //  var binding : ActivityMainBinding?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("MainActivity","onCreate")


    }

    override fun onStart() {
        super.onStart()
        Log.e("MainActivity","onStart")


        val dbHelper = DatabaseHelper(this)

        binding.apply {

            fab.setOnClickListener {
                startActivity(Intent(this@MainActivity, AddActivity::class.java))
            }

            rvStudent.adapter = StudentAdapter(this@MainActivity,dbHelper.getAllStudents())


        }
    }
}