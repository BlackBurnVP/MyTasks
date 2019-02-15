package com.vitalii.mytasks

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes.*

class AddNotes : AppCompatActivity() {

    private var dbTable = "Notes"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        btnAdd.setOnClickListener(addNote)
    }

    val addNote = View.OnClickListener{
        val dbManager = DBManager()
        val dbHelper = dbManager.DatabaseHelper(this)
        val sqlDB = dbHelper.writableDatabase
        val values = ContentValues()
        values.put("Title", editTitle.text.toString())
        values.put("Description", editDesc.text.toString())
        val ID = dbManager.insert(values,sqlDB)
        if (ID>0){
            Toast.makeText(this,"Note is added",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Cannot add note",Toast.LENGTH_SHORT).show()
        }
        //TODO : Video on 21:36
//        val intent = Intent(this,MainActivity::class.java)
//        intent.putExtra("title", txtTitle.text)
//        intent.putExtra("description",txtContent.text)
//        startActivity(intent)
    }
}
