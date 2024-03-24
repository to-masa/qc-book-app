package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.EntityNotFoundException
import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.book.Book
import com.qcbookapp.domain.model.book.BookTitle
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class GetBookByIdUseCaseTest : BehaviorSpec({
    Given("書籍を取得するユースケース") {
        val mockkBookRepository: BookRepository = mockk()
        val getBookByIdUseCase = GetBookByIdUseCase(
            bookRepository = mockkBookRepository,
        )
        And("正常系") {
            val book: Book = Book.create(
                title = BookTitle("テスト"),
                authorId = AuthorId(1L),
            )

            // モックの設定
            every { mockkBookRepository.fetchById(book.identifier) } returns book

            When("実行した時") {
                val result: BookDto = getBookByIdUseCase.execute(
                    id = book.identifier,
                )
                Then("指定したIDの書籍が取得されること") {
                    assertSoftly(result) {
                        id.shouldBe(book.identifier.value)
                        title.shouldBe(book.title.value)
                        authorId.shouldBe(book.authorId.value)
                    }
                }
            }
        }

        And("異常系") {
            And("指定したIDの書籍が存在しない時") {
                val book: Book = Book.create(
                    title = BookTitle("テスト"),
                    authorId = AuthorId(1L),
                )

                // モックの設定
                every { mockkBookRepository.fetchById(book.identifier) } returns null

                When("実行した時") {
                    Then("例外が発生すること") {
                        shouldThrow<EntityNotFoundException> {
                            getBookByIdUseCase.execute(
                                id = book.identifier,
                            )
                        }.message.shouldBe("書籍が見つかりませんでした")
                    }
                }
            }
        }
    }
})
