package com.example.snapchatclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    var emailEditText: EditText? = null
    var passwordEditText: EditText? = null
    var mAuth = FirebaseAuth.getInstance()

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        if (mAuth.currentUser != null) {
            logIn()
        }
    }

    fun signUpLoginClicked(view: View) {
        //check if we can log in user
        mAuth.signInWithEmailAndPassword(
            emailEditText?.text.toString(),
            passwordEditText?.text.toString()
        ).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.i("!@!Email", "signInWithEmail:success")
                logIn()
            } else {
                // If sign in fails, display a message to the user.
                mAuth.createUserWithEmailAndPassword(
                    emailEditText?.text.toString(),
                    passwordEditText?.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Add to database
                            database = FirebaseDatabase.getInstance().getReference()
                            database.push().key
                            database.child("users").child(task.result?.user?.uid.toString()).child("email")
                                .setValue(emailEditText?.text.toString())
                            logIn()
                        } else {
                            Toast.makeText(this, "Login Failed. Try Again", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }
            }
        }


        // else sign up user
    }

    private fun logIn() {
        // move to next activity
        val intent = Intent(this, SnapsActivity::class.java)
        startActivity(intent)
    }
}
