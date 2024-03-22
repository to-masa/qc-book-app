package com.qcbookapp.useCase.author

import com.qcbookapp.domain.model.author.Author
import com.qcbookapp.domain.model.author.AuthorId

/**
 * 著者リポジトリ
 */
interface AuthorRepository {
    fun findAll(): List<Author>

    fun fetchById(id: AuthorId): Author?

    fun insert(author: Author)

    fun update(author: Author)
}
