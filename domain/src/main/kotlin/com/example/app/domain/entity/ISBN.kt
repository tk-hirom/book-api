package com.example.app.domain.entity

import com.example.app.domain.ValidISBN

@JvmInline
value class ISBN private constructor(
    val value: String
) {
    companion object{
        fun valueOf(@ValidISBN value: String): ISBN {
            return ISBN(value)
        }
    }
}