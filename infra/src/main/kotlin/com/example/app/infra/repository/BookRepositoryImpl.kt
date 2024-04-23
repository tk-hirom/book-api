package com.example.app.infra.repository

import com.example.app.application.repository.BookRepository
import com.example.app.domain.entity.Book
import com.example.app.infra.mapper.BookMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl (
    private val bookMapper: BookMapper
): BookRepository {
    override fun fetchBooks(): List<Book> {
        return try {
            bookMapper.selectBooks().map { it.toEntity() }
        } catch (e: Exception) {
            throw Exception("Error while getting books", e)
        }
    }

    override fun fetchBook(isbn: String): Book? {
        return try {
            bookMapper.selectBook(isbn)?.toEntity()
        } catch (e: Exception) {
            throw Exception("Error while getting a book", e)
        }
    }

    override fun addBook(isbn: String, title: String, author: String, publisher: String, price: Int) {
        try {
            logger.info("Adding book with isbn: $isbn")
            bookMapper.insertBook(
                isbn = isbn,
                title = title,
                author = author,
                publisher = publisher,
                price = price
            )
        } catch (e: Exception) {
            throw Exception("Error while adding a book", e)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(BookRepositoryImpl::class.java)
    }
}