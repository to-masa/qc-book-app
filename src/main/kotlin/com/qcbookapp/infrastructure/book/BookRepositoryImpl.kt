package com.qcbookapp.infrastructure.book

import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.book.Book
import com.qcbookapp.domain.model.book.BookId
import com.qcbookapp.domain.model.book.BookTitle
import com.qcbookapp.domain.model.shared.CreatedAt
import com.qcbookapp.domain.model.shared.UpdatedAt
import com.qcbookapp.infrastructure.jooq.tables.records.BookRecord
import com.qcbookapp.useCase.book.BookRepository
import org.springframework.stereotype.Repository

/**
 * 書籍リポジトリ
 * 実装クラス
 */
@Repository
class BookRepositoryImpl(
    private val bookDao: BookDao,
) : BookRepository() {

    /**
     * 全ての書籍を取得する
     */
    override fun findAll(): List<Book> {
        return bookDao.findAll().map { bookRecord -> bookMapper(bookRecord) }
    }

    /**
     * IDで書籍を取得する
     */
    override fun fetchById(id: BookId): Book? {
        return bookDao.fetchById(id)?.let { bookRecord -> bookMapper(bookRecord) }
    }

    /**
     * 書籍を作成する
     */
    override fun insert(book: Book) {
        privateSave(book)
    }

    /**
     * 書籍を更新する
     */
    override fun update(book: Book) {
        privateSave(book)
    }

    /**
     * 書籍を保存する
     *  - 作成の場合は新規レコードを作成する
     *  - 更新の場合は既存レコードを更新する
     */
    private fun privateSave(book: Book) {
        val record: BookRecord = bookDao.fetchById(book.identifier) ?: bookDao.newRecord()
        record.run {
            updateBy(book)
            store()
        }
    }

    /**
     * レコードを更新する
     */
    private fun BookRecord.updateBy(book: Book) {
        id = book.identifier.value
        title = book.title.value
        authorId = book.authorId.value
        createdAt = book.createdAt.value
        updatedAt = book.updatedAt.value
    }

    /**
     * 取得したデータをドメインオブジェクトにマッピングする
     */
    private fun bookMapper(bookRecord: BookRecord): Book {
        return Book.reconstruct(
            id = BookId(bookRecord.id),
            title = BookTitle(bookRecord.title),
            authorId = AuthorId(bookRecord.authorId),
            createdAt = CreatedAt(bookRecord.createdAt),
            updatedAt = UpdatedAt(bookRecord.updatedAt),
        )
    }
}
