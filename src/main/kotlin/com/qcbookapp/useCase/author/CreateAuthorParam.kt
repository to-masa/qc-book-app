package com.qcbookapp.useCase.author

import com.qcbookapp.domain.model.author.AuthorKana
import com.qcbookapp.domain.model.author.AuthorName

/**
 * 著者作成パラメータ
 */
data class CreateAuthorParam(
    val name: AuthorName,
    val kana: AuthorKana,
)
