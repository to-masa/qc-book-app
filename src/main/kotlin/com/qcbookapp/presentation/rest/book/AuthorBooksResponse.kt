package com.qcbookapp.presentation.rest.book

import com.qcbookapp.presentation.rest.author.AuthorResponse
import com.qcbookapp.useCase.book.AuthorBooksDto

/**
 * 著者に紐づく全ての書籍のレスポンス
 */
class AuthorBooksResponse(
    val author: AuthorResponse,
    val books: List<BookResponse>,
) {

    companion object {

        /**
         * DTOからレスポンスに変換する
         */
        fun fromDto(dto: AuthorBooksDto): AuthorBooksResponse {
            dto.run {
                return AuthorBooksResponse(
                    author = AuthorResponse.fromDto(author),
                    books = books.map { BookResponse.fromDto(it) },
                )
            }
        }
    }
}
