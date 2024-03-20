package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.EntityNotFoundException
import com.qcbookapp.domain.model.book.Book
import com.qcbookapp.domain.model.book.BookId
import com.qcbookapp.presentation.rest.book.UpdateBookParam
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * 書籍を更新するユースケース
 */
@Component
@Transactional
class UpdateBookUseCase(
    private val bookRepository: BookRepository
) {
    fun execute(id: BookId, param: UpdateBookParam): BookDto {
        // TODO: 著者の検索を入れる

        // 書籍を更新する
        val book: Book = bookRepository.findById(id)?: throw EntityNotFoundException("書籍が見つかりませんでした")
        val updated: Book = book.update(
            title = param.title,
            authorId = param.authorId
        )
        bookRepository.update(updated)

        return BookDto.fromDomain(updated)
    }
}