package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.book.Book
import org.springframework.stereotype.Component

/**
 * 全ての書籍を取得するユースケース
 */
@Component
class GetAllBooksUseCase(
    private val bookRepository: BookRepository
) {
    fun execute(): List<Book> {
        return bookRepository.findAll()
    }
}