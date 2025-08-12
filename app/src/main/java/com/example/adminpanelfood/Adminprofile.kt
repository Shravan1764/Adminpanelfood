package com.example.adminpanelfood

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminpanelfood.databinding.ActivityAdminprofileBinding

class Adminprofile : AppCompatActivity() {
    private val binding : ActivityAdminprofileBinding by lazy {
        ActivityAdminprofileBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.editTextText2.isEnabled = false
        binding.editTextText3.isEnabled = false
        binding.editTextText4.isEnabled = false
        binding.editTextText8.isEnabled = false
        binding.editTextText5.isEnabled = false

        var isenable = false

        binding.startedit.setOnClickListener {
            isenable = ! isenable

            binding.editTextText2.isEnabled = isenable
            binding.editTextText3.isEnabled = isenable
            binding.editTextText4.isEnabled = isenable
            binding.editTextText8.isEnabled = isenable
            binding.editTextText5.isEnabled = isenable

        }
    }
}