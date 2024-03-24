package com.qcbookapp.presentation.rest.author

import com.qcbookapp.domain.model.author.AuthorKana
import com.qcbookapp.domain.model.author.AuthorName
import com.qcbookapp.useCase.author.UpdateAuthorParam
import org.springframework.web.bind.annotation.RequestParam

/**
 * 著者更新リクエスト
 */
data class UpdateAuthorRequest(
    @RequestParam
    val name: String,
    @RequestParam
    val kana: String,
) {

    /**
     * パラメータに変換する
     */
    fun toParam(): UpdateAuthorParam {
        return UpdateAuthorParam(AuthorName(name), AuthorKana(kana))
    }
}
