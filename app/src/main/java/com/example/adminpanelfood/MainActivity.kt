package com.example.adminpanelfood

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.adminpanelfood.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.additem.setOnClickListener {
            val intent = Intent(this, additem::class.java)
            startActivity(intent)
        }
        binding.allitem.setOnClickListener {
            val intent = Intent(this, allitemsact::class.java)
            startActivity(intent)
        }
        binding.outfordelivery.setOnClickListener {
            val intent = Intent(this, outfordelivery::class.java)
            startActivity(intent)
        }
        binding.adminprofile.setOnClickListener {
            val intent = Intent(this, Adminprofile::class.java)
            startActivity(intent)
        }
        binding.newuser.setOnClickListener {
            val intent = Intent(this, adduser::class.java)
            startActivity(intent)
        }
        binding.imageView5.setOnClickListener {
            val intent = Intent(this, pendingorder::class.java)
            startActivity(intent)
        }
//        binding.textView5.setOnClickListener {
//            val intent = Intent(this, pendingorder::class.java)
//            startActivity(intent)
//        }
    }
}