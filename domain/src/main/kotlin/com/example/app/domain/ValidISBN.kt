package com.example.app.domain

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

// ValidISBNアノテーションがISBNValidatorでデータを検証することを示す
@Constraint(validatedBy = [ISBNValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidISBN(
    val message: String = "Invalid ISBN format: ISBN must be 13 digits.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class ISBNValidator : ConstraintValidator<ValidISBN, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext): Boolean {
        return value.matches("[0-9]{13}".toRegex())
    }
}