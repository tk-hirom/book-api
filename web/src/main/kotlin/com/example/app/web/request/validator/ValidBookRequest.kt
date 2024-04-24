package com.example.app.web.request.validator

import com.example.app.web.request.BookRequest
import jakarta.validation.*
import kotlin.reflect.KClass

@Constraint(validatedBy = [BookRequestValidator::class])
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
@ReportAsSingleViolation
@MustBeDocumented
annotation class ValidBookRequest(
    val message: String = "Invalid book request.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class BookRequestValidator: ConstraintValidator<ValidBookRequest, BookRequest> {
    override fun isValid(bookRequest: BookRequest, context: ConstraintValidatorContext): Boolean {
        val isInValid = bookRequest.publisher == validationTargetPublisher && bookRequest.price < validationTargetPrice
        if (isInValid) {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate("出版社が${validationTargetPublisher}で価格が${validationTargetPrice}円未満は不許可です")
                .addConstraintViolation()
            return false
        }
        return true
    }

    companion object {
        private const val validationTargetPublisher = "ほげほげ書房"
        private const val validationTargetPrice = 1000
    }
}
