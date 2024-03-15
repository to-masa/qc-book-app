package com.qcbookapp.domain.model.shared

import java.time.LocalDateTime

data class UpdatedAt(
    val value: LocalDateTime
) {
    companion object {
        fun of(): UpdatedAt {
            return UpdatedAt(
                value = LocalDateTime.now()
            )
        }
    }
}