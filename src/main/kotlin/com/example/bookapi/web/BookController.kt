package com.example.bookapi.web

import com.example.bookapi.web.entity.Book
import com.example.bookapi.web.repository.BookRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController(
    val bookRepository: BookRepository
) {

    @GetMapping("/books")
    fun getBooks(): List<Book> {
        return bookRepository.getBooks()
    }
}
