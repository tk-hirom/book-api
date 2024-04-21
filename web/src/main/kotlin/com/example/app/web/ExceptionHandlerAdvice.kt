package com.example.app.web

import com.example.app.domain.exception.BookRepositoryException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class ExceptionHandlerAdvice {

    /**
     * リクエストのバリデーションで落ちた時のエラーハンドリング
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): Map<String, Any> {
        val errors = ex.bindingResult.fieldErrors.map {
            "${it.field}: ${it.defaultMessage}"
        }
        return mapOf(
            "reason" to "invalid parameter",
            "detail" to errors
        )
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNotFound(ex: NoHandlerFoundException): Map<String, String> {
        return mapOf(
            "reason" to "no handler found",
            "status" to "404"
        )
    }

    @ExceptionHandler(BookRepositoryException::class)
    fun handleBookRepositoryException(ex: BookRepositoryException): ResponseEntity<Map<String, String>> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                mapOf(
                    "status" to HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    "reason" to "BookRepository#findでエラーが発生しました"
                )
            )
    }
}