package com.example.bookapi.web.repository

import com.example.bookapi.web.entity.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.sql.DataSource

@Repository
class BookRepository(@Autowired private val dataSource: DataSource) {
    fun getBooks(): List<Book> {
        try {
            dataSource.connection.use { connection ->
                connection.createStatement().use { statement ->  
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