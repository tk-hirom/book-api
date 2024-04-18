package com.example.app.application.usecase

import com.example.app.application.repository.BookRepository
import com.example.app.domain.entity.Book
import org.springframework.stereotype.Component

@Component
class FetchBook(
    private val bookRepository: BookRepository
) {
    fun fetchBooks(): List<Book> {
        return bookRepository.fetchBooks()
    }
}