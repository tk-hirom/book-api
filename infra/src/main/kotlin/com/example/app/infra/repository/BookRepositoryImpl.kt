package com.example.app.infra.repository

import com.example.app.application.repository.BookRepository
import com.example.app.domain.entity.Book
import com.example.app.infra.entity.BookDto
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class BookRepositoryImpl (private val jdbcTemplate: JdbcTemplate): BookRepository {
    private val bookMapper: RowMapper<BookDto> =
        RowMapper{ rs: ResultSet, _:Int ->
            BookDto(
                rs.getString("title"),
                rs.getString("author")
            )
        }

    override fun fetchBooks(): List<Book> {
        return try {
            jdbcTemplate.query(
                "SELECT title, author FROM book",
                bookMapper
            ).map { it.toEntity() }
        } catch (e: Exception) {
            throw Exception("Error while getting books", e)
        }
    }
}