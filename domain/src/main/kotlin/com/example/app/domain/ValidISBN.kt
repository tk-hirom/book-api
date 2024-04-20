package com.example.app.domain

import jakarta.validation.*
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass


// ValidISBNアノテーションがISBNValidatorでデータを検証することを示す
@Constraint(validatedBy = [ISBNValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@ReportAsSingleViolation
annotation class ValidISBN(
    val message: String = "Invalid ISBN format: ISBN is incorrect.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class ISBNValidator : ConstraintValidator<ValidISBN, String> {
    override fun isValid(value: String, context: ConstraintValidatorContext): Boolean {
        val is13DigitISBN = value.matches("[0-9]{13}".toRegex())
        if (is13DigitISBN.not()) {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate("ISBN must be exactly 13 digits.")
                .addConstraintViolation()
            return false
        }

        val isAccurateCheckDigit = isAccurateCheckDigit(value)
        if (isAccurateCheckDigit.not()) {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate(
                "Invalid ISBN format: ISBN check digit is incorrect."
            ).addConstraintViolation()
            return false
        }
        return is13DigitISBN && isAccurateCheckDigit
    }

    /**
     * ISBNのチェックディジットが正しいかどうかを検証する
     */
    private fun isAccurateCheckDigit(isbn: String): Boolean {
        logger.info("Checking ISBN: $isbn")
        val isbn12 = isbn.substring(0, 12)
        val actualCheckDigit = isbn.substring(12).toInt()
        val sum = isbn12.mapIndexed { index, c ->
            val digit = c.toString().toInt()
            if (index % 2 == 0) digit else digit * 3
        }.sum()
        val modulo10 = sum % 10

        val expectedCheckDigit = if (modulo10 == 0) 0 else 10 - modulo10
        return actualCheckDigit == expectedCheckDigit
    }
    companion object {
        private val logger =  LoggerFactory.getLogger(ISBNValidator::class.java)
    }
}