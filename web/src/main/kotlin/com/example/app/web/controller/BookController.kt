package com.example.app.web.controller

import com.example.app.application.usecase.AddBook
import com.example.app.application.usecase.FetchBook
import com.example.app.domain.exception.BookRepositoryException
import com.example.app.web.request.BookRequest
import com.example.app.web.response.BookResponse
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(
    private val fetchBook: FetchBook,
    private val addBook: AddBook
) {
    @GetMapping("/books")
    fun getBooks(): List<BookResponse> {
        return try {
            fetchBook.fetchBooks().map { BookResponse.responseOf(it) }
        } catch (bookRepositoryException: BookRepositoryException) {
            throw bookRepositoryException
        } catch (e: Exception) {
            logger.error("Error while getting books", e)
            throw e
        }
    }

    @GetMapping("/book")
    fun book(@Valid @RequestBody bookRequest: BookRequest): BookResponse? {
        return try {
            val result = fetchBook.fetchBookBy(bookRequest.isbn)
            if(result != null) {
                 BookResponse.responseOf(result)
            } else {
                null
            }
        } catch (bookRepositoryException: BookRepositoryException) {
            throw bookRepositoryException
        } catch (e: Exception) {
            logger.error("Error while getting a book", e)
            throw e
        }
    }

    @PostMapping("/add_book")
    fun addBook(@Valid @RequestBody bookRequest: BookRequest) {
        try {
            addBook.addBook(
                bookRequest.isbn,
                bookRequest.title,
                bookRequest.author,
                bookRequest.publisher,
                bookRequest.price
                )
        } catch (e: Exception) {
            logger.error("Error while adding a book", e)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while adding book")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(BookController::class.java)
    }
}