package com.example.tenword.data

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.tenword.model.Question

@Database(entities = arrayOf(Question::class), version = 1)
abstract class QuestionDb: RoomDatabase() {
    abstract fun questionDao():QuestionDao

    companion object {
        @Volatile
        var INSTANCE: QuestionDb? = null

        fun getInstance(context: Context): QuestionDb {
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    QuestionDb::class.java,
                    "question_db"
                ).addCallback(roomDbCallback)
                    .build()
            }
            return INSTANCE as QuestionDb
        }

        private val roomDbCallback = object :RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                PoulateAsyncTask(INSTANCE).execute()
            }
        }

        class PoulateAsyncTask(private val db: QuestionDb?):AsyncTask<Void, Void, Void>(){
            private val dao:QuestionDao? by lazy {
                db?.questionDao()
            }

            override fun doInBackground(vararg params: Void?): Void? {
                var question = Question (
                    question = "teleurgesteld",
                    optionA = "sevmek",
                    optionB = "nefret",
                    optionC = "sok olmak",
                    answer = "hayal kirikligi"
                )
                dao?.addQuestion(question)

                var question1 = Question (
                    question = "begrijpen",
                    optionA = "konusmak",
                    optionB = "uyumak",
                    optionC = "icmek",
                    answer = "anlamak"
                )
                dao?.addQuestion(question1)
                var question2 = Question (
                    question = "volharden",
                    optionA = "usenmek",
                    optionB = "donmek",
                    optionC = "yatmak",
                    answer = "azmetmek"
                )
                dao?.addQuestion(question2)
                return null
            }
        }
    }
}