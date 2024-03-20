package com.qcbookapp.infrastructure.author

import com.qcbookapp.domain.model.author.Author
import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.author.AuthorKana
import com.qcbookapp.domain.model.author.AuthorName
import com.qcbookapp.domain.model.shared.CreatedAt
import com.qcbookapp.domain.model.shared.UpdatedAt
import com.qcbookapp.useCase.author.AuthorRepository
import org.springframework.stereotype.Repository

/**
 * 著者リポジトリ
 * 実装クラス
 */
@Repository
class AuthorRepositoryImpl(
    private val authorDao: AuthorDao,
) : AuthorRepository {

    /**
     * 取得したデータをドメインオブジェクトにマッピングする
     */
    private val rootMapper: (AuthorDao.AuthorRow) -> Author = { result ->
        result.run {
            Author.reconstruct(
                id = AuthorId(id),
                name = AuthorName(name),
                kana = AuthorKana(kana),
                createdAt = CreatedAt(createdAt),
                updatedAt = UpdatedAt(updatedAt),
            )
        }
    }

    /**
     * 全ての書籍を取得する
     */
    override fun findAll(): List<Author> {
        return authorDao.findAll().map(rootMapper)
    }

    /**
     * IDで書籍を取得する
     */
    override fun findById(id: AuthorId): Author? {
        return authorDao.findById(id)?.let(rootMapper)
    }

    /**
     * 書籍を作成する
     */
    override fun insert(author: Author) {
        authorDao.insert(
            author = author
        )
    }

    /**
     * 書籍を更新する
     */
    override fun update(author: Author) {
        authorDao.update(
            author = author
        )
    }
}