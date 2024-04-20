package com.example.app.web.controller

import com.example.app.application.usecase.FetchBook
import com.example.app.web.request.BookRequest
import com.example.app.web.response.BookResponse
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class BookController(private val fetchBook: FetchBook) {
    @GetMapping("/books")
    fun getBooks(): List<BookResponse> {
        return try {
            fetchBook.fetchBooks().map { BookResponse.responseOf(it) }
        } catch (e: Exception) {
            logger.error("Error while getting books", e)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while getting books")
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
        } catch (e: Exception) {
            logger.error("Error while getting book", e)
            throw e
        }
    }
    companion object {
        private val logger = LoggerFactory.getLogger(BookController::class.java)
    }
}