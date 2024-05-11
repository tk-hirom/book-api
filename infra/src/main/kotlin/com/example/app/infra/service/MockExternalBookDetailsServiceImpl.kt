package com.example.app.infra.service

import com.example.app.application.service.ExternalBookDetailsService
import com.example.app.domain.entity.BookDetails
import org.springframework.stereotype.Service

@Service
class MockExternalBookDetailsServiceImpl(): ExternalBookDetailsService {
    override fun getBookDetails(isbn: String): BookDetails? {
        return BookDetails("4.5", "Fiction", isbn)
    }
}