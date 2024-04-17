package com.example.bookapi.web.entity

class Book private constructor(
    val title: String,
    val author: String
) {
    companion object {
        fun convertFrom(title: String, author: String): Book {
            return Book(title, author)
        }
    }
}
