package com.qcbookapp.domain.model.author

import com.qcbookapp.domain.model.shared.Identifier
import com.qcbookapp.domain.model.shared.IdentifierGenerator

/**
 * 著者ID
 * - IDオブジェクト
 */
class AuthorId(value: Long = IdentifierGenerator.execute()): Identifier(value = value)