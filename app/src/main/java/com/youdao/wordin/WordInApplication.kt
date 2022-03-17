package com.youdao.wordin

import android.app.Application
import java.lang.Appendable

class WordInApplication : Application() {
    companion object {
        lateinit var instance : WordInApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}