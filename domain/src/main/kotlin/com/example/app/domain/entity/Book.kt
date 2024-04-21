package com.example.app.domain.entity

class Book(
    val isbn: ISBN,
    val title: String,
    val author: String,
    val publisher: String,
    val price: Int,
)