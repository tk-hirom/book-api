package com.example.bookapi.web

import com.example.bookapi.web.entity.Book
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController {

    @GetMapping("/books")
    fun getBooks(): List<Book> {
        return listOf(
            Book("Kotlin入門", "コトリン太郎"),
            Book("Java入門", "ジャバ郎")
        )
    }
}
