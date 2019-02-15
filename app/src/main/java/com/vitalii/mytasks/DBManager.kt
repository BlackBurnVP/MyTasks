package com.vitalii.mytasks

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBManager {

    val dbName:String = "MyNotes"
    val dbTable:String = "Notes"
    val colID = "ID"
    val colTitle = "Title"
    val colDesc = "Description"
    val dbVersion = 2
    val sqlCreateTable = "CREATE TABLE IF NOT EXISTS $dbTable ($colID INTEGER PRIMARY KEY, $colTitle TEXT, $colDesc TEXT);"
    var sqlDB:SQLiteDatabase? = null

    fun createDB(context: Context) {
        //Toast.makeText(context,"SMTH",Toast.LENGTH_LONG).show()
        val db = DatabaseHelper(context)
        sqlDB = db.writableDatabase
    }

    fun insert(values:ContentValues,sqlDB:SQLiteDatabase):Long{
        val ID = sqlDB!!.insert(dbTable,"",values)
        return ID
    }

    inner class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {
        var context:Context? = context
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCreateTable)
            Toast.makeText(context,"Database is created",Toast.LENGTH_SHORT).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table IF EXISTS $dbTable")
        }

    }
}