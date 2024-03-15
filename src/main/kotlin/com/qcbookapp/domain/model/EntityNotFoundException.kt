package com.qcbookapp.domain.model

/**
 * ドメイン層で定義したルール違反の例外クラス
 */
class EntityNotFoundException(message: String): DomainException(message)