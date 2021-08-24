package com.mahmouddev.database.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mahmouddev.database.AddActivity
import com.mahmouddev.database.R
import com.mahmouddev.database.database.DatabaseHelper
import com.mahmouddev.database.databinding.ItemStdBinding
import com.mahmouddev.database.model.Student

class StudentAdapter(val context: Context, val data: ArrayList<Student>) :
    RecyclerView.Adapter<StudentAdapter.MyHolder>() {
    lateinit var dbHelper: DatabaseHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val binding = ItemStdBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class MyHolder(val binding: ItemStdBinding) : RecyclerView.ViewHolder(binding.root) {


        init {
            dbHelper = DatabaseHelper(context)
        }

        fun bind(student: Student) {

            binding.apply {
                tvName.text = student.name
                tvAge.text = "${student.age}العمر: "
                tvRate.text = student.rate.toString()

                if (student.rate >= 60) {
                    tvIsSuccess.text = "ناجح"
                    tvIsSuccess.setTextColor(ContextCompat.getColor(context, R.color.teal_700))
                } else {
                    tvIsSuccess.text = "راسب"
                    tvIsSuccess.setTextColor(ContextCompat.getColor(context, R.color.red))

                }

                if (student.isGraduated == 1) {
                    tvGraduate.text = "خريج"
                    tvGraduate.setTextColor(ContextCompat.getColor(context, R.color.teal_700))
                    tvGraduate.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_true, 0, 0, 0)
                } else {
                    tvGraduate.text = "غير خريج"
                    tvGraduate.setTextColor(ContextCompat.getColor(context, R.color.red))
                    tvGraduate.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_false, 0, 0, 0)

                }

                if (student.rate >= 60) {
                    tvRate.setTextColor(ContextCompat.getColor(context, R.color.teal_700))
                    tvRate.background =
                        ContextCompat.getDrawable(context, R.drawable.sh_rate_success)

                } else {
                    tvRate.setTextColor(ContextCompat.getColor(context, R.color.red))
                    tvRate.background = ContextCompat.getDrawable(context, R.drawable.sh_rate_fail)
                }

                imgDelete.setOnClickListener {
                    if (dbHelper.delete(student.id)) {
                        data.removeAt(adapterPosition)
                        notifyItemRemoved(adapterPosition)
                        Toast.makeText(context, "تم الحذف بنجاح", Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(context, "حدث خطأ ما", Toast.LENGTH_LONG).show()

                    }
                }

                container.setOnLongClickListener {
                    Log.e("TAG", "bind: ${student.id}")
                    val intent = Intent(context, AddActivity::class.java)
                    intent.putExtra("std", student)
                    context.startActivity(intent)

                    true
                }

            }

        }

    }
}