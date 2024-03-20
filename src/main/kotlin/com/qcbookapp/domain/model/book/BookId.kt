package com.qcbookapp.domain.model.book

import com.qcbookapp.domain.model.shared.Identifier
import com.qcbookapp.domain.model.shared.IdentifierGenerator

/**
 * 書籍ID
 * - IDオブジェクト
 */
class BookId(value: Long = IdentifierGenerator.execute()): Identifier(value = value)