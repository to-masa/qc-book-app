package com.qcbookapp.useCase.author

import com.qcbookapp.domain.model.author.Author
import com.qcbookapp.presentation.rest.author.CreateAuthorParam
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * 著者を作成するユースケース
 */
@Component
@Transactional
class CreateAuthorUseCase(
    private val authorRepository: AuthorRepository
) {
    fun execute(param: CreateAuthorParam): AuthorDto {

        // 書籍を作成する
        val created: Author = Author.create(
            name = param.name,
            kana = param.kana
        )
        authorRepository.insert(created)

        return AuthorDto.fromDomain(created)
    }
}