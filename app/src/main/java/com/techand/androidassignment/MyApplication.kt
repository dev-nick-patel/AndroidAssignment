package com.techand.androidassignment

import android.app.Application
import androidx.preference.PreferenceManager
import com.techand.androidassignment.util.Constants.Companion.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Prefs = PreferenceManager.getDefaultSharedPreferences(this)
    }
}