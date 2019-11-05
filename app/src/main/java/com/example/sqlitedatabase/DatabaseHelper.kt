package com.example.sqlitedatabase

import android.content.ContentValues
import android.content.Context //provide access to application resources
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import org.w3c.dom.Text

/*
* reference:
* ==========
* developer.android.com/training/data-storage/sqlite
* developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper.html
* * */

class DatabaseHelper(context:Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,1){

    //for static members declared purpose 'companion object'
    companion object {
        val DATABASE_NAME="college.db" //DB name
        val TABLE_NAME="stud_table"     //table name
        val COL_1="ID"
        val COL_2="FNAME"
        val COL_3="LNAME"
        val COL_4="DOB"
    }

    //CREATE TABLE TO DB
    override fun onCreate(db: SQLiteDatabase) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db.execSQL("CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY "+" AUTOINCREMENT,FNAME TEXT," +
                "LNAME TEXT,DOB TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db.execSQL("DROP TABLE IF EXISTS"+ TABLE_NAME)
    }


    //INSERT
    fun insertData(name:String,surname:String,dob:String){
        val db=this.writableDatabase
        val contentvalues=ContentValues()
        contentvalues.put(COL_2,name)
        contentvalues.put(COL_3,surname)
        contentvalues.put(COL_4,dob)
        db.insert(TABLE_NAME,null,contentvalues)
    }

    //UPDATE
    fun updateDate(id:String,name: String,surname: String,dob: String):Boolean
    {
        val db=this.writableDatabase
        val contentvalues=ContentValues()
        contentvalues.put(COL_1,id)
        contentvalues.put(COL_2,name)
        contentvalues.put(COL_3,surname)
        contentvalues.put(COL_4,dob)
        db.update(TABLE_NAME,contentvalues,"ID=?",arrayOf(id))
        return true
    }

    //DELETE
    fun deleteDate(id: String):Int{
        val db=this.writableDatabase
        return db.delete(TABLE_NAME,"ID=?", arrayOf(id))
    }

    val showData:Cursor
    get() {
        val db=this.writableDatabase
        val result=db.rawQuery("SELECT * FROM "+ TABLE_NAME,null)
        return  result
    }
}