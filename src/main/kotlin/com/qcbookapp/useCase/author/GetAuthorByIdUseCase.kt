package com.qcbookapp.useCase.author

import com.qcbookapp.domain.model.EntityNotFoundException
import com.qcbookapp.domain.model.author.Author
import com.qcbookapp.domain.model.author.AuthorId
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * 著者を取得するユースケース
 */
@Component
@Transactional(readOnly = true)
class GetAuthorByIdUseCase(
    private val authorRepository: AuthorRepository,
) {
    fun execute(id: AuthorId): AuthorDto {
        val author: Author = authorRepository.fetchById(id) ?: throw EntityNotFoundException("書籍が見つかりませんでした")
        return AuthorDto.fromDomain(author)
    }
}
