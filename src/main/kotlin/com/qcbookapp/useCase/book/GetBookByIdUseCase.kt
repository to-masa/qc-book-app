package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.EntityNotFoundException
import com.qcbookapp.domain.model.book.Book
import com.qcbookapp.domain.model.book.BookId
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * 書籍を取得するユースケース
 */
@Component
@Transactional(readOnly = true)
class GetBookByIdUseCase(
    private val bookRepository: BookRepository,
) {
    fun execute(id: BookId): BookDto {
        val book: Book = bookRepository.fetchById(id) ?: throw EntityNotFoundException("書籍が見つかりませんでした")
        return BookDto.fromDomain(book)
    }
}
