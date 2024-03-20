package com.alexblanco.playhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.toObject

class UsersActivity : AppCompatActivity() {
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        userRecyclerView = findViewById(R.id.userRecyclerView)
        userAdapter = UserAdapter(emptyList())
        userRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@UsersActivity)
            adapter = userAdapter
        }

        loadUsers()
    }

    private fun loadUsers() {
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                val users = mutableListOf<com.alexblanco.playhub.User>()

                for (document in result) {
                    val userData = document.toObject<com.alexblanco.playhub.User>()
                    users.add(userData)
                }

                userAdapter.userList = users
                userAdapter.notifyDataSetChanged()

            }
    }
}