package com.mahmouddev.database.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val name: String,
    val age: Int,
    val rate: Double,
    val isGraduated: Int,
    var id: Int = 0
    ) : Parcelable


const val TABLE_STD = "student_tb"
const val ID = "id"
const val NAME = "name"
const val AGE = "age"
const val RATE = "rate"
const val IS_GRADUATED = "isGraduated"

const val STUDENT_CREATE_TABLE =
    "CREATE TABLE IF NOT EXISTS " + TABLE_STD + " (" +
            "$ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$NAME TEXT," +
            "$AGE INTEGER," +
            "$RATE DOUBLE," +
            "$IS_GRADUATED INTEGER" +

            ")"