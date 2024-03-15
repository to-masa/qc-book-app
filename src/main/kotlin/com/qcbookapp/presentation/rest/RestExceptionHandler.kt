package com.qcbookapp.presentation.rest

import com.qcbookapp.domain.model.DomainException
import com.qcbookapp.domain.model.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handling(
        exceptionHandler: Exception
    ): ResponseEntity<ErrorResponseBody> {
        return when (exceptionHandler) {
            is EntityNotFoundException -> handleEntityNotFoundException(exceptionHandler)
            is DomainException -> handleDomainException(exceptionHandler)
            else -> handleThrowable(exceptionHandler)
        }
    }

    /**
     * EntityNotFoundException のハンドリング
     */
    private fun handleEntityNotFoundException(
        exception: EntityNotFoundException,
    ): ResponseEntity<ErrorResponseBody> {
        val httpStatus: HttpStatus = HttpStatus.NOT_FOUND
        val body: ErrorResponseBody = ErrorResponseBody(
            error = ErrorInfo(
                code = HttpStatus.NOT_FOUND.value(),
                message = exception.message ?: "値が見つかりませんでした",
            ),
        )
        return ResponseEntity<ErrorResponseBody>(body, httpStatus)
    }


    private fun handleDomainException(
        exception: Exception,
    ): ResponseEntity<ErrorResponseBody> {
        val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST
        val body: ErrorResponseBody = ErrorResponseBody(
            error = ErrorInfo(
                code = HttpStatus.BAD_REQUEST.value(),
                message = exception.message ?: "エラーが発生しました",
            ),
        )
        return ResponseEntity<ErrorResponseBody>(body, httpStatus)
    }

    /**
     * その他例外のハンドリング
     */
    private fun handleThrowable(
        exception: Throwable,
    ): ResponseEntity<ErrorResponseBody> {

        val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        val body: ErrorResponseBody = ErrorResponseBody(
            error = ErrorInfo(
                code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = "サーバーでエラーが発生しました",
            ),
        )
        return ResponseEntity<ErrorResponseBody>(body, httpStatus)
    }

    /**
     * エラー情報を返すレスポンスのボディ
     */
    data class ErrorResponseBody(
        val error: ErrorInfo,
    )

    /**
     * エラー情報
     *
     * @property code エラーコード
     * @property message エラーメッセージ
     */
    data class ErrorInfo(
        val code: Int,
        val message: String,
    )
}