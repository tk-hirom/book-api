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
        return buildMap {
            put("reason", "invalid parameter")

            val fieldErrors = ex.bindingResult.fieldErrors.map { "${it.field}: ${it.defaultMessage}" }
            if (fieldErrors.isNotEmpty()) {
                put("fieldErrors", fieldErrors)
            }

            val globalErrors = ex.bindingResult.globalErrors.map { "${it.objectName}: ${it.defaultMessage}" }
            if (globalErrors.isNotEmpty()) {
                put("globalErrors", globalErrors)
            }
        }
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