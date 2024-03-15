package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.book.Book

/**
 * 書籍リポジトリ
 */
abstract class BookRepository {
    abstract fun findAll(): List<Book>
}