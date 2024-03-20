package com.qcbookapp.infrastructure.book

import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.book.Book
import com.qcbookapp.domain.model.book.BookId
import com.qcbookapp.domain.model.book.BookTitle
import com.qcbookapp.domain.model.shared.CreatedAt
import com.qcbookapp.domain.model.shared.UpdatedAt
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
     * 取得したデータをドメインオブジェクトにマッピングする
     */
    private val rootMapper: (BookDao.BookRow) -> Book = { result ->
        result.run {
            Book.reconstruct(
                id = BookId(id),
                title = BookTitle(title),
                authorId = AuthorId(authorId),
                createdAt = CreatedAt(createdAt),
                updatedAt = UpdatedAt(updatedAt),
            )
        }
    }

    /**
     * 全ての書籍を取得する
     */
    override fun findAll(): List<Book> {
        return bookDao.findAll().map(rootMapper)
    }

    /**
     * IDで書籍を取得する
     */
    override fun findById(id: BookId): Book? {
        return bookDao.findById(id)?.let(rootMapper)
    }

    /**
     * 書籍を作成する
     */
    override fun insert(book: Book) {
        bookDao.insert(
            book = book
        )
    }

    /**
     * 書籍を更新する
     */
    override fun update(book: Book) {
        bookDao.update(
            book = book
        )
    }
}