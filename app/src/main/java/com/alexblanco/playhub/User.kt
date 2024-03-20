package com.alexblanco.playhub

import com.google.firebase.firestore.PropertyName

data class User(
    @get:PropertyName("email") val email: String = "",
    @get:PropertyName("address") val address: String = "",
    @get:PropertyName("telefono") val telefono: String = "",
)