package com.example.app.application.usecase

import com.example.app.application.repository.BookRepository
import org.springframework.stereotype.Component

@Component
class UpdateBook(
    private val bookRepository: BookRepository
) {

        fun updateBook(
            isbn: String,
            title: String,
            author: String,
            publisher: String,
            price: Int
        ) {
            bookRepository.updateBook(isbn, title, author, publisher, price)
        }
}