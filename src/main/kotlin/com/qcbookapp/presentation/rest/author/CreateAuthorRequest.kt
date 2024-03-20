package com.qcbookapp.presentation.rest.author

import com.qcbookapp.domain.model.author.AuthorKana
import com.qcbookapp.domain.model.author.AuthorName
import org.springframework.web.bind.annotation.RequestParam

/**
 * 著者作成リクエスト
 */
data class CreateAuthorRequest(
    @RequestParam
    val name: String,
    @RequestParam
    val kana: String,
) {
    fun toParam(): CreateAuthorParam {
        return CreateAuthorParam(AuthorName(name), AuthorKana(kana))
    }
}