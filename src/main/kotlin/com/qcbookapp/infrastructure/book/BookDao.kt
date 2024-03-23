package com.qcbookapp.infrastructure.book

import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.book.BookId
import com.qcbookapp.infrastructure.jooq.App
import com.qcbookapp.infrastructure.jooq.tables.records.BookRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Component

/**
 * 書籍テーブルとやりとりするクラス
 */
@Component
class BookDao(
    private val dslContext: DSLContext,
) {
    private val jAppSchema: App = App.APP
    private val jBookTable = jAppSchema.BOOK
    fun newRecord(): BookRecord = dslContext.newRecord(jBookTable)

    /**
     * 全ての書籍を取得する
     */
    fun findAll(): List<BookRecord> {
        return dslContext.selectFrom(jBookTable)
            .fetch()
            .into(jBookTable)
    }

    /**
     * 著者IDに紐づく全ての書籍を取得する
     */
    fun findByAuthorId(authorId: AuthorId): List<BookRecord> {
        return dslContext.selectFrom(jBookTable)
            .where(jBookTable.AUTHOR_ID.eq(authorId.value))
            .fetchInto(jBookTable)
    }

    fun fetchById(id: BookId): BookRecord? {
        return dslContext.selectFrom(jBookTable)
            .where(jBookTable.ID.eq(id.value))
            .fetchOne()
            ?.into(jBookTable)
    }
}
