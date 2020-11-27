package com.anton.dobrogorsky.usersposts.flow.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anton.dobrogorsky.usersposts.R
import com.anton.dobrogorsky.usersposts.model.User

class UserAdapter(val onItemClick: (userId: User) -> Unit) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var userList: List<User> = emptyList()

    private var dataList: List<User> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun updateData(data: List<User>) {
        userList = data
        dataList = userList
    }

    fun sortList(ascending: Boolean) {
        dataList = if (ascending) userList.sortedBy { it.username }
        else userList.sortedByDescending { it.username }
    }

    fun showAll() {
        dataList = userList
    }

    fun search(query: String) {
        dataList = userList.filter { it.username.contains(query, true) }
    }

    private fun getItem(position: Int) = dataList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

    inner class ViewHolder(
        val view: View,
        private val usernameTextView: TextView? = view.findViewById(R.id.username),
        private val emailTextView: TextView? = view.findViewById(R.id.email),
        private val addressTextView: TextView? = view.findViewById(R.id.address),
        private val userIdTextView: TextView? = view.findViewById(R.id.user_id)
    ) : RecyclerView.ViewHolder(view) {

        fun bind(user: User) {
            view.setOnClickListener {
                onItemClick.invoke(user)
            }
            usernameTextView?.text = user.username
            emailTextView?.text = user.email
            addressTextView?.text = user.address.toString()
            userIdTextView?.text = "# ${adapterPosition + 1}"
        }

    }

}