package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val username=findViewById<TextView>(R.id.username)
        val score=findViewById<TextView>(R.id.score)
        val finish=findViewById<Button>(R.id.finish_btn)

        username.text=intent.getStringExtra(Constants.UserName).toString()
        score.text="Your score is "+intent.getStringExtra(Constants.correctAns)+"/"+intent.getStringExtra(Constants.totalQues)

        finish.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }
}