package com.qcbookapp.domain.model.book

/**
 * 書籍タイトル
 * - 値オブジェクト
 */
data class BookTitle(
    val value: String
) {
    init {
        if(value.length > MAX_LENGTH) {
            throw IllegalArgumentException("書籍タイトルは${MAX_LENGTH}文字以下でなければなりません")
        }
    }
    
    companion object {
        private const val MAX_LENGTH: Int = 100
    }
}