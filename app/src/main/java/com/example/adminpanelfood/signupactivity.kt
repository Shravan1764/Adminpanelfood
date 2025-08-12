package com.example.adminpanelfood

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.adminpanelfood.databinding.ActivitySignupactivityBinding
import com.example.adminpanelfood.model.usermodel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class signupactivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var restname: String
    private lateinit var database: DatabaseReference

    private val binding: ActivitySignupactivityBinding by lazy {
        ActivitySignupactivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val loclist = arrayOf("Jaipur", "Orissa", "Jhansi", "Agra")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, loclist)
        binding.listoflocation.setAdapter(adapter)

        // innetialize the firebase auth
        auth = Firebase.auth
        // innitialize the database
        database = Firebase.database.reference

        binding.signupbtn.setOnClickListener {
            name = binding.editTextText.text.toString().trim()
            restname = binding.editTextText1.text.toString().trim()
            email = binding.editTextTextEmailAddress.text.toString().trim()
            password = binding.editTextTextPassword.text.toString().trim()

            if (name.isBlank() || restname.isBlank() || email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                createaccount(email, password)
            }
        }
        binding.alrdacc.setOnClickListener {
            val intent = Intent(this, loginactivity::class.java)
            startActivity(intent)
        }
    }

    private fun createaccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                saveuserdata()
                val intent = Intent(this, loginactivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Account creation failed", Toast.LENGTH_SHORT).show()
                Log.d("Account", "account creation failed", task.exception)
            }

        }
    }

    private fun saveuserdata() {

        name = binding.editTextText.text.toString().trim()
        restname = binding.editTextText1.text.toString().trim()
        email = binding.editTextTextEmailAddress.text.toString().trim()
        password = binding.editTextTextPassword.text.toString().trim()

        val user = usermodel(name, restname, email, password)
        val userid = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("user").child(userid).setValue(user)

    }
}