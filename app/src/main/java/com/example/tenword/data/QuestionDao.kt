package com.example.tenword.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tenword.model.Question


@Dao
interface QuestionDao {
    @Query("SELECT * From questions")
    fun getQuestions():LiveData<List<Question>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addQuestion(question: Question)

    @Query("DELETE FROM questions")
    fun deleteAll()
}