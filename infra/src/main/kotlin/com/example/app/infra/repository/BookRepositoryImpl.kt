package com.example.app.infra.repository

import com.example.app.application.repository.BookRepository
import com.example.app.domain.entity.Book
import com.example.app.infra.mapper.BookMapper
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl (
    private val bookMapper: BookMapper
): BookRepository {
    override fun fetchBooks(): List<Book> {
        return try {
            bookMapper.selectBooks().map { it.toEntity() }
        } catch (e: Exception) {
            throw Exception("Error while getting books", e)
        }
    }
}