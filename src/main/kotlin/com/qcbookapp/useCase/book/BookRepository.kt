package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.book.Book
import com.qcbookapp.domain.model.book.BookId

/**
 * 書籍リポジトリ
 */
abstract class BookRepository {
    abstract fun findAll(): List<Book>

    abstract fun fetchById(id: BookId): Book?

    abstract fun insert(book: Book)

    abstract fun update(book: Book)
}
