package com.example.adminpanelfood

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminpanelfood.Adapter.pendingadaper
import com.example.adminpanelfood.databinding.ActivityPendingorderBinding

class pendingorder : AppCompatActivity() {
    private val binding : ActivityPendingorderBinding by lazy{
        ActivityPendingorderBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val customernames = arrayOf<String>("Shravan","Shryam","Preeti","priyam")
        val orderquantity = arrayOf<Int>(1,2,3,4)
        val image = arrayOf<Int>(R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo)

        val adapter = pendingadaper(ArrayList(customernames.toList()), ArrayList(orderquantity.toList()), ArrayList(image.toList()),this)
        binding.recypending.layoutManager = LinearLayoutManager(this)
        binding.recypending.adapter = adapter


    }
}