package com.example.app.infra.mapper

import com.example.app.infra.entity.BookDto
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface BookMapper {
    @Select("SELECT isbn, title, author, publisher, price FROM book")
    fun selectBooks(): List<BookDto>

    @Select("SELECT isbn, title, author, publisher, price FROM book WHERE isbn = #{isbn}")
    fun selectBook(isbn: String): BookDto?

    @Insert("""
        INSERT INTO book (isbn, title, author, publisher, price)
        VALUES (#{isbn}, #{title}, #{author}, #{publisher}, #{price})
    """
    )
    fun insertBook(
        isbn: String,
        title: String,
        author: String,
        publisher: String,
        price: Int
    )
}