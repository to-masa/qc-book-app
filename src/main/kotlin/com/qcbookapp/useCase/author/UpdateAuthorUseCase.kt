package com.qcbookapp.useCase.author

import com.qcbookapp.domain.model.EntityNotFoundException
import com.qcbookapp.domain.model.author.Author
import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.presentation.rest.author.UpdateAuthorParam
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * 著者を更新するユースケース
 */
@Component
@Transactional
class UpdateAuthorUseCase(
    private val authorRepository: AuthorRepository,
) {
    fun execute(id: AuthorId, param: UpdateAuthorParam): AuthorDto {

        // 書籍を更新する
        val author: Author = authorRepository.findById(id)?: throw EntityNotFoundException("著者が見つかりませんでした")
        val updated: Author = author.update(
            name = param.name,
            kana = param.kana
        )
        authorRepository.update(updated)

        return AuthorDto.fromDomain(updated)
    }
}