package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.author.Author
import com.qcbookapp.domain.model.book.Book
import com.qcbookapp.useCase.author.AuthorDto

/**
 * 著者に紐づく全ての書籍DTO
 */
class AuthorBooksDto(
    val author: AuthorDto,
    val books: List<BookDto>,
) {
    companion object {

        /**
         * ドメインオブジェクトからDTOへの変換
         */
        fun fromDomain(author: Author, books: List<Book>): AuthorBooksDto {
            return AuthorBooksDto(
                author = AuthorDto.fromDomain(
                    author = author,
                ),
                books = books.map { BookDto.fromDomain(it) },
            )
        }
    }
}
