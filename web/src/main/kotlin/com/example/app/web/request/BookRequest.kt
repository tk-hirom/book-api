package com.example.app.web.request

import com.example.app.domain.ValidISBN
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.slf4j.LoggerFactory

class BookRequest(
    @field:ValidISBN // @ValidISBNと書くとだめ
    val isbn: String
) {
    companion object {
        // JSONからこのメソッドを使ってインスタンスを作成する
        @JvmStatic
        @JsonCreator
        fun create(
            @ValidISBN()
            @JsonProperty("isbn")
            isbn: String
        ): BookRequest {
            logger.info("Creating BookRequest with isbn: $isbn")
            return BookRequest(isbn)
        }

        private val logger = LoggerFactory.getLogger(BookRequest::class.java)
    }
}