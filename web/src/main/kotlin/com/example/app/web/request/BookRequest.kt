package com.example.app.web.request

import com.example.app.domain.ValidISBN
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class BookRequest private constructor(
    @ValidISBN
    val isbn: String
) {
    companion object {
        // JSONからこのメソッドを使ってインスタンスを作成する
        @JvmStatic
        @JsonCreator
        fun create(@JsonProperty("isbn") isbn: String): BookRequest {
            return BookRequest(isbn)
        }
    }
}