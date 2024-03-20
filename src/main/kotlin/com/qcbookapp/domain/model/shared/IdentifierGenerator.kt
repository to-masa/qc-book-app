package com.qcbookapp.domain.model.shared

import java.util.UUID

object IdentifierGenerator {
    fun execute(): Long {
        // 正の値のみを生成するために、UUIDの上位ビットを取得し、Long.MAX_VALUEとのAND演算を行う
        return UUID.randomUUID().mostSignificantBits and Long.MAX_VALUE
    }
}