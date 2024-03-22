package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.book.Book
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * 全ての書籍を取得するユースケース
 */
@Component
@Transactional(readOnly = true)
class GetAllBooksUseCase(
    private val bookRepository: BookRepository,
) {
    fun execute(): List<BookDto> {
        val books: List<Book> = bookRepository.findAll()

        return books.map { BookDto.fromDomain(it) }
    }
}
