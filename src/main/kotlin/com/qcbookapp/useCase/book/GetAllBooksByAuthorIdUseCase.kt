package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.EntityNotFoundException
import com.qcbookapp.domain.model.author.Author
import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.book.Book
import com.qcbookapp.useCase.author.AuthorRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * 著者に紐づく全ての書籍を取得するユースケース
 */
@Component
@Transactional(readOnly = true)
class GetAllBooksByAuthorIdUseCase(
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository,
) {
    fun execute(authorId: AuthorId): AuthorBooksDto {
        val author: Author = authorRepository.fetchById(authorId) ?: throw EntityNotFoundException("著者が見つかりませんでした")
        val books: List<Book> = bookRepository.findByAuthorId(authorId)

        return AuthorBooksDto.fromDomain(
            author = author,
            books = books,
        )
    }
}
