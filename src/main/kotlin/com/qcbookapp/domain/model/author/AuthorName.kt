package com.qcbookapp.domain.model.author

import com.qcbookapp.domain.service.TextLengthValidator

/**
 * 著者名
 * - 値オブジェクト
 */
data class AuthorName(
    val value: String
) {
    init {
        TextLengthValidator.execute(
            value = value,
            minLength = 1,
            maxLength = 100,
            label = LABEL
        )
    }
    
    companion object {
        private const val LABEL: String = "著者名"
    }
}