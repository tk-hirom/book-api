package com.example.bookapi.web.repository

import com.example.bookapi.AppProperties
import com.example.bookapi.web.entity.Book
import org.springframework.stereotype.Repository
import java.sql.DriverManager

@Repository
class BookRepository(private val appProperties: AppProperties) {
    fun getBooks(): List<Book> {
        try {
            val connection = DriverManager.getConnection(
                "jdbc:postgresql://" + appProperties.host + ":" + appProperties.port + "/" + appProperties.database,
                appProperties.user,
                appProperties.password
            )
            connection.use { conn ->
                conn.createStatement().use { statement ->
                    statement.executeQuery("SELECT * FROM book").use { resultSet ->
                        return generateSequence {
                            if (resultSet.next()) {
                                Book.convertFrom(
                                    resultSet.getString("title"),
                                    resultSet.getString("author")
                                )
                            } else {
                                null
                            }
                        }.toList()
                    }
                }
            }
        } catch (e: Exception) {
            throw Exception("Error while getting books", e)
        }
    }
}