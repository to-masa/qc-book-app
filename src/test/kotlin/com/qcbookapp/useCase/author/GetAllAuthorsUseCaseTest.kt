package com.qcbookapp.useCase.author

import com.qcbookapp.domain.model.author.Author
import com.qcbookapp.domain.model.author.AuthorKana
import com.qcbookapp.domain.model.author.AuthorName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class GetAllAuthorsUseCaseTest : BehaviorSpec({

    Given("著者一覧を取得するユースケース") {
        When("著者が存在する時") {
            val authors = listOf(
                Author.create(
                    name = AuthorName("てすと 太郎"),
                    kana = AuthorKana("てすと たろう"),
                ),
                Author.create(
                    name = AuthorName("てすと 次郎"),
                    kana = AuthorKana("てすと じろう"),
                ),
            )

            val mockAuthorRepository: AuthorRepository = mockk()
            val getAllAuthorsUseCase = GetAllAuthorsUseCase(
                authorRepository = mockAuthorRepository,
            )

            every { mockAuthorRepository.findAll() } returns authors

            val result: List<AuthorDto> = getAllAuthorsUseCase.execute()

            Then("全件が取得されること") {
                result.size.shouldBe(authors.size)
            }
        }

        When("著者が存在しない時") {
            val mockAuthorRepository: AuthorRepository = mockk()
            val getAllAuthorsUseCase = GetAllAuthorsUseCase(
                authorRepository = mockAuthorRepository,
            )

            every { mockAuthorRepository.findAll() } returns emptyList()

            val result: List<AuthorDto> = getAllAuthorsUseCase.execute()

            Then("空のリストが返ること") {
                result.size.shouldBe(0)
            }
        }
    }
})
