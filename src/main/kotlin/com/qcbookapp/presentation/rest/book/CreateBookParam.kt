package com.qcbookapp.presentation.rest.book

import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.book.BookTitle

data class CreateBookParam(
    val title: BookTitle,
    val authorId: AuthorId,
)
