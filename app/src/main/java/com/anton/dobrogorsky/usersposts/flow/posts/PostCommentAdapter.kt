package com.anton.dobrogorsky.usersposts.flow.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anton.dobrogorsky.usersposts.R
import com.anton.dobrogorsky.usersposts.model.Comment

class PostCommentAdapter : RecyclerView.Adapter<PostCommentAdapter.ViewHolder>() {

    var commentList: List<Comment> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private fun getItem(position: Int) = commentList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post_comment_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return commentList.count()
    }

    inner class ViewHolder(
        val view: View,
        private val commentNameTextView: TextView? = view.findViewById(R.id.comment_name),
        private val commentBodyTextView: TextView? = view.findViewById(R.id.comment_body),
        private val commentEmailTextView: TextView? = view.findViewById(R.id.comment_email),
    ) : RecyclerView.ViewHolder(view) {

        fun bind(comment: Comment) {
            commentNameTextView?.text = comment.name
            commentBodyTextView?.text = comment.body
            commentEmailTextView?.text = comment.email
        }

    }
}