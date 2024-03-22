package com.qcbookapp.useCase.author

import com.qcbookapp.domain.model.author.AuthorKana
import com.qcbookapp.domain.model.author.AuthorName

/**
 * 著者更新パラメータ
 */
data class UpdateAuthorParam(
    val name: AuthorName,
    val kana: AuthorKana,
)
