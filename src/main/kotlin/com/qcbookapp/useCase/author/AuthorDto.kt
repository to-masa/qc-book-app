package com.qcbookapp.useCase.author

import com.qcbookapp.domain.model.author.Author
import java.time.LocalDateTime

/**
 * 書籍DTO
 */
class AuthorDto(
    val id: Long,
    val name: String,
    val kana: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun fromDomain(book: Author): AuthorDto {
            return AuthorDto(
                id = book.identifier.value,
                name = book.name.value,
                kana = book.kana.value,
                createdAt = book.createdAt.value,
                updatedAt = book.updatedAt.value,
            )
        }
    }
}
