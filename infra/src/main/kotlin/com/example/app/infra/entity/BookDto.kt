package com.example.app.infra.entity

import com.example.app.domain.entity.Book
import com.example.app.domain.entity.ISBN

class BookDto(
    private val isbn: String,
    private val title: String,
    private val author: String,
    private val publisher: String,
    private val price: Int
) {
    fun toEntity(): Book {
        return Book(
            ISBN.valueOf(isbn),
            title,
            author,
            publisher,
            price
        )
    }
}