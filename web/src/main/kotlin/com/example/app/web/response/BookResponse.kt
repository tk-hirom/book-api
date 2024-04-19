package com.example.app.web.response

import com.example.app.domain.entity.Book
import com.fasterxml.jackson.annotation.JsonCreator

class BookResponse private constructor(
    val isbn: String,
    val title: String,
    val author: String
) {
    companion object {
        @JvmStatic
        @JsonCreator
        fun responseOf(book: Book): BookResponse {
            return BookResponse(
                isbn = book.isbn.value,
                title = book.title,
                author = book.author
            )
        }
    }
}
