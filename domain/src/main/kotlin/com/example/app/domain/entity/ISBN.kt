package com.example.app.domain.entity

import com.example.app.domain.ValidISBN

@JvmInline
value class ISBN private constructor(
    @field:ValidISBN val value: String
) {
    companion object{
        fun valueOf(value: String): ISBN {
            return ISBN(value)
        }
    }
}