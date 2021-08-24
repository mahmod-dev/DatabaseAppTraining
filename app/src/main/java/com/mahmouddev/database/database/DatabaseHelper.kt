package com.mahmouddev.database.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.mahmouddev.database.model.*

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "db-students", null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(STUDENT_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_STD ")
        onCreate(db)
    }


    fun insert(student: Student): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, student.name)
        contentValues.put(AGE, student.age)
        contentValues.put(RATE, student.rate)
        contentValues.put(IS_GRADUATED, student.isGraduated)
        // Inserting Row
        val success = db.insert(TABLE_STD, null, contentValues) > 0

        db.close()
        return success
    }


    fun getAllStudents(): ArrayList<Student> {
        val empList: ArrayList<Student> = ArrayList()
        val selectQuery = "SELECT  * FROM $TABLE_STD"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(ID))
                val name = cursor.getString(cursor.getColumnIndex(NAME))
                val age = cursor.getInt(cursor.getColumnIndex(AGE))
                val rate = cursor.getDouble(cursor.getColumnIndex(RATE))
                val isGraduate = cursor.getInt(cursor.getColumnIndex(IS_GRADUATED))
                val emp = Student(name, age, rate, isGraduate, id)
                empList.add(emp)

            } while (cursor.moveToNext())
        }
        cursor.close()
        return empList
    }

    fun update(student: Student): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, student.name)
        contentValues.put(AGE, student.age)
        contentValues.put(RATE, student.rate)
        contentValues.put(IS_GRADUATED, student.isGraduated)
        // Updating Row
        val success = db.update(TABLE_STD, contentValues, "$ID = ${student.id}", null) > 0
        db.close()
        return success
    }


    fun delete(id: Int): Boolean {
        val db = this.writableDatabase
        val success = db.delete(TABLE_STD, "$ID = $id", null) > 0
        db.close()
        return success
    }
}