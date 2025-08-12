package com.example.adminpanelfood

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminpanelfood.Adapter.allitemadapter
import com.example.adminpanelfood.databinding.ActivityAllitemsactBinding
import java.util.ArrayList

class allitemsact : AppCompatActivity() {
    private val binding : ActivityAllitemsactBinding by lazy{
        ActivityAllitemsactBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val foodnames = arrayListOf<String>("Burger","Noodels","Pizza","Salad","Momos")
        val itemprice = arrayListOf<String>("200Rs","100Rs","300Rs","150Rs","250Rs")
        val foodimage = arrayListOf<Int>(R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo)

        val adapter = allitemadapter(foodnames,itemprice,foodimage)
        binding.allitemrecycler.layoutManager = LinearLayoutManager(this)
        binding.allitemrecycler.adapter = adapter
    }
}