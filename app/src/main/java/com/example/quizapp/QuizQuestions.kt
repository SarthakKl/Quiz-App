package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestions : AppCompatActivity(), View.OnClickListener {
    private val QuesList=Constants.getQuestions()
    private var currentQuestion=1
    private var selectedOption=0
    private var correctAns=0
    private var username:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        username= intent.getStringExtra(Constants.UserName).toString()
        setContentView(R.layout.activity_quiz_questions)
        setQuestion()
        defaultOptionView()

        val option1=findViewById<TextView>(R.id.option1)
        option1.setOnClickListener(this)

        val option2=findViewById<TextView>(R.id.option2)
        option2.setOnClickListener(this)

        val option3=findViewById<TextView>(R.id.option3)
        option3.setOnClickListener(this)

        val option4=findViewById<TextView>(R.id.option4)
        option4.setOnClickListener(this)

        val submit=findViewById<Button>(R.id.submitAndNext)
        submit.setOnClickListener(this)

    }
    private fun setQuestion(){
        val question: Questions =QuesList[currentQuestion-1]

        val bar=findViewById<ProgressBar>(R.id.progressBar)
        bar.progress=currentQuestion

        val progress=findViewById<TextView>(R.id.progress)
        progress.text="$currentQuestion"+"/"+"${bar.max}"

        val image=findViewById<ImageView>(R.id.flag)
        image.setImageResource(question.image)

        val option1=findViewById<TextView>(R.id.option1)
        option1.text= question.option1

        val option2=findViewById<TextView>(R.id.option2)
        option2.text= question.option2

        val option3=findViewById<TextView>(R.id.option3)
        option3.text= question.option3

        val option4=findViewById<TextView>(R.id.option4)
        option4.text= question.option4
    }
    private fun defaultOptionView(){
        val CurrentOptionList= arrayListOf<TextView>()

        val option1=findViewById<TextView>(R.id.option1)
        val option2=findViewById<TextView>(R.id.option2)
        val option3=findViewById<TextView>(R.id.option3)
        val option4=findViewById<TextView>(R.id.option4)

        CurrentOptionList.add(0,option1)
        CurrentOptionList.add(1,option2)
        CurrentOptionList.add(2,option3)
        CurrentOptionList.add(3,option4)

        for(option in CurrentOptionList){
            option.setTextColor(Color.parseColor("#808080"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(this,
                R.drawable.default_option_view
            )
        }
    }
    private fun getOptionView(question:Questions,plata:Boolean):TextView{
        var option:TextView=findViewById(R.id.option1)
        val selection=if(plata){
            selectedOption
        }
        else{
            question.CorrectAns
        }
        when(selection){
            1 -> option= findViewById(R.id.option1)
            2 -> option= findViewById(R.id.option2)
            3 -> option= findViewById(R.id.option3)
            4 -> option= findViewById(R.id.option4)
        }
        return option
    }
    fun submitBtn(){
        val question=QuesList[currentQuestion-1]
        val option:TextView=getOptionView(question,true)


        if(selectedOption==question.CorrectAns){
            option.background=ContextCompat.getDrawable(
                this,R.drawable.correct_option_view
            )
            correctAns+=1
        }
        else{
            val correctView=getOptionView(question,false)
            correctView.background=ContextCompat.getDrawable(
                this,R.drawable.correct_option_view
            )
            correctView.typeface=Typeface.DEFAULT_BOLD
            correctView.setTextColor(Color.parseColor("#FF000000"))

            option.background=ContextCompat.getDrawable(
                this,R.drawable.incorrect_option_view
            )
            option.typeface=Typeface.DEFAULT
            option.setTextColor(Color.parseColor("#42000000"))
        }
    }

    override fun onClick(v: View?) {

        if (v != null) {
            when(v.id){
                R.id.option1 -> selectedOptionView(v as TextView,1)
                R.id.option2 -> selectedOptionView(v as TextView,2)
                R.id.option3 -> selectedOptionView(v as TextView,3)
                R.id.option4 -> selectedOptionView(v as TextView,4)
                R.id.submitAndNext-> {
                    if(selectedOption==0){
                        Toast.makeText(this, "Please Select an Option", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        when((v as Button).text.toString()){
                            "SUBMIT" -> {
                                submitBtn()
                                if(currentQuestion==10) {
                                    val intent=Intent(this,Result::class.java)
                                    intent.putExtra(Constants.UserName,username)
                                    intent.putExtra(Constants.totalQues,"10")
                                    intent.putExtra(Constants.correctAns,"$correctAns")
                                    startActivity(intent)
                                    finish()
                                }
                                v.text="NEXT QUESTION"
                            }
                            "NEXT QUESTION" -> {
                                currentQuestion+=1
                                setQuestion()
                                selectedOption=0
                                defaultOptionView()
                                v.setText(R.string.submit)

                            }
                        }
                    }
                }
            }
        }
    }
    private fun selectedOptionView(tv:TextView,selectedOptionNum:Int){
        defaultOptionView()
        selectedOption=selectedOptionNum

        tv.setTextColor(Color.parseColor("#FF000000"))
        tv.typeface= Typeface.DEFAULT_BOLD
        tv.background=ContextCompat.getDrawable(this,
            R.drawable.selected_option_view
        )
    }
}