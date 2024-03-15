package com.qcbookapp.presentation.rest.book

import com.qcbookapp.domain.model.book.Book
import com.qcbookapp.useCase.book.GetAllBooksUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

const val BOOKS_PATH: String = "/books"

/**
 * 書籍のREST APIコントローラークラス
 */
@RestController
class BookController(
    private val getAllBooksUseCase: GetAllBooksUseCase
) {
    @GetMapping(BOOKS_PATH)
    fun getAllBooks(): List<Book> {
        return getAllBooksUseCase.execute()
    }
}