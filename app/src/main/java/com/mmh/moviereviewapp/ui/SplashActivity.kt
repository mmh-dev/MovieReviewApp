package com.mmh.moviereviewapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.mmh.moviereviewapp.R
import com.mmh.moviereviewapp.databinding.ActivityMoviesBinding
import com.mmh.moviereviewapp.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme)
        } else {
            setTheme(R.style.Theme_MovieReviewApp)
        }

        /** спорное решение с GlobalScope, если бы не требование в ТЗ использовать splash экран
         в отдельном активити, я бы вообще сделал splash экран с новым SplashScreen API без создания
         отдельного активити для него
        **/
        GlobalScope.launch {
            delay(2000)
            startActivity(Intent(this@SplashActivity, MoviesActivity::class.java))
            finish()
        }
    }
}