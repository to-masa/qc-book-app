package com.qcbookapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QcBookAppApplication

fun main(args: Array<String>) {
    runApplication<QcBookAppApplication>(*args)
}
