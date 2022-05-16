package com.example.basicmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basicmvvm.model.Post

class PostViewModel : ViewModel() {

    var listPost = arrayListOf<Post>()
    private val _posts = MutableLiveData<ArrayList<Post>>()
    val getPosts: LiveData<ArrayList<Post>>
        get() = _posts

    fun add(post: Post) {
        listPost.add(post)
        _posts.value = listPost
    }

    fun remove(position: Int) {
        listPost.removeAt(position)
        _posts.value = listPost
    }

}