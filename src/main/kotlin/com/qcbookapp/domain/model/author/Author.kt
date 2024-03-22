package com.qcbookapp.domain.model.author

import com.qcbookapp.domain.model.shared.CreatedAt
import com.qcbookapp.domain.model.shared.MutableAggregation
import com.qcbookapp.domain.model.shared.UpdatedAt

/**
 * 著者
 * - 集約ルート
 */
class Author private constructor(
    override val identifier: AuthorId,
    val name: AuthorName,
    val kana: AuthorKana,
    override val createdAt: CreatedAt,
    override val updatedAt: UpdatedAt,
) : MutableAggregation<Author> {

    companion object {

        /**
         * 著者を作成する
         */
        fun create(name: AuthorName, kana: AuthorKana): Author {
            return Author(
                identifier = AuthorId(),
                name = name,
                kana = kana,
                createdAt = CreatedAt.of(),
                updatedAt = UpdatedAt.of(),
            )
        }

        /**
         * 著者を再構築する
         */
        fun reconstruct(id: AuthorId, name: AuthorName, kana: AuthorKana, createdAt: CreatedAt, updatedAt: UpdatedAt): Author {
            return Author(
                identifier = id,
                name = name,
                kana = kana,
                createdAt = createdAt,
                updatedAt = updatedAt,
            )
        }
    }

    /**
     * 著者を更新する
     */
    fun update(name: AuthorName, kana: AuthorKana): Author {
        return privateUpdate(
            name = name,
            kana = kana,
        )
    }

    /**
     * 著者を更新する
     * - ファクトリーメソッド
     */
    private fun privateUpdate(name: AuthorName, kana: AuthorKana): Author {
        // thisは省略可能だが、更新前の状態で更新することを明示的に示すためにbeforeを使っている
        val before: Author = this
        return Author(
            identifier = before.identifier,
            name = name,
            kana = kana,
            createdAt = before.createdAt,
            updatedAt = UpdatedAt.of(),
        )
    }
}
