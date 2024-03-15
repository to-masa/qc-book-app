package com.qcbookapp.domain.model.shared

interface MutableEntity<T>: Entity<T> {
    /**
     * 更新日時
     */
    val updatedAt: UpdatedAt
}