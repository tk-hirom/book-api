package com.example.app.application.usecase

import com.example.app.application.repository.BookRepository
import com.example.app.domain.entity.Book
import com.example.app.domain.exception.BookRepositoryException
import org.slf4j.LoggerFactory
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Component

@Component
class FetchBook(
    private val bookRepository: BookRepository
) {
    fun fetchBooks(): List<Book> {
        try {
            return bookRepository.fetchBooks()
        } catch (dataAccessException: DataAccessException) {
            throw BookRepositoryException("Error while getting books")
        } catch (e: Exception) {
            logger.error("Error while getting books", e)
            throw e
        }
    }

    fun fetchBookBy(isbn: String): Book? {
        try {
            return bookRepository.fetchBook(isbn)
        } catch (dataAccessException: DataAccessException) {
            throw BookRepositoryException("Error while getting a book")
        } catch (e: Exception) {
            logger.error("Error while getting a book", e)
            throw e

        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FetchBook::class.java)
    }
}