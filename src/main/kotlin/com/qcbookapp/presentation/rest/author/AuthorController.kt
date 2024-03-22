package com.qcbookapp.presentation.rest.author

import com.qcbookapp.domain.model.author.AuthorId
import com.qcbookapp.useCase.author.AuthorDto
import com.qcbookapp.useCase.author.CreateAuthorUseCase
import com.qcbookapp.useCase.author.GetAllAuthorsUseCase
import com.qcbookapp.useCase.author.GetAuthorByIdUseCase
import com.qcbookapp.useCase.author.UpdateAuthorUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

const val AUTHORS_PATH: String = "/authors"

/**
 * 著者のREST APIコントローラークラス
 */
@RestController
class AuthorController(
    private val getAllAuthorsUseCase: GetAllAuthorsUseCase,
    private val getAuthorByIdUseCase: GetAuthorByIdUseCase,
    private val createAuthorUseCase: CreateAuthorUseCase,
    private val updateAuthorUseCase: UpdateAuthorUseCase,
) {

    /**
     * 全ての著者を取得する
     */
    @GetMapping(AUTHORS_PATH)
    fun getAllBooks(): List<AuthorResponse> {
        val dto: List<AuthorDto> = getAllAuthorsUseCase.execute()
        return dto.map { AuthorResponse.fromDto(it) }
    }

    /**
     * 著者を取得する
     */
    @GetMapping("$AUTHORS_PATH/{id}")
    fun getBook(@PathVariable id: Long): AuthorResponse {
        val dto: AuthorDto = getAuthorByIdUseCase.execute(AuthorId(id))
        return AuthorResponse.fromDto(dto)
    }

    /**
     * 著者を作成する
     */
    @PostMapping(AUTHORS_PATH)
    fun createBook(request: CreateAuthorRequest): AuthorResponse {
        val dto: AuthorDto = createAuthorUseCase.execute(request.toParam())
        return AuthorResponse.fromDto(dto)
    }

    /**
     * 書籍を更新する
     */
    @PutMapping("$AUTHORS_PATH/{id}")
    fun updateBook(@PathVariable id: Long, request: UpdateAuthorRequest): AuthorResponse {
        val dto: AuthorDto = updateAuthorUseCase.execute(AuthorId(id), request.toParam())
        return AuthorResponse.fromDto(dto)
    }
}
