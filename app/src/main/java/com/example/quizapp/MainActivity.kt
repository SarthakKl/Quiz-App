package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start=findViewById<Button>(R.id.start)
        val name=findViewById<EditText>(R.id.name)

        start.setOnClickListener {
            if(name.text.isEmpty())
                Toast.makeText(this, ":Please enter your name", Toast.LENGTH_SHORT).show()
            else{
                val ques=Intent(this,QuizQuestions::class.java)
                ques.putExtra(Constants.UserName,name.text.toString())
                startActivity(ques)
                finish()
            }
        }

    }
}