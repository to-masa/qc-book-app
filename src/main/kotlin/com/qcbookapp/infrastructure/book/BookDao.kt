package com.qcbookapp.infrastructure.book

import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * 書籍テーブルとやりとりするクラス
 */
@Component
class BookDao {

    /**
     * 全ての書籍を取得する
     */
    fun findAll(): List<BookRow> {
        // TODO: Jooqを使ってDBアクセスし、値を取得する
        return listOf(
            BookRow(
                id = 1,
                title = "タイトル",
                authorId = 2,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            ),
            BookRow(
                id = 2,
                title = "タイトル2",
                authorId = 2,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        )
    }
}

class BookRow(
    val id: Long,
    val title: String,
    val authorId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)