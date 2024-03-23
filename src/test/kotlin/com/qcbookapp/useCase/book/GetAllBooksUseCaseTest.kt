package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.book.Book
import com.qcbookapp.domain.model.book.BookTitle
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class GetAllBooksUseCaseTest : BehaviorSpec({
    Given("全ての書籍を取得するユースケース") {
        And("書籍が存在する場合") {
            val books: List<Book> = listOf(
                Book.create(
                    title = BookTitle("テスト"),
                    authorId = AuthorId(1L),
                ),
                Book.create(
                    title = BookTitle("テスト2"),
                    authorId = AuthorId(2L),
                ),
            )

            val mockBookRepository: BookRepository = mockk()
            val getAllBooksUseCase = GetAllBooksUseCase(
                bookRepository = mockBookRepository,
            )

            every { mockBookRepository.findAll() } returns books
            When("実行した時") {
                val result: List<BookDto> = getAllBooksUseCase.execute()

                Then("全件が取得されること") {
                    result.size.shouldBe(books.size)
                }
            }
        }

        And("書籍が存在しない場合") {
            val mockBookRepository: BookRepository = mockk()
            val getAllBooksUseCase = GetAllBooksUseCase(
                bookRepository = mockBookRepository,
            )

            every { mockBookRepository.findAll() } returns emptyList()
            When("実行した時") {
                val result: List<BookDto> = getAllBooksUseCase.execute()

                Then("空のリストが返ること") {
                    result.size.shouldBe(0)
                }
            }
        }
    }
})
