package com.qcbookapp.domain.service

import com.qcbookapp.domain.model.DomainException

/**
 * テキスト長バリデータ
 */
object TextLengthValidator {
    /**
     * テキスト長バリデーション
     * @param value バリデーション対象のテキスト
     * @param minLength 最小文字数
     * @param maxLength 最大文字数
     * @param label ラベル
     */
    fun execute(value: String, minLength: Int, maxLength: Int, label: String) {
        if (value.length < minLength) {
            throw DomainException("${label}は${minLength}文字以上で入力してください")
        }
        if (value.length > maxLength) {
            throw DomainException("${label}は${maxLength}文字以下で入力してください")
        }
    }
}
