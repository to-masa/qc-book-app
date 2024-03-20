package com.qcbookapp.infrastructure.author

import com.qcbookapp.domain.model.author.Author
import com.qcbookapp.domain.model.author.AuthorId
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * 著者テーブルとやりとりするクラス
 */
@Component
class AuthorDao(
    private val dslContext: DSLContext,
) {

    fun newRecord() = dslContext.newRecord(DSL.table("app.book"))

    /**
     * 全ての著者を取得する
     */
    fun findAll(): List<AuthorRow> {
        return dslContext.selectFrom("app.author")
            .fetch()
            .into(AuthorRow::class.java)
    }

    fun findById(id: AuthorId): AuthorRow? {
        return dslContext.selectFrom("app.author")
            .where(DSL.field("id").eq(id.value))
            .fetchOne()
            ?.into(AuthorRow::class.java)
    }

    fun insert(author: Author) {
        dslContext.insertInto(DSL.table("app.author"))
            .columns(DSL.field("id"), DSL.field("name"), DSL.field("kana"), DSL.field("created_at"), DSL.field("updated_at"))
            .values(author.identifier.value, author.name.value, author.kana.value, author.createdAt.value, author.updatedAt.value)
            .execute()
    }

    fun update(author: Author) {
        dslContext.update(DSL.table("app.author"))
            .set(DSL.field("name"), author.name.value)
            .set(DSL.field("kana"), author.kana.value)
            .set(DSL.field("updated_at"), author.updatedAt.value)
            .where(DSL.field("id").eq(author.identifier.value))
            .execute()
    }

    class AuthorRow(
        val id: Long,
        val name: String,
        val kana: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    )
}