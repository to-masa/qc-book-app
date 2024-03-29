package com.qcbookapp.presentation.rest.book

import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.book.BookTitle
import com.qcbookapp.useCase.book.CreateBookParam
import org.springframework.web.bind.annotation.RequestParam

data class CreateBookRequest(
    @RequestParam
    val title: String,
    @RequestParam
    val authorId: Long,
) {

    /**
     * パラメータに変換する
     */
    fun toParam(): CreateBookParam {
        return CreateBookParam(BookTitle(title), AuthorId(authorId))
    }
}
