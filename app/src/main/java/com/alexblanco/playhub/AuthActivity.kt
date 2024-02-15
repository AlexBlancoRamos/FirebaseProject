package com.alexblanco.playhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //Setup
        setup()
    }

    private fun setup(){
        title = "Autenticación"

        val signUpButton = findViewById<Button>(R.id.signUpButton)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val contraEditText = findViewById<EditText>(R.id.emailEditText)


        signUpButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && contraEditText.text.isNotEmpty()){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(), contraEditText.text.toString()).addOnCompleteListener {

                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }

        loginButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && contraEditText.text.isNotEmpty()){

                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(), contraEditText.text.toString()).addOnCompleteListener {

                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider)
        }
        startActivity(homeIntent)
    }
}