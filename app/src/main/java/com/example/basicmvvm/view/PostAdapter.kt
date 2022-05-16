package com.example.basicmvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basicmvvm.R
import com.example.basicmvvm.model.Post
import com.google.android.material.button.MaterialButton

class PostAdapter(private val posts: List<Post>, private val onPostListener: OnPostListener) :
    RecyclerView.Adapter<PostHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): PostHolder {
        return PostHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_post, viewGroup, false),
            onPostListener
        )
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bindHero(posts[position], position)
    }
}

class PostHolder(view: View, private val onPostListener: OnPostListener) :
    RecyclerView.ViewHolder(view) {
    private val tvTitle = view.findViewById<TextView>(R.id.tv_title)
    private val tvCategory = view.findViewById<TextView>(R.id.tv_category)
    private val btDelete = view.findViewById<MaterialButton>(R.id.bt_delete)

    fun bindHero(post: Post, position: Int) {
        tvTitle.text = post.title
        tvCategory.text = post.category
        btDelete.setOnClickListener {
            onPostListener.onClick(position)
        }
    }
}

interface OnPostListener {
    fun onClick(position: Int)
}