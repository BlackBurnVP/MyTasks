package com.vitalii.mytasks

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes.*

class AddNotes : AppCompatActivity() {

    private var dbTable = "Notes"
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)

        id = intent.getIntExtra("ID",0)
        if(id!=0){
            editTitle.setText(intent.getStringExtra("Title"))
            editDesc.setText(intent.getStringExtra("Description"))
        }

        btnAdd.setOnClickListener(addNote)
    }

    val addNote = View.OnClickListener{
        val dbManager = DBManager(this)
        val values = ContentValues()
        values.put("Title", editTitle.text.toString())
        values.put("Description", editDesc.text.toString())
        if(id==0){
            val ID = dbManager.insert(values)
            if (ID>0){
                Toast.makeText(this,"Note is added",Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Cannot add note",Toast.LENGTH_SHORT).show()
            }
        }else{
            val selectionArgs = arrayOf(id.toString())
            val ID = dbManager.update(values,"ID=?",selectionArgs)
            if (ID>0){
                Toast.makeText(this,"Note is added",Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Cannot add note",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
