package com.qcbookapp.presentation.rest.author

import com.qcbookapp.useCase.author.AuthorDto

/**
 * 著者レスポンス
 */
class AuthorResponse(
    val id: Long,
    val name: String,
    val kana: String,
    val createdAt: String,
    val updatedAt: String,

) {

    companion object {

        /**
         * DTOからレスポンスに変換する
         */
        fun fromDto(dto: AuthorDto): AuthorResponse {
            dto.run {
                return AuthorResponse(
                    id = id,
                    name = name,
                    kana = kana,
                    createdAt = createdAt.toString(),
                    updatedAt = updatedAt.toString(),
                )
            }
        }
    }
}
