package com.qcbookapp.domain.model.book

import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.shared.CreatedAt
import com.qcbookapp.domain.model.shared.MutableAggregation
import com.qcbookapp.domain.model.shared.UpdatedAt

class Book private constructor(
    override val identifier: BookId,
    val title: BookTitle,
    val authorId: AuthorId,
    override val createdAt: CreatedAt,
    override val updatedAt: UpdatedAt,
): MutableAggregation<Book> {

    companion object {

        fun create(title: BookTitle, authorId: AuthorId): Book {
            return Book(
                identifier = BookId(),
                title = title,
                authorId = authorId,
                createdAt = CreatedAt.of(),
                updatedAt = UpdatedAt.of()
            )
        }
        fun reconstruct(id: BookId, title: BookTitle, authorId: AuthorId, createdAt: CreatedAt, updatedAt: UpdatedAt): Book {
            return Book(
                identifier = id,
                title = title,
                authorId = authorId,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }

    /**
     * 書籍を更新する
     */
    fun update(title: BookTitle, authorId: AuthorId): Book {
        return privateUpdate(
            title = title,
            authorId = authorId
        )
    }

    /**
     * 書籍を更新する
     * - ファクトリーメソッド
     */
    private fun privateUpdate(title: BookTitle, authorId: AuthorId): Book {
        // 省略可能だが、更新前の状態で更新することを明示的に示すためにbeforeを使っている
        val before: Book = this
        return Book(
            identifier = before.identifier,
            title = title,
            authorId = authorId,
            createdAt = before.createdAt,
            updatedAt = UpdatedAt.of()
        )
    }
}