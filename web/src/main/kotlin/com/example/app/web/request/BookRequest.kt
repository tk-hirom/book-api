package com.example.app.web.request

import com.example.app.domain.ValidISBN
import com.example.app.web.request.validator.ValidBookRequest
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

@ValidBookRequest
class BookRequest(
    @field:ValidISBN // @ValidISBNと書くとだめ
    val isbn: String,
    val title: String,
    val author: String,
    val publisher: String,
    val price: Int
) {
    companion object {
        // JSONからこのメソッドを使ってインスタンスを作成する
        @JvmStatic
        @JsonCreator
        fun create(
            @ValidISBN() @JsonProperty("isbn") isbn: String,
            @JsonProperty("title") title: String,
            @JsonProperty("author") author: String,
            @JsonProperty("publisher") publisher: String,
            @JsonProperty("price") price: Int
        ): BookRequest {
            return BookRequest(
                isbn,
                title,
                author,
                publisher,
                price
            )
        }
    }
}