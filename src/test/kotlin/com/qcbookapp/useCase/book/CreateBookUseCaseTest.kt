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

class CreateBookUseCaseTest : BehaviorSpec({
    Given("書籍を作成するユースケース") {
        val mockBookRepository: BookRepository = mockk()
        val mockAuthorRepository: AuthorRepository = mockk()
        val createBookUseCase = CreateBookUseCase(
            bookRepository = mockBookRepository,
            authorRepository = mockAuthorRepository,
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

            // モックの設定
            every { mockBookRepository.fetchById(book.identifier) } returns book
            every { mockAuthorRepository.fetchById(author.identifier) } returns author
            val slot = slot<Book>()
            justRun { mockBookRepository.insert(capture(slot)) }

            val param = CreateBookParam(
                title = book.title,
                authorId = book.authorId,
            )

            When("実行した時") {
                createBookUseCase.execute(
                    param = param,
                )
                Then("書籍が指定した値で作成されていること") {
                    assertSoftly(slot.captured) {
                        title.shouldBe(book.title)
                        authorId.shouldBe(book.authorId)
                    }
                }
            }
        }

        And("異常系") {
            And("指定した著者IDが存在しない場合") {
                val author: Author = Author.create(
                    name = AuthorName("てすと 太郎"),
                    kana = AuthorKana("てすと たろう"),
                )
                val book: Book = Book.create(
                    title = BookTitle("テスト"),
                    authorId = author.identifier,
                )

                // モックの設定
                every { mockBookRepository.fetchById(book.identifier) } returns book
                every { mockAuthorRepository.fetchById(author.identifier) } returns null
                val slot = slot<Book>()
                justRun { mockBookRepository.insert(capture(slot)) }

                val param = CreateBookParam(
                    title = book.title,
                    authorId = book.authorId,
                )
                When("実行した時") {
                    Then("例外が発生すること") {
                        shouldThrow<DomainException> {
                            createBookUseCase.execute(
                                param = param,
                            )
                        }.message.shouldBe("著者が見つかりませんでした")
                    }
                }
            }
        }
    }
})
