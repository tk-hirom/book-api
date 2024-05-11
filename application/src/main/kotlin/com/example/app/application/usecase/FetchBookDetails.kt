package com.example.app.application.usecase

import com.example.app.application.service.ExternalBookDetailsService
import com.example.app.domain.entity.BookDetails
import org.springframework.stereotype.Component

@Component
class FetchBookDetails(private val externalBookDetailsService: ExternalBookDetailsService) {
    fun fetchBookDetails(isbn: String): BookDetails? {
        return externalBookDetailsService.getBookDetails(isbn)
    }
}