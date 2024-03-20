package com.qcbookapp.domain.model.book

import com.qcbookapp.domain.model.DomainException
import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.domain.model.shared.CreatedAt
import com.qcbookapp.domain.model.shared.UpdatedAt
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import java.time.LocalDateTime

class BookTest : BehaviorSpec({

    Given("createメソッド") {
        And("正常系") {
            val title = "タイトル"
            val authorId = 1L
            When("作成した時") {
                val created: Book = Book.create(
                    title = BookTitle(title),
                    authorId = AuthorId(authorId)
                )
                Then("書籍IDが作成されること") {
                    created.identifier.shouldNotBeNull()
                    created.identifier.value.shouldBeTypeOf<Long>()

                }
                Then("書籍タイトルが指定した値で作成されること") {
                    created.title.value.shouldBe(title)
                }
                Then("著者IDが指定した値で作成されること") {
                    created.authorId.value.shouldBe(authorId)
                }
                Then("作成日時が作成されること") {
                    created.createdAt.shouldNotBeNull()
                    created.createdAt.value.shouldBeTypeOf<LocalDateTime>()
                }
                Then("更新日時が作成されること") {
                    created.updatedAt.shouldNotBeNull()
                    created.updatedAt.value.shouldBeTypeOf<LocalDateTime>()
                }
            }
        }
        And("異常系") {
            And("書籍タイトルが空文字の場合") {
                val title = ""
                val authorId = 1L
                When("作成した時") {
                    Then("例外が発生すること") {
                        shouldThrow<DomainException> {
                            Book.create(
                                title = BookTitle(title),
                                authorId = AuthorId(authorId)
                            )
                        }.message.shouldBe("書籍タイトルは1文字以上で入力してください")
                    }
                }
            }
            And("書籍タイトルが101文字の場合") {
                val title = "あ".repeat(101)
                val authorId = 1L
                When("作成した時") {
                    Then("例外が発生すること") {
                        shouldThrow<DomainException> {
                            Book.create(
                                title = BookTitle(title),
                                authorId = AuthorId(authorId)
                            )
                        }.message.shouldBe("書籍タイトルは100文字以下で入力してください")
                    }
                }
            }
        }
    }

    Given("updateメソッド") {
        val title = "タイトル"
        val authorId = 1L
        val before: Book = Book.create(
            title = BookTitle(title),
            authorId = AuthorId(authorId)
        )
        And("正常系") {
            When("更新した時") {
                val updatedTitle = "更新後のタイトル"
                val updatedAuthorId = 2L
                val updated: Book = before.update(
                    title = BookTitle(updatedTitle),
                    authorId = AuthorId(updatedAuthorId)
                )
                Then("書籍IDが更新されないこと") {
                    updated.identifier.shouldBe(before.identifier)
                }
                Then("書籍タイトルが指定した値で更新されること") {
                    updated.title.value.shouldBe(updatedTitle)
                }
                Then("著者IDが指定した値で更新されること") {
                    updated.authorId.value.shouldBe(updatedAuthorId)
                }
                Then("作成日時が更新されないこと") {
                    updated.createdAt.shouldBe(before.createdAt)
                }
                Then("更新日時が更新されること") {
                    updated.updatedAt.value.shouldBeAfter(before.createdAt.value)
                }
            }
        }

        And("異常系") {
            And("書籍タイトルが空文字の場合") {
                val updatedTitle = ""
                val updatedAuthorId = 2L
                When("更新した時") {
                    Then("例外が発生すること") {
                        shouldThrow<DomainException> {
                            before.update(
                                title = BookTitle(updatedTitle),
                                authorId = AuthorId(updatedAuthorId)
                            )
                        }.message.shouldBe("書籍タイトルは1文字以上で入力してください")
                    }
                }
            }
            And("書籍タイトルが101文字の場合") {
                val updatedTitle = "あ".repeat(101)
                val updatedAuthorId = 2L
                When("更新した時") {
                    Then("例外が発生すること") {
                        shouldThrow<DomainException> {
                            before.update(
                                title = BookTitle(updatedTitle),
                                authorId = AuthorId(updatedAuthorId)
                            )
                        }.message.shouldBe("書籍タイトルは100文字以下で入力してください")
                    }
                }
            }
        }
    }

    Given("reconstructメソッド") {
        And("正常系") {
            val id = 1L
            val title = "タイトル"
            val authorId = 1L
            val createdAt = LocalDateTime.now()
            val updatedAt = LocalDateTime.now()
            When("再構築した時") {
                val reconstructed: Book = Book.reconstruct(
                    id = BookId(id),
                    title = BookTitle(title),
                    authorId = AuthorId(authorId),
                    createdAt = CreatedAt(createdAt),
                    updatedAt = UpdatedAt(updatedAt)
                )
                Then("書籍IDが指定した値で再構築されること") {
                    reconstructed.identifier.value.shouldBe(id)
                }
                Then("書籍タイトルが指定した値で再構築されること") {
                    reconstructed.title.value.shouldBe(title)
                }
                Then("著者IDが指定した値で再構築されること") {
                    reconstructed.authorId.value.shouldBe(authorId)
                }
                Then("作成日時が指定した値で再構築されること") {
                    reconstructed.createdAt.value.shouldBe(createdAt)
                }
                Then("更新日時が指定した値で再構築されること") {
                    reconstructed.updatedAt.value.shouldBe(updatedAt)
                }
            }
        }

        And("異常系") {
            And("書籍タイトルが空文字の場合") {
                val id = 1L
                val title = ""
                val authorId = 1L
                val createdAt = LocalDateTime.now()
                val updatedAt = LocalDateTime.now()
                When("再構築した時") {
                    Then("例外が発生すること") {
                        shouldThrow<DomainException> {
                            Book.reconstruct(
                                id = BookId(id),
                                title = BookTitle(title),
                                authorId = AuthorId(authorId),
                                createdAt = CreatedAt(createdAt),
                                updatedAt = UpdatedAt(updatedAt)
                            )
                        }.message.shouldBe("書籍タイトルは1文字以上で入力してください")
                    }
                }
            }
            And("書籍タイトルが101文字の場合") {
                val id = 1L
                val title = "あ".repeat(101)
                val authorId = 1L
                val createdAt = LocalDateTime.now()
                val updatedAt = LocalDateTime.now()
                When("再構築した時") {
                    Then("例外が発生すること") {
                        shouldThrow<DomainException> {
                            Book.reconstruct(
                                id = BookId(id),
                                title = BookTitle(title),
                                authorId = AuthorId(authorId),
                                createdAt = CreatedAt(createdAt),
                                updatedAt = UpdatedAt(updatedAt)
                            )
                        }.message.shouldBe("書籍タイトルは100文字以下で入力してください")
                    }
                }
            }
        }
    }
})
