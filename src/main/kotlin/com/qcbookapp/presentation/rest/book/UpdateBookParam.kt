package com.qcbookapp.presentation.rest.book

import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.book.BookTitle

/**
 * 書籍更新パラメータ
 */
data class UpdateBookParam(
    val title: BookTitle,
    val authorId: AuthorId
)