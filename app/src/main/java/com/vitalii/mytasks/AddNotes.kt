package com.vitalii.mytasks

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_notes.*
import kotlinx.android.synthetic.main.task_view.*

class AddNotes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        btnAdd.setOnClickListener(addNote)
    }

    val addNote = View.OnClickListener{
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("title", txtTitle.text)
        intent.putExtra("description",txtContent.text)
        startActivity(intent)
    }
}
