package com.qcbookapp.domain.model.author

/**
 * 著者名(かな)
 * - 値オブジェクト
 */
data class AuthorKana(
    val value: String
) {
    init {
        if(value.length > MAX_LENGTH) {
            throw IllegalArgumentException("${LABEL}は${MAX_LENGTH}文字以下でなければなりません")
        }
    }
    
    companion object {
        private const val MAX_LENGTH: Int = 100
        private const val LABEL: String = "著者名(かな)"
    }
}