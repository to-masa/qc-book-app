package com.qcbookapp.domain.model.shared

import java.time.LocalDateTime

/**
 * 作成日時
 * - 値オブジェクト
 */
data class CreatedAt(
    val value: LocalDateTime
) {
    companion object {
        fun of(): CreatedAt {
            return CreatedAt(
                value = LocalDateTime.now()
            )
        }
    }
}