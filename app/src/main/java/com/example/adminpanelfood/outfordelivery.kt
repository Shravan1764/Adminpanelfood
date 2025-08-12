package com.example.adminpanelfood

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminpanelfood.Adapter.deliveryadapter
import com.example.adminpanelfood.databinding.ActivityOutfordeliveryBinding

class outfordelivery : AppCompatActivity() {

    private val binding : ActivityOutfordeliveryBinding by lazy {
        ActivityOutfordeliveryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val customername = arrayListOf<String>("Shravan","Priya","Rakesh")
        val moneystate = arrayListOf<String>("recieved","Not Recieved","Pending")
        val adarpter = deliveryadapter(customername,moneystate)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adarpter

    }
}