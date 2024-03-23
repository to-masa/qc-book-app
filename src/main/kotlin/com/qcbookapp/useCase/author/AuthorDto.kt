package com.qcbookapp.useCase.author

import com.qcbookapp.domain.model.author.Author
import java.time.LocalDateTime

/**
 * 著者DTO
 */
class AuthorDto(
    val id: Long,
    val name: String,
    val kana: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun fromDomain(author: Author): AuthorDto {
            return AuthorDto(
                id = author.identifier.value,
                name = author.name.value,
                kana = author.kana.value,
                createdAt = author.createdAt.value,
                updatedAt = author.updatedAt.value,
            )
        }
    }
}
