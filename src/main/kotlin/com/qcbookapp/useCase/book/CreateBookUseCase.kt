package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.EntityNotFoundException
import com.qcbookapp.domain.model.book.Book
import com.qcbookapp.presentation.rest.book.CreateBookParam
import com.qcbookapp.useCase.author.AuthorRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * 書籍を作成するユースケース
 */
@Component
@Transactional
class CreateBookUseCase(
    private val bookRepository: BookRepository,
    private val authorRepository: AuthorRepository,
) {
    fun execute(param: CreateBookParam): BookDto {
        authorRepository.fetchById(param.authorId) ?: throw EntityNotFoundException("著者が見つかりませんでした")

        // 書籍を作成する
        val created: Book = Book.create(
            title = param.title,
            authorId = param.authorId,
        )
        bookRepository.insert(created)

        return BookDto.fromDomain(created)
    }
}
