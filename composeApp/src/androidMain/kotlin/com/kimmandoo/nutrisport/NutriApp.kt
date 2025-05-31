package com.kimmandoo.nutrisport

import android.app.Application
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.initialize

class NutriApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(context = this)
    }
}