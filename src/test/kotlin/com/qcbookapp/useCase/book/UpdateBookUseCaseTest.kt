package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.DomainException
import com.qcbookapp.domain.model.author.Author
import com.qcbookapp.domain.model.author.AuthorKana
import com.qcbookapp.domain.model.author.AuthorName
import com.qcbookapp.domain.model.book.Book
import com.qcbookapp.domain.model.book.BookTitle
import com.qcbookapp.useCase.author.AuthorRepository
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.slot

class UpdateBookUseCaseTest : BehaviorSpec({
    Given("書籍を更新するユースケース") {
        val mockkBookRepository: BookRepository = mockk()
        val mockkAuthorRepository: AuthorRepository = mockk()
        val updateBookUseCase = UpdateBookUseCase(
            bookRepository = mockkBookRepository,
            authorRepository = mockkAuthorRepository,
        )
        And("正常系") {
            val author: Author = Author.create(
                name = AuthorName("てすと 太郎"),
                kana = AuthorKana("てすと たろう"),
            )
            val book: Book = Book.create(
                title = BookTitle("テスト"),
                authorId = author.identifier,
            )

            // 更新する著者IDの著者
            val author2 = Author.create(
                name = AuthorName("てすと 次郎"),
                kana = AuthorKana("てすと じろう"),
            )

            // モックの設定
            every { mockkBookRepository.fetchById(book.identifier) } returns book
            every { mockkAuthorRepository.fetchById(author2.identifier) } returns author2
            val slot = slot<Book>()
            justRun { mockkBookRepository.update(capture(slot)) }

            val updatedBookTitle = BookTitle("テスト2")
            val updatedAuthorId = author2.identifier
            val param = UpdateBookParam(
                title = updatedBookTitle,
                authorId = updatedAuthorId,
            )

            When("実行した時") {
                updateBookUseCase.execute(
                    id = book.identifier,
                    param = param,
                )
                Then("指定したIDの書籍が更新されること") {
                    assertSoftly(slot.captured) {
                        identifier.shouldBe(book.identifier)
                        title.shouldBe(updatedBookTitle)
                        authorId.shouldBe(updatedAuthorId)
                    }
                }
            }
        }

        And("異常系") {
            And("指定したIDの書籍が存在しない場合") {
                val author: Author = Author.create(
                    name = AuthorName("てすと 太郎"),
                    kana = AuthorKana("てすと たろう"),
                )
                val book: Book = Book.create(
                    title = BookTitle("テスト"),
                    authorId = author.identifier,
                )

                // モックの設定
                every { mockkBookRepository.fetchById(book.identifier) } returns null
                every { mockkAuthorRepository.fetchById(author.identifier) } returns author

                val param = UpdateBookParam(
                    title = book.title,
                    authorId = author.identifier,
                )
                When("実行した時") {
                    Then("例外が発生すること") {
                        shouldThrow<DomainException> {
                            updateBookUseCase.execute(
                                id = book.identifier,
                                param = param,
                            )
                        }.message.shouldBe("書籍が見つかりませんでした")
                    }
                }
            }
            And("指定した著者IDの著者が存在しない場合") {
                val author: Author = Author.create(
                    name = AuthorName("てすと 太郎"),
                    kana = AuthorKana("てすと たろう"),
                )
                val book: Book = Book.create(
                    title = BookTitle("テスト"),
                    authorId = author.identifier,
                )

                // モックの設定
                every { mockkBookRepository.fetchById(book.identifier) } returns book
                every { mockkAuthorRepository.fetchById(author.identifier) } returns null

                val param = UpdateBookParam(
                    title = book.title,
                    authorId = author.identifier,
                )

                When("実行した時") {
                    Then("例外が発生すること") {
                        shouldThrow<DomainException> {
                            updateBookUseCase.execute(
                                id = book.identifier,
                                param = param,
                            )
                        }.message.shouldBe("著者が見つかりませんでした")
                    }
                }
            }
        }
    }
})
