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
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.slot

class UpdateAuthorUseCaseTest : BehaviorSpec({

    Given("著者を更新するユースケース") {
        val mockAuthorRepository: AuthorRepository = mockk()
        val updateAuthorUseCase = UpdateAuthorUseCase(
            authorRepository = mockAuthorRepository,
        )
        And("正常系") {
            val author: Author = Author.create(
                name = AuthorName("てすと 太郎"),
                kana = AuthorKana("てすと たろう"),
            )

            // モックの設定
            every { mockAuthorRepository.fetchById(author.identifier) } returns author

            val slot = slot<Author>()
            justRun { mockAuthorRepository.update(capture(slot)) }

            val updatedAuthorName = AuthorName("てすと 次郎")
            val updatedAuthorKana = AuthorKana("てすと じろう")
            val param = UpdateAuthorParam(
                name = updatedAuthorName,
                kana = updatedAuthorKana,
            )

            When("実行した時") {
                updateAuthorUseCase.execute(
                    id = author.identifier,
                    param = param,
                )
                Then("指定したIDの著者が更新されること") {
                    assertSoftly(slot.captured) {
                        identifier.shouldBe(author.identifier)
                        name.shouldBe(updatedAuthorName)
                        kana.shouldBe(updatedAuthorKana)
                    }
                }
            }
        }
        And("異常系") {
            And("指定した著者IDが存在しない時") {
                val author: Author = Author.create(
                    name = AuthorName("てすと 太郎"),
                    kana = AuthorKana("てすと たろう"),
                )

                // モックの設定
                every { mockAuthorRepository.fetchById(author.identifier) } returns null

                val updateAuthorName = AuthorName("てすと 次郎")
                val updateAuthorKana = AuthorKana("てすと じろう")
                val param = UpdateAuthorParam(
                    name = updateAuthorName,
                    kana = updateAuthorKana,
                )

                When("実行した時") {
                    Then("例外が発生すること") {
                        shouldThrow<EntityNotFoundException> {
                            updateAuthorUseCase.execute(
                                id = author.identifier,
                                param = param,
                            )
                        }.message.shouldBe("著者が見つかりませんでした")
                    }
                }
            }
        }
    }
})
