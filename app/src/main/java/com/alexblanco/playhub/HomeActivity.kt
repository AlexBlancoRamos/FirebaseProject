package com.alexblanco.playhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")
    }

    private fun setup(email: String, provider:String){
        title = "Inicio"

        val emailTextView = findViewById<TextView>(R.id.emailTextView)
        val providerTextView = findViewById<TextView>(R.id.providerTextView)
        val closeSesion = findViewById<Button>(R.id.closeSessionButton)

        emailTextView.text = email
        providerTextView.text = provider

        closeSesion.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}