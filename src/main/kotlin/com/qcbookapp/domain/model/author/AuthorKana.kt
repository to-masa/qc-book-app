package com.qcbookapp.domain.model.author

import com.qcbookapp.domain.model.DomainException
import com.qcbookapp.domain.service.TextLengthValidator

/**
 * 著者名(かな)
 * - 値オブジェクト
 */
data class AuthorKana(
    val value: String,
) {
    init {
        TextLengthValidator.execute(
            value = value,
            minLength = 1,
            maxLength = 100,
            label = LABEL,
        )
        // ひらがなのみを許容する
        if (!value.matches(FORMAT_REGEX)) {
            throw DomainException("${LABEL}はひらがなで入力してください")
        }
    }

    companion object {
        private val FORMAT_REGEX: Regex = Regex("^[ぁ-んー 　]*$")
        private const val LABEL: String = "著者名(かな)"
    }
}
