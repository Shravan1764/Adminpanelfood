package com.example.adminpanelfood

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class splashadmin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashadmin)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@splashadmin, loginactivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}