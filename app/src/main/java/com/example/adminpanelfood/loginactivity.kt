package com.example.adminpanelfood

import android.content.Context
import android.content.Intent
import android.credentials.CredentialManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.lifecycleScope
import com.example.adminpanelfood.databinding.ActivityLoginactivityBinding
import com.example.adminpanelfood.model.usermodel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import kotlinx.coroutines.launch
//import androidx.credentials.googleid.GetGoogleIdOption
//import androidx.credentials.googleid.GoogleIdTokenCredential
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.GoogleAuthProvider

class loginactivity : AppCompatActivity() {

    private var name : String?= null
    private var restname : String?= null
    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var database: DatabaseReference
    //private lateinit var credentialManager: CredentialManager
    private lateinit var credentials: androidx.credentials.CredentialManager

    private val binding: ActivityLoginactivityBinding by lazy {
        ActivityLoginactivityBinding.inflate(layoutInflater)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        credentials = androidx.credentials.CredentialManager.create(this)
       // credentialManager = CredentialManager.c
        auth = Firebase.auth
        database = Firebase.database.reference

        binding.loginbtn.setOnClickListener {

            email = binding.editTextTextEmailAddress.text.toString().trim()
            password = binding.editTextTextPassword.text.toString().trim()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else {
                login(email, password)
            }

        }
        binding.noaccbtn.setOnClickListener {
            val intent = Intent(this, signupactivity::class.java)
            startActivity(intent)
        }

        binding.button4.setOnClickListener {
                startGoogleSignIn()
            }
    }
    private fun startGoogleSignIn() {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(getString(R.string.clientid)) // Web client ID
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(true) // Optional for smoother UX
            .build()

        val request : GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch {
            try {
                val result = credentials.getCredential(
                    request = request,
                    context = this@loginactivity
                )
                handleSignIn(result.credential)
            } catch (e: GetCredentialException) {
                // Handle error (e.getType() gives reason)
            }
        }
    }
    private fun handleSignIn(credential: Credential) {
        if (
            credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            val idToken: String = googleIdTokenCredential.idToken
            val email: String = googleIdTokenCredential.id
            val displayName: String? = googleIdTokenCredential.displayName
            val profilePicUrl: String? = googleIdTokenCredential.profilePictureUri?.toString()

            // 1. Build a user object
            val user = usermodel(
                email = email
            )

            // 2. Sign in to Firebase with Google ID token
            val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
            FirebaseAuth.getInstance().signInWithCredential(firebaseCredential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // 3. Save user details to the database
                        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@addOnCompleteListener
                        val database = Firebase.database.reference
                        database.child("users").child(userId).setValue(user)

                        // 4. Update the UI or move to your next activity
                        runOnUiThread {
                            Toast.makeText(
                                this,
                                "Signed in as: ${displayName ?: email}\nEmail: $email",
                                Toast.LENGTH_LONG
                            ).show()
                            // Navigate to next screen (example: MainActivity)
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this, "Firebase sign-in failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        } else {
            // Handle cancelled or failed sign-in
            runOnUiThread {
                Toast.makeText(this, "Sign-in failed or cancelled.", Toast.LENGTH_SHORT).show()
            }
        }
    }
            private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateui(user)
                }else{
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            saveuserdata()
                            updateui(user)
                        }else{
                            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                            Log.d("Login", "login failed", task.exception)
                        }
                    }
                }
        }
    }

    private fun saveuserdata() {
        email = binding.editTextTextEmailAddress.text.toString().trim()
        password = binding.editTextTextPassword.text.toString().trim()

        val user = usermodel(name,restname, email, password)
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let {
            database.child("users").child(it).setValue(user)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentuser : FirebaseUser? = auth.currentUser
        if (currentuser!=null){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
        }
    }

    private fun updateui(user: FirebaseUser?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
