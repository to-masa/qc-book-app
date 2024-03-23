package com.qcbookapp.presentation.rest.book

import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.book.BookId
import com.qcbookapp.presentation.rest.author.AuthorController
import com.qcbookapp.useCase.book.AuthorBooksDto
import com.qcbookapp.useCase.book.BookDto
import com.qcbookapp.useCase.book.CreateBookUseCase
import com.qcbookapp.useCase.book.GetAllBooksByAuthorIdUseCase
import com.qcbookapp.useCase.book.GetAllBooksUseCase
import com.qcbookapp.useCase.book.GetBookByIdUseCase
import com.qcbookapp.useCase.book.UpdateBookUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 書籍のREST APIコントローラークラス
 */
@RestController
class BookController(
    private val getAllBooksUseCase: GetAllBooksUseCase,
    private val getBooksByIdUseCase: GetBookByIdUseCase,
    private val getAllBooksByAuthorIdUseCase: GetAllBooksByAuthorIdUseCase,
    private val createBookUseCase: CreateBookUseCase,
    private val updateBookUseCase: UpdateBookUseCase,
) {

    companion object {
        const val BOOKS_PATH: String = "/books"
    }

    /**
     * 全ての書籍を取得する
     */
    @GetMapping(BOOKS_PATH)
    fun getAllBooks(): List<BookResponse> {
        val dto = getAllBooksUseCase.execute()
        return dto.map { BookResponse.fromDto(it) }
    }

    /**
     * 書籍を取得する
     */
    @GetMapping("$BOOKS_PATH/{id}")
    fun getBook(@PathVariable id: Long): BookResponse {
        val dto: BookDto = getBooksByIdUseCase.execute(BookId(id))
        return BookResponse.fromDto(dto)
    }

    /**
     *  著者に紐づく書籍を取得する
     */
    @GetMapping("${AuthorController.AUTHORS_PATH}/{id}$BOOKS_PATH")
    fun getBooksByAuthor(@PathVariable id: Long): AuthorBooksResponse {
        val dto: AuthorBooksDto = getAllBooksByAuthorIdUseCase.execute(AuthorId(id))
        return AuthorBooksResponse.fromDto(dto)
    }

    /**
     * 書籍を作成する
     */
    @PostMapping(BOOKS_PATH)
    fun createBook(request: CreateBookRequest): BookResponse {
        val dto: BookDto = createBookUseCase.execute(request.toParam())
        return BookResponse.fromDto(dto)
    }

    /**
     * 書籍を更新する
     */
    @PutMapping("$BOOKS_PATH/{id}")
    fun updateBook(@PathVariable id: Long, request: UpdateBookRequest): BookResponse {
        val dto = updateBookUseCase.execute(
            id = BookId(id),
            param = request.toParam(),
        )
        return BookResponse.fromDto(dto)
    }
}
