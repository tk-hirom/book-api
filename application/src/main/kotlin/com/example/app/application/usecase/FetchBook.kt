package com.example.app.application.usecase

import com.example.app.application.repository.BookRepository
import com.example.app.domain.entity.Book
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class FetchBook(
    private val bookRepository: BookRepository
) {
    fun fetchBooks(): List<Book> {
        return bookRepository.fetchBooks()
    }

    fun fetchBookBy(isbn: String): Book? {
        logger.info("Fetching book by isbn: $isbn")
        return bookRepository.fetchBook(isbn)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FetchBook::class.java)
    }
}