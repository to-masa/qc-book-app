package com.qcbookapp.useCase.author

import com.qcbookapp.domain.model.EntityNotFoundException
import com.qcbookapp.domain.model.author.Author
import com.qcbookapp.domain.model.author.AuthorKana
import com.qcbookapp.domain.model.author.AuthorName
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class GetAuthorByIdUseCaseTest : BehaviorSpec({

    Given("著者を取得するユースケース") {
        val mockAuthorRepository: AuthorRepository = mockk()
        val getAuthorByIdUseCase = GetAuthorByIdUseCase(
            authorRepository = mockAuthorRepository,
        )
        And("正常系") {
            val author: Author = Author.create(
                name = AuthorName("てすと 太郎"),
                kana = AuthorKana("てすと たろう"),
            )

            // モックの設定
            every { mockAuthorRepository.fetchById(author.identifier) } returns author
            When("実行した時") {
                val result: AuthorDto = getAuthorByIdUseCase.execute(
                    id = author.identifier,
                )
                Then("指定したIDの著者が取得されること") {
                    assertSoftly(result) {
                        id.shouldBe(author.identifier.value)
                        name.shouldBe(author.name.value)
                        kana.shouldBe(author.kana.value)
                    }
                }
            }
        }

        And("異常系") {
            And("指定したIDの著者が存在しない時") {
                val author: Author = Author.create(
                    name = AuthorName("てすと 太郎"),
                    kana = AuthorKana("てすと たろう"),
                )

                // モックの設定
                every { mockAuthorRepository.fetchById(author.identifier) } returns null

                When("実行した時") {
                    Then("例外が発生すること") {
                        shouldThrow<EntityNotFoundException> {
                            getAuthorByIdUseCase.execute(
                                id = author.identifier,
                            )
                        }.message.shouldBe("書籍が見つかりませんでした")
                    }
                }
            }
        }
    }
})
