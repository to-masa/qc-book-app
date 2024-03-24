package com.qcbookapp.presentation.rest.author

import com.qcbookapp.domain.model.author.AuthorKana
import com.qcbookapp.domain.model.author.AuthorName
import com.qcbookapp.useCase.author.CreateAuthorParam
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

    /**
     * パラメータに変換する
     */
    fun toParam(): CreateAuthorParam {
        return CreateAuthorParam(AuthorName(name), AuthorKana(kana))
    }
}
