package com.example.basicmvvm.view

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basicmvvm.R
import com.example.basicmvvm.model.Post
import com.example.basicmvvm.viewmodel.PostViewModel
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity(), OnPostListener {

    lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[PostViewModel::class.java]

        setupView()
        observeData()

    }

    private fun setupView() {
        findViewById<MaterialButton>(R.id.bt_save).setOnClickListener {
            viewModel.add(
                Post(
                    findViewById<EditText>(R.id.ed_title).text.toString(),
                    findViewById<EditText>(R.id.ed_category).text.toString(),
                )
            )
        }

    }

    private fun observeData() {
        viewModel.getPosts.observe({ lifecycle }, {
            val postsAdapter = PostAdapter(it, this)

            findViewById<RecyclerView>(R.id.rv_posts).apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = postsAdapter
            }
        })
    }

    override fun onClick(position: Int) {
        viewModel.remove(position)
    }
}