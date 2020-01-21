package com.example.tenword

import android.app.Application
import com.facebook.stetho.Stetho

class QuizApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
    }
}