package com.vitalii.mytasks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.task_view.view.*

class MainActivity : AppCompatActivity() {

    private var listNotes = ArrayList<Notes>()
    private var adapter:BaseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNotes()

        adapter = notesAdapter(listNotes)
        listTasks.adapter = adapter
    }

    private fun addNotes(){
        for (i in 1..10){
            listNotes.add(Notes(i,"Note no. $i","Description of note no $i"))
        }
    }

    inner class notesAdapter:BaseAdapter{

        var listNotes = ArrayList<Notes>()
        constructor(listNotes:ArrayList<Notes>):super(){
            this.listNotes = listNotes
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val myView = layoutInflater.inflate(R.layout.task_view,null)
            myView.txtTitle.text = listNotes[position].noteName
            myView.txtContent.text = listNotes[position].noteDes
            return myView
        }

        override fun getItem(position: Int): Any {
            return listNotes[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listNotes.size
        }



    }
}
