package com.qcbookapp.infrastructure.author

import com.qcbookapp.domain.model.author.Author
import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.author.AuthorKana
import com.qcbookapp.domain.model.author.AuthorName
import com.qcbookapp.domain.model.shared.CreatedAt
import com.qcbookapp.domain.model.shared.UpdatedAt
import com.qcbookapp.infrastructure.jooq.tables.records.AuthorRecord
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
     * 全ての書籍を取得する
     */
    override fun findAll(): List<Author> {
        return authorDao.findAll().map { authorRecord -> authorMapping(authorRecord) }
    }

    /**
     * IDで書籍を取得する
     */
    override fun fetchById(id: AuthorId): Author? {
        return authorDao.fetchById(id)?.let { authorRecord -> authorMapping(authorRecord) }
    }

    /**
     * 書籍を作成する
     */
    override fun insert(author: Author) {
        // 保存する
        privateSave(author)
    }

    /**
     * 書籍を更新する
     */
    override fun update(author: Author) {
        privateSave(author)
    }

    /**
     * 書籍を保存する
     *  - 作成の場合は新規レコードを作成する
     *  - 更新の場合は既存レコードを更新する
     */
    private fun privateSave(author: Author) {
        val record: AuthorRecord = authorDao.fetchById(author.identifier) ?: authorDao.newRecord()
        record.run {
            updateBy(author)
            store()
        }
    }

    /**
     * レコードを更新する
     */
    private fun AuthorRecord.updateBy(author: Author) {
        id = author.identifier.value
        name = author.name.value
        kana = author.kana.value
        createdAt = author.createdAt.value
        updatedAt = author.updatedAt.value
    }

    /**
     * 取得したレコードをドメインオブジェクトにマッピングする
     */
    private fun authorMapping(authorRecord: AuthorRecord): Author {
        return Author.reconstruct(
            id = AuthorId(authorRecord.id),
            name = AuthorName(authorRecord.name),
            kana = AuthorKana(authorRecord.kana),
            createdAt = CreatedAt(authorRecord.createdAt),
            updatedAt = UpdatedAt(authorRecord.updatedAt),
        )
    }
}
