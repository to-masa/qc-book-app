package com.qcbookapp.presentation.rest.book

import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.book.BookTitle
import com.qcbookapp.useCase.book.UpdateBookParam
import org.springframework.web.bind.annotation.RequestParam

/**
 * 書籍更新リクエスト
 */
data class UpdateBookRequest(
    @RequestParam
    val title: String,
    @RequestParam
    val authorId: Long,
) {

    /**
     * パラメータに変換する
     */
    fun toParam(): UpdateBookParam {
        return UpdateBookParam(BookTitle(title), AuthorId(authorId))
    }
}
