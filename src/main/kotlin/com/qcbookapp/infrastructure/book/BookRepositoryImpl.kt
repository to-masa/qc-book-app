package com.qcbookapp.infrastructure.book

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
    private val rootMapper: (BookRow) -> Book = { result ->
        result.run {
            Book.reconstruct(
                id = BookId(id),
                title = BookTitle(title),
                authorId = authorId,
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
}