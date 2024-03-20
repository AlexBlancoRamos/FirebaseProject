package com.alexblanco.playhub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexblanco.playhub.R


class UserAdapter(var userList: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val email: TextView = itemView.findViewById(R.id.emailTextView)
        val address: TextView = itemView.findViewById(R.id.addressTextView)
        val telefono: TextView = itemView.findViewById(R.id.telefonoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.email.text = currentUser.email
        holder.address.text = currentUser.address
        holder.telefono.text = currentUser.telefono
    }

    override fun getItemCount() = userList.size

    interface OnItemClickListener {
        fun onItemClick(user: User)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}