package com.example.app.domain

import jakarta.validation.ConstraintValidatorContext
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ValidISBNTest {
    private lateinit var validator: ISBNValidator
    private lateinit var context: ConstraintValidatorContext
    private lateinit var builder: ConstraintValidatorContext.ConstraintViolationBuilder
    private lateinit var nodeBuilder: ConstraintValidatorContext.ConstraintViolationBuilder.LeafNodeBuilderCustomizableContext

    @BeforeEach
    fun setup() {
        validator = ISBNValidator()
        /**
         * ISBNValidatorの isValid メソッド内でバリデーションメッセージを構築する際に
         * 実際のコンテキストオブジェクトとビルダーオブジェクトを使用する代わりに、モックを使ってこのプロセスを模倣
         */
        context = mock(ConstraintValidatorContext::class.java)
        builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder::class.java)
        nodeBuilder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.LeafNodeBuilderCustomizableContext::class.java)

        // 次にチェインされるメソッド呼び出しに対して、モックの戻り値を設定
        `when`(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder)
        `when`(builder.addConstraintViolation()).thenReturn(context)
    }

    @Test
    fun `ISBN with less than 13 digits should be invalid`() {
        val isbnWithLessThan13Digits = "978477416"
        assert(
            validator.isValid(
                value = isbnWithLessThan13Digits,
                context = context
            ).not()
        )
    }

    @Test
    fun `ISBN with more than 13 digits should be invalid`() {
        val isbnWithLessThan13Digits = "978477416111111"
        assert(
            validator.isValid(
                value = isbnWithLessThan13Digits,
                context = context
            ).not()
        )
    }

    @Test
    fun `ISBN with correct check digit should be valid`() {
        val isbnWithLessThan13Digits = "9784065223963"
        assert(
            validator.isValid(
                value = isbnWithLessThan13Digits,
                context = context
            )
        )
    }

    @Test
    fun `ISBN with incorrect check digit should be Invalid`() {
        val isbnWithLessThan13Digits = "9784065223964"
        assert(
            validator.isValid(
                value = isbnWithLessThan13Digits,
                context = context
            ).not()
        )
    }
}