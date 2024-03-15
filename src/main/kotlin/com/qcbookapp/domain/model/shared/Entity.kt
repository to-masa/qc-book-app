package com.qcbookapp.domain.model.shared

interface Entity<T> {
    // 識別子
    val identifier: Identifier
    // 作成日時
    val createdAt: CreatedAt
}