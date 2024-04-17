package com.example.bookapi.web.repository

import com.example.bookapi.web.entity.Book
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class BookRepository(private val jdbcTemplate: JdbcTemplate) {

    private val bookMapper: RowMapper<Book> =
        RowMapper{ rs: ResultSet, _: Int ->
            Book.convertFrom(
                rs.getString("title"),
                rs.getString("author")
            )
        }
    fun getBooks(): List<Book> {
        return try {
            jdbcTemplate.query(
                "SELECT title, author FROM book",
                bookMapper
            )
        } catch (e: Exception) {
            throw Exception("Error while getting books", e)
        }
    }
}