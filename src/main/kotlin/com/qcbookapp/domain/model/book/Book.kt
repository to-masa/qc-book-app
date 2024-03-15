package com.qcbookapp.domain.model.book

import com.qcbookapp.domain.model.shared.CreatedAt
import com.qcbookapp.domain.model.shared.MutableAggregation
import com.qcbookapp.domain.model.shared.UpdatedAt

class Book private constructor(
    override val identifier: BookId,
    val title: BookTitle,
    val authorId: Long,
    override val createdAt: CreatedAt,
    override val updatedAt: UpdatedAt,
): MutableAggregation<Book> {

    companion object {

        fun create(id: BookId, title: BookTitle, authorId: Long): Book {
            return Book(
                identifier = id,
                title = title,
                authorId = authorId,
                createdAt = CreatedAt.of(),
                updatedAt = UpdatedAt.of()
            )
        }
        fun reconstruct(id: BookId, title: BookTitle, authorId: Long, createdAt: CreatedAt, updatedAt: UpdatedAt): Book {
            return Book(
                identifier = id,
                title = title,
                authorId = authorId,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }
}