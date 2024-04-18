package com.example.app.web.controller

import com.example.app.application.usecase.FetchBook
import com.example.app.web.response.BookResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(private val fetchBook: FetchBook) {
    @GetMapping("/books")
    fun getBooks(): List<BookResponse> {
        return fetchBook.fetchBooks().map { BookResponse.responseOf(it) }
    }
}