package com.example.app.application.repository

import com.example.app.domain.entity.Book

interface BookRepository {
    fun fetchBooks(): List<Book>

    fun fetchBook(isbn: String): Book?
}