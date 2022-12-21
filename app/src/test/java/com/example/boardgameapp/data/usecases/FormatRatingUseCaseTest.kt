package com.example.boardgameapp.data.usecases

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Test


class FormatRatingUseCaseTest {
    @Test
    fun ratingTest() {
        val formatRatingUseCase = FormatRatingUseCase(arrayListOf(1.0, 2.0))
        assertThat(formatRatingUseCase.getRating(), `is`(equalTo(1.5F)))
    }

    @Test
    fun `can handle null test`() {
        val formatRatingUseCase = FormatRatingUseCase(null)
        assertThat(formatRatingUseCase.getRating(), `is`(equalTo(0.0F)))
    }
}