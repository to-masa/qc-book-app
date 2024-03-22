package com.qcbookapp.domain.model.book

import com.qcbookapp.domain.service.TextLengthValidator

/**
 * 書籍タイトル
 * - 値オブジェクト
 */
data class BookTitle(
    val value: String,
) {
    init {
        TextLengthValidator.execute(
            value = value,
            minLength = 1,
            maxLength = 100,
            label = LABEL,
        )
    }

    companion object {
        private const val LABEL: String = "書籍タイトル"
    }
}
