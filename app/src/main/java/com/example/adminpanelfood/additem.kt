package com.example.adminpanelfood

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.adminpanelfood.databinding.ActivityAdditemBinding
import com.example.adminpanelfood.model.allitemsdata
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class additem : AppCompatActivity() {
        private val binding : ActivityAdditemBinding by lazy {
            ActivityAdditemBinding.inflate(layoutInflater)
        }

   private lateinit var foodname  : String
   private  var imageuri : Uri? = null
   private lateinit var foodprice : String
   private lateinit var fooddescription : String
   private lateinit var foodIngedrient : String

   private lateinit var auth: FirebaseAuth
   private lateinit var database : FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.imageupload.setOnClickListener {
            foodname = binding.editTextText2.text.toString().trim()
            foodprice = binding.editTextText3.text.toString().trim()
            fooddescription = binding.editTextText4.text.toString().trim()
            foodIngedrient = binding.editTextText6.text.toString().trim()
            if (!(foodname.isBlank()||foodprice.isBlank()||fooddescription.isBlank()||foodIngedrient.isBlank())){
                uploaddata()
                Toast.makeText(this,"This item Added Successfully",Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()
            }
        }

        binding.imageView14.setOnClickListener {
            pickimage.launch("image/*")
        }
        binding.imageView4.setOnClickListener {
            finish()
        }
    }

    private fun uploaddata() {
        val menuref : DatabaseReference = database.getReference("menu")
        val itemkey:String = menuref.push().key.toString()
        if(imageuri!=null){
                val storage = FirebaseStorage.getInstance().reference
            val image  = storage.child("menu images ${itemkey}.jpg")
            val uploadTask = image.putFile(imageuri!!)

            uploadTask.addOnSuccessListener {
                image.downloadUrl.addOnSuccessListener {
                    downloadUrl->
                    val newitem = allitemsdata(
                         foodname=foodname,
                         foodimage=downloadUrl.toString(),
                         foodprice=foodprice,
                         fooddescription=fooddescription,
                         foodIngedrient=foodIngedrient)
                    itemkey?.let {
                        key->
                        menuref.child(key).setValue(newitem).addOnSuccessListener {
                            Toast.makeText(this,"Item Added Successfully",Toast.LENGTH_SHORT).show()
                        }
                            .addOnFailureListener {
                                Toast.makeText(this,"Item Not Added",Toast.LENGTH_SHORT).show()
                            }
                    }

                }

            }
                .addOnFailureListener {
                    Toast.makeText(this,"Image Not Uploaded",Toast.LENGTH_SHORT).show()
                }

        }
            else {
                Toast.makeText(this,"please select an image",Toast.LENGTH_SHORT).show()
            }
    }

    private val pickimage = registerForActivityResult(ActivityResultContracts.GetContent()){uri ->
        if(uri!=null){
            binding.imageView14.setImageURI(uri)
            imageuri = uri
        }
    }
}