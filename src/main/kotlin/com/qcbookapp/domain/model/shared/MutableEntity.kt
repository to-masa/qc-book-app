package com.qcbookapp.domain.model.shared

/**
 * 更新可能なエンティティ
 */
interface MutableEntity<T>: Entity<T> {
    /**
     * 更新日時
     */
    val updatedAt: UpdatedAt
}