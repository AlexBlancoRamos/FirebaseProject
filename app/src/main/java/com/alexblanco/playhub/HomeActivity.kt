package com.alexblanco.playhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

enum class ProviderType{
    BASIC
}

class HomeActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Setup
        val bundle = intent.extras; val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")
    }

    private fun setup(email: String, provider:String){
        title = "Inicio"

        val emailTextView = findViewById<TextView>(R.id.emailTextView)
        val address = findViewById<EditText>(R.id.addressTextView)
        val tel = findViewById<EditText>(R.id.phoneTextView)


        val closeSesion = findViewById<Button>(R.id.logOutButton)
        val safe = findViewById<Button>(R.id.safeButton)
        val get = findViewById<Button>(R.id.getButton)
        val delete = findViewById<Button>(R.id.deleteButton)
        val show = findViewById<Button>(R.id.showUsers)



        emailTextView.text = email

        closeSesion.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

        safe.setOnClickListener{
            db.collection("users").document(email).set(
                hashMapOf("email" to emailTextView.text.toString(),
                    "address" to address.text.toString(),
                    "telefono" to tel.text.toString())
            )
        }

        get.setOnClickListener{
            db.collection("users").document(email).get().addOnSuccessListener {
                address.setText(it.get("address") as String?)
                tel.setText(it.get("telefono") as String?)
            }
        }

        delete.setOnClickListener {
            db.collection("users").document(email).delete()
        }

        show.setOnClickListener{
            showUsers()
        }
    }

    private fun showUsers() {
        val userIntent = Intent(this, UsersActivity::class.java).apply {
        }
        startActivity(userIntent)
    }
}