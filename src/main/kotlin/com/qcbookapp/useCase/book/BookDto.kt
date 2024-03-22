package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.book.Book
import java.time.LocalDateTime

/**
 * 書籍DTO
 */
class BookDto(
    val id: Long,
    val title: String,
    val authorId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun fromDomain(book: Book): BookDto {
            return BookDto(
                id = book.identifier.value,
                title = book.title.value,
                authorId = book.authorId.value,
                createdAt = book.createdAt.value,
                updatedAt = book.updatedAt.value,
            )
        }
    }
}
