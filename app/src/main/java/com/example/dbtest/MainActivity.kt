package com.example.dbtest

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dbtest.db1.DBconnection
import com.example.dbtest.db1.DBqueries
import com.example.dbtest.db1.u.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.sql.Connection

class MainActivity : AppCompatActivity() {

    private var email : EditText?= null
    private var password : EditText?= null
    private var name : EditText?= null
    private var sentIt: Button?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        email= findViewById(R.id.emailED)
        password= findViewById(R.id.passwordED)
        name= findViewById(R.id.nameED)
        sentIt= findViewById(R.id.SENTITBRO)

        sentIt?.setOnClickListener {

           val nameT= name?.text.toString()
           val passwordT= password?.text.toString()
           val emailT= email?.text.toString()

           val user= User(nameT, emailT, passwordT)

            runBlocking{launch (Dispatchers.IO){
                insertUser(user)
            }
         }

       }
    }

    private suspend fun insertUser(user: User){
        withContext(Dispatchers.IO){
            val connection: Connection= DBconnection.getConnection()
            val dbqueries= DBqueries(connection)
            val result= dbqueries.insertUser(user)
            connection.close()

            withContext(Dispatchers.Main){
                if(result){
                    Toast.makeText(this@MainActivity, "User inserted", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@MainActivity, "User insertion failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}