package com.qcbookapp.domain.model

/**
 * ドメイン層で定義したルール違反の例外クラス
 */
open class DomainException(message: String) : RuntimeException(message)
