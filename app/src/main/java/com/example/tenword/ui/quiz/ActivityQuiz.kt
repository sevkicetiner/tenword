package com.example.tenword.ui.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tenword.R
import com.example.tenword.model.Question
import com.example.tenword.ui.result.ResultActivity
import com.example.tenword.util.Constans
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz.*

class ActivityQuiz : AppCompatActivity() {
    var qIndex = 0
    lateinit var viewModel: QuizViewModel
    private var questionList:List<Question> = arrayListOf()
    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        viewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
        viewModel.allQuestions.observe(this, Observer {
            if (it.isNotEmpty()){
                questionList = it
                setViews()
                next_button.setOnClickListener {
                    val answer:Chip = findViewById<Chip>(chip_grup.checkedChipId)

                    checkAnswer(answer)
                    qIndex++

                    if(qIndex<questionList.size) {
                        setViews()
                    } else {
                        startActivity(Intent(this, ResultActivity::class.java).putExtra(Constans.EXTRA_RESULT, score))
                    }
                    chip_grup.clearCheck()
                }


            }
        })
    }

    private fun checkAnswer(answer:Chip){
        if (questionList[qIndex].answer == answer.text){
            Toast.makeText(this, "Dogru Cevap", Toast.LENGTH_SHORT).show()
            score++
        } else {
            Toast.makeText(this, "yanlis cevap \n Cevap : ${questionList[qIndex].answer}", Toast.LENGTH_SHORT).show()
        }
    }

    fun setViews(){

        textview_question.text = questionList[qIndex].question
        chip_option_a.text = questionList[qIndex].optionA
        chip_option_b.text = questionList[qIndex].optionB
        chip_option_c.text = questionList[qIndex].optionC
        chip_option_d.text = questionList[qIndex].answer
    }
}
