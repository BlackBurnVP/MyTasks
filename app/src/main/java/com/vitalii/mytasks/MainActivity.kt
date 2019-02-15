package com.vitalii.mytasks

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.task_view.*
import kotlinx.android.synthetic.main.task_view.view.*

class MainActivity : AppCompatActivity() {

    private var listNotes = ArrayList<Notes>()
    private var adapter:BaseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //addNotes()

        loadFromDB("%") //load ALL from Database
    }

    /**
     * Load data from Database
     * @param title what we want to load from database
     */
    private fun loadFromDB(title:String){
        val projections = arrayOf("ID","Title","Description")
        val dbManager = DBManager(this)
        val selectionArgs = arrayOf(title)
        val myCursor = dbManager.query(projections,"Title like ?",selectionArgs,"Title")
        listNotes.clear()
        if (myCursor.moveToFirst()){
            do {
                val ID = myCursor.getInt(myCursor.getColumnIndex("ID"))
                val Title = myCursor.getString(myCursor.getColumnIndex("Title"))
                val Desc = myCursor.getString(myCursor.getColumnIndex("Description"))

                listNotes.add(Notes(ID,Title,Desc))

            }while (myCursor.moveToNext())
        }
        adapter = NotesAdapter(this,listNotes)
        listTasks.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)

        val searchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                loadFromDB("%$query$%")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.addNote -> {
                val intent = Intent(this,AddNotes::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        loadFromDB("%")
        super.onResume()
    }

    inner class NotesAdapter(context: Context, var listNotes: ArrayList<Notes>) : BaseAdapter() {

        private var context:Context? = context

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val myView = layoutInflater.inflate(R.layout.task_view,null)
            myView.txtTitle.text = listNotes[position].noteName
            myView.txtContent.text = listNotes[position].noteDes
            myView.imgDelete.setOnClickListener{
                val dbManager = DBManager(context!!)
                val selectionArgs = arrayOf(listNotes[position].noteID.toString())
                dbManager.delete("ID=?",selectionArgs)
                loadFromDB("%")
            }
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
