package com.anton.dobrogorsky.usersposts.flow.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anton.dobrogorsky.usersposts.R
import com.anton.dobrogorsky.usersposts.model.Post


class UserPostsAdapter() : RecyclerView.Adapter<UserPostsAdapter.ViewHolder>() {

    var userPostList: List<Post> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private fun getItem(position: Int) = userPostList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_post_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return userPostList.count()
    }

    inner class ViewHolder(
        val view: View,
        private val postTitleTextView: TextView? = view.findViewById(R.id.title),
        private val postBodyTextView: TextView? = view.findViewById(R.id.body),
        private val postIdTextView: TextView? = view.findViewById(R.id.post_id)
    ) : RecyclerView.ViewHolder(view) {

        fun bind(post: Post) {

            postTitleTextView?.text = post.title
            postBodyTextView?.text = post.body
            postIdTextView?.text = "# ${adapterPosition + 1}"
        }

    }

}