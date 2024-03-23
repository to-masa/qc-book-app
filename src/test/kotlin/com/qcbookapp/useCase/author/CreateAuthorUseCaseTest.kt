package com.qcbookapp.useCase.author

import com.qcbookapp.domain.model.author.Author
import com.qcbookapp.domain.model.author.AuthorKana
import com.qcbookapp.domain.model.author.AuthorName
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.slot

class CreateAuthorUseCaseTest : BehaviorSpec({
    Given("著者を作成するユースケース") {
        val mockAuthorRepository: AuthorRepository = mockk()
        val createAuthorUseCase = CreateAuthorUseCase(
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
            justRun { mockAuthorRepository.insert(capture(slot)) }

            val param = CreateAuthorParam(
                name = author.name,
                kana = author.kana,
            )
            When("実行した時") {
                createAuthorUseCase.execute(
                    param = param,
                )
                Then("著者が指定した値で作成されていること") {
                    assertSoftly(slot.captured) {
                        name.shouldBe(author.name)
                        kana.shouldBe(author.kana)
                    }
                }
            }
        }

        And("異常系") {
            // ユースケース層で定義したルール・制約はないので省略
        }
    }
})
