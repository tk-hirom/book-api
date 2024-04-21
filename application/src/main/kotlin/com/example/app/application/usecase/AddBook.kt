package com.example.app.application.usecase

import com.example.app.application.repository.BookRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class AddBook(
    private val bookRepository: BookRepository
) {

    fun addBook(
        isbn: String,
        title: String,
        author: String,
        publisher: String,
        price: Int
    ) {
        logger.info("Adding book with isbn: $isbn")
        bookRepository.addBook(isbn, title, author, publisher, price)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(AddBook::class.java)
    }
}