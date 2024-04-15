package com.example.bookapi.web.entity

import kotlinx.serialization.Serializable

@Serializable
class Book(
    val title: String,
    val author: String
) {
}
