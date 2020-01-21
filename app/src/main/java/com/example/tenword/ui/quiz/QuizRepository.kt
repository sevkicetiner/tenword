package com.example.tenword.ui.quiz

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.tenword.data.QuestionDao
import com.example.tenword.data.QuestionDb
import com.example.tenword.model.Question

class QuizRepository (context: Context){

    private val db by lazy { QuestionDb.getInstance(context) }
    private val dao: QuestionDao by lazy { db.questionDao()}

    fun getAllQuestions(): LiveData<List<Question>> {
        return dao.getQuestions()
    }
}