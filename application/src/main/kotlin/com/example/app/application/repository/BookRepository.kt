package com.example.app.application.repository

import com.example.app.domain.entity.Book

interface BookRepository {
    fun fetchBooks(): List<Book>

    fun fetchBook(isbn: String): Book?

    fun addBook(
        isbn: String,
        title: String,
        author: String,
        publisher: String,
        price: Int
    )

    fun updateBook(
        isbn: String,
        title: String,
        author: String,
        publisher: String,
        price: Int,
    )
}