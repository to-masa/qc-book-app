package com.qcbookapp.infrastructure.author

import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.infrastructure.jooq.App
import com.qcbookapp.infrastructure.jooq.tables.records.AuthorRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Component

/**
 * 著者テーブルとやりとりするクラス
 */
@Component
class AuthorDao(
    private val dslContext: DSLContext,
) {
    private val jAppSchema: App = App.APP
    private val jAuthorTable = jAppSchema.AUTHOR

    /**
     * 新規レコードを作成する
     */
    fun newRecord(): AuthorRecord = dslContext.newRecord(jAuthorTable)

    /**
     * 全ての著者を取得する
     */
    fun findAll(): List<AuthorRecord> {
        return dslContext.selectFrom(jAuthorTable)
            .fetch()
            .into(jAuthorTable)
    }

    /**
     * IDで著者を取得する
     */
    fun fetchById(id: AuthorId): AuthorRecord? {
        return dslContext.selectFrom(jAuthorTable)
            .where(
                jAuthorTable.ID.eq(id.value),
            )
            .fetchOne()
            ?.into(jAuthorTable)
    }
}
