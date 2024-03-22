package com.qcbookapp.presentation.rest.book

import com.qcbookapp.useCase.book.BookDto

class BookResponse(
    val id: Long,
    val title: String,
    val authorId: Long,
    val createdAt: String,
    val updatedAt: String,

) {

    companion object {
        fun fromDto(dto: BookDto): BookResponse {
            dto.run {
                return BookResponse(
                    id = id,
                    title = title,
                    authorId = authorId,
                    createdAt = createdAt.toString(),
                    updatedAt = updatedAt.toString(),
                )
            }
        }
    }
}
