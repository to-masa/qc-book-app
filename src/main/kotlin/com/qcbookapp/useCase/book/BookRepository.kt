package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.book.Book
import com.qcbookapp.domain.model.book.BookId

/**
 * 書籍リポジトリ
 */
interface BookRepository {
    fun findAll(): List<Book>

    fun findByAuthorId(authorId: AuthorId): List<Book>

    fun fetchById(id: BookId): Book?

    fun insert(book: Book)

    fun update(book: Book)
}
