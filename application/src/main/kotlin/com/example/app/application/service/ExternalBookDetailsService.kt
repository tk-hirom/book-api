package com.example.app.application.service

import com.example.app.domain.entity.BookDetails

interface ExternalBookDetailsService {
    fun getBookDetails(isbn: String): BookDetails?
}