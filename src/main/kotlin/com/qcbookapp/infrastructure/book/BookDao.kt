package com.qcbookapp.infrastructure.book

import com.qcbookapp.domain.model.book.Book
import com.qcbookapp.domain.model.book.BookId
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * 書籍テーブルとやりとりするクラス
 */
@Component
class BookDao(
    private val dslContext: DSLContext,
) {

    fun newRecord() = dslContext.newRecord(DSL.table("app.book"))

    /**
     * 全ての書籍を取得する
     */
    fun findAll(): List<BookRow> {
        return dslContext.selectFrom("app.book")
            .fetch()
            .into(BookRow::class.java)
    }

    fun findById(id: BookId): BookRow? {
        return dslContext.selectFrom("app.book")
            .where(DSL.field("id").eq(id.value))
            .fetchOne()
            ?.into(BookRow::class.java)
    }

    fun insert(book: Book) {
        dslContext.insertInto(DSL.table("app.book"))
            .columns(DSL.field("id"), DSL.field("title"), DSL.field("author_id"), DSL.field("created_at"), DSL.field("updated_at"))
            .values(book.identifier.value, book.title.value, book.authorId.value, book.createdAt.value, book.updatedAt.value)
            .execute()
    }

    fun update(book: Book) {
        dslContext.update(DSL.table("app.book"))
            .set(DSL.field("title"), book.title.value)
            .set(DSL.field("author_id"), book.authorId)
            .set(DSL.field("updated_at"), book.updatedAt.value)
            .where(DSL.field("id").eq(book.identifier.value))
            .execute()
    }

    class BookRow(
        val id: Long,
        val title: String,
        val authorId: Long,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    )
}