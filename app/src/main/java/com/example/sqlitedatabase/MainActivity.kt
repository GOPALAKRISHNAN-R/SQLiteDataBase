package com.example.sqlitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal var dbhelper = DatabaseHelper(this) //internal variable created for DatabaseHelper class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        insert()
        update()
        delete()
        show()
    }

    //for display purpose
    fun showDialog(title: String, Message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }


    //clear input fields
    fun clear() {
        id.setText("")
        fname.setText("")
        lname.setText("")
        dob.setText("")
    }

    //insert
    fun insert() {
        btn_insert.setOnClickListener {
            try {
                dbhelper.insertData(
                    fname.text.toString(),
                    lname.text.toString(),
                    dob.text.toString()
                ) //call insertData method from DatabaseHelper

                clear() //step 29 called
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    //update
    fun update() {
        btn_update.setOnClickListener {
            try {
                var isupdate = dbhelper.updateDate(
                    id.text.toString(),
                    fname.text.toString(),
                    lname.text.toString(),
                    dob.text.toString()
                )
                if (isupdate == true) {
                    Toast.makeText(this, "Updated Successfully", Toast.LENGTH_LONG).show()
                    clear()
                } else {
                    Toast.makeText(this, "Failed to Upadate", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
            }


        }

    }

    //delete
    fun delete() {
        btn_delete.setOnClickListener {
            try {
                dbhelper.deleteDate(id.text.toString())
                clear()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
            }


        }

    }

    //Show
    fun show(){
        btn_show.setOnClickListener {
            val res=dbhelper.showData

            if(res.count==0)
            {
                Toast.makeText(this, "No data found", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val buffer=StringBuffer()
            while (res.moveToNext())
            {
                buffer.append("ID:"+res.getString(0)+"\n")
                buffer.append("First Name:"+res.getString(1)+"\n")
                buffer.append("Last Name:"+res.getString(2)+"\n")
                buffer.append("DOB:"+res.getString(3)+"\n")
            }
            showDialog("List of Records",buffer.toString())
        }
    }

}