package com.qcbookapp.useCase.book

import com.qcbookapp.domain.model.EntityNotFoundException
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
import io.mockk.mockk

class GetAllBooksByAuthorIdUseCaseTest : BehaviorSpec({
    Given("著者に紐づく全ての書籍を取得するユースケース") {
        And("正常系") {
            And("書籍が存在する場合") {
                val mockBookRepository: BookRepository = mockk()
                val mockAuthorRepository: AuthorRepository = mockk()

                val getAllBooksByAuthorIdUseCase = GetAllBooksByAuthorIdUseCase(
                    bookRepository = mockBookRepository,
                    authorRepository = mockAuthorRepository,
                )

                val author: Author = Author.create(
                    name = AuthorName("てすと 太郎"),
                    kana = AuthorKana("てすと たろう"),
                )
                val books: List<Book> = listOf(
                    Book.create(
                        title = BookTitle("テスト"),
                        authorId = author.identifier,
                    ),
                    Book.create(
                        title = BookTitle("テスト2"),
                        authorId = author.identifier,
                    ),
                )

                // モックの設定
                every { mockBookRepository.findByAuthorId(authorId = author.identifier) } returns books
                every { mockAuthorRepository.fetchById(author.identifier) } returns author

                When("実行した時") {
                    val result: AuthorBooksDto = getAllBooksByAuthorIdUseCase.execute(
                        authorId = author.identifier,
                    )

                    Then("著者に紐づく書籍が全件が取得されること") {
                        assertSoftly(result) {
                            author.identifier.shouldBe(author.identifier)
                            books.size.shouldBe(books.size)
                        }
                        result.books.size.shouldBe(books.size)
                    }
                }
            }

            And("書籍が存在しない場合") {
                val author: Author = Author.create(
                    name = AuthorName("てすと 太郎"),
                    kana = AuthorKana("てすと たろう"),
                )

                val mockBookRepository: BookRepository = mockk()
                val mockAuthorRepository: AuthorRepository = mockk()

                val getAllBooksByAuthorIdUseCase = GetAllBooksByAuthorIdUseCase(
                    bookRepository = mockBookRepository,
                    authorRepository = mockAuthorRepository,
                )

                // モックの設定
                every { mockBookRepository.findByAuthorId(authorId = author.identifier) } returns emptyList()
                every { mockAuthorRepository.fetchById(author.identifier) } returns author
                When("実行した時") {
                    val result: AuthorBooksDto = getAllBooksByAuthorIdUseCase.execute(
                        authorId = author.identifier,
                    )

                    Then("書籍は空のリストであること") {
                        assertSoftly(result) {
                            books.size.shouldBe(0)
                            author.identifier.shouldBe(author.identifier)
                        }
                    }
                }
            }
        }

        And("異常系") {
            And("著者が存在しない場合") {
                val mockBookRepository: BookRepository = mockk()
                val mockAuthorRepository: AuthorRepository = mockk()

                val getAllBooksByAuthorIdUseCase = GetAllBooksByAuthorIdUseCase(
                    bookRepository = mockBookRepository,
                    authorRepository = mockAuthorRepository,
                )

                val author: Author = Author.create(
                    name = AuthorName("てすと 太郎"),
                    kana = AuthorKana("てすと たろう"),
                )
                val books: List<Book> = listOf(
                    Book.create(
                        title = BookTitle("テスト"),
                        authorId = author.identifier,
                    ),
                    Book.create(
                        title = BookTitle("テスト2"),
                        authorId = author.identifier,
                    ),
                )

                // モックの設定
                every { mockBookRepository.findByAuthorId(authorId = author.identifier) } returns books
                every { mockAuthorRepository.fetchById(author.identifier) } returns null

                When("実行した時") {
                    Then("例外が発生すること") {
                        shouldThrow<EntityNotFoundException> {
                            getAllBooksByAuthorIdUseCase.execute(
                                authorId = author.identifier,
                            )
                        }.message.shouldBe("著者が見つかりませんでした")
                    }
                }
            }
        }
    }
})
