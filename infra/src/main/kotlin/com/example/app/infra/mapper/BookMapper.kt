package com.example.app.infra.mapper

import com.example.app.infra.entity.BookDto
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface BookMapper {
    @Select("SELECT isbn, title, author FROM book")
    fun selectBooks(): List<BookDto>

    @Select("SELECT isbn, title, author FROM book WHERE isbn = #{isbn}")
    fun selectBook(isbn: String): BookDto?
}