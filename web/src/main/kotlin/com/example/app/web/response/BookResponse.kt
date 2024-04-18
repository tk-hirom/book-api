package com.example.app.web.response

import com.example.app.domain.entity.Book

class BookResponse private constructor(
    val title: String,
    val author: String
) {
    companion object {
        fun responseOf(book: Book): BookResponse {
            return BookResponse(
                title = book.title,
                author = book.author
            )
        }
    }
}
