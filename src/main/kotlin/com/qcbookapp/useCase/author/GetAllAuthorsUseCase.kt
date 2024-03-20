package com.qcbookapp.useCase.author

import com.qcbookapp.domain.model.author.Author
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * 全ての著者を取得するユースケース
 */
@Component
@Transactional(readOnly = true)
class GetAllAuthorsUseCase(
    private val authorRepository: AuthorRepository
) {
    fun execute(): List<AuthorDto> {
        val authors: List<Author> = authorRepository.findAll()

        return authors.map { AuthorDto.fromDomain(it) }
    }
}