package com.example.tenword.ui.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tenword.R
import com.example.tenword.util.Constans
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        score = intent.getIntExtra(Constans.EXTRA_RESULT,0)

        textview_result.text = score.toString()
    }
}
