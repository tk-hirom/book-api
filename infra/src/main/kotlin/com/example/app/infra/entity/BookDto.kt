package com.example.app.infra.entity

import com.example.app.domain.entity.Book

class BookDto(
    val title: String,
    val author: String
) {
    fun toEntity(): Book {
        return Book(title, author)
    }
}