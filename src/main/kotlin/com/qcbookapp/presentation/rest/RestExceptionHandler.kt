package com.qcbookapp.presentation.rest

import com.qcbookapp.domain.model.DomainException
import com.qcbookapp.domain.model.EntityNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception

@RestControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handling(
        exceptionHandler: Exception,
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
        val body = ErrorResponseBody(
            error = ErrorInfo(
                code = HttpStatus.NOT_FOUND.value(),
                message = exception.message ?: ApiError.NOT_FOUND.message,
            ),
        )
        return ResponseEntity<ErrorResponseBody>(body, httpStatus)
    }

    private fun handleDomainException(
        exception: Exception,
    ): ResponseEntity<ErrorResponseBody> {
        val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST
        val body = ErrorResponseBody(
            error = ErrorInfo(
                code = HttpStatus.BAD_REQUEST.value(),
                message = exception.message ?: ApiError.BAD_REQUEST.message,
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
        // FIXME: エラー通知などを実装する際に、引数の exception のメッセージを参照する
        println(exception.message)

        val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        val body = ErrorResponseBody(
            error = ErrorInfo(
                code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = ApiError.INTERNAL_SERVER_ERROR.message,
            ),
        )
        return ResponseEntity<ErrorResponseBody>(body, httpStatus)
    }

    /**
     * APIでのエラー情報を返すレスポンスのボディ
     * - 他の例外発生時のレスポンスの構成と統一するためにカスタマイズしている
     */
    override fun handleExceptionInternal(
        exception: Exception,
        body: Any?,
        headers: HttpHeaders,
        statusCode: HttpStatusCode,
        request: WebRequest,
    ): ResponseEntity<Any>? {
        val errorBody = ErrorResponseBody(
            error = ErrorInfo(
                code = statusCode.value(),
                message = when (statusCode) {
                    HttpStatus.NOT_FOUND -> ApiError.NOT_FOUND.message
                    // FIXME: 何が原因で BAD_REQUEST になったかをメッセージに含めたいが、現状は入力によるエラーでることを返す
                    HttpStatus.BAD_REQUEST -> ApiError.BAD_REQUEST.message
                    HttpStatus.INTERNAL_SERVER_ERROR -> ApiError.INTERNAL_SERVER_ERROR.message
                    else -> ""
                },
            ),
        )
        return super.handleExceptionInternal(exception, errorBody, headers, statusCode, request)
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

    /**
     * REST-API上で例外が発生した場合に返すエラー
     * */
    enum class ApiError(
        /** エラーコード */
        val code: String,
        /** エラーメッセージ */
        val message: String,
    ) {
        /** 見つからなかった場合に返すエラー */
        NOT_FOUND(
            code = HttpStatus.NOT_FOUND.value().toString(),
            message = "見つかりませんでした",
        ),

        /** クライアント側からの不正なリクエストに返すエラー */
        BAD_REQUEST(
            code = HttpStatus.BAD_REQUEST.value().toString(),
            message = "入力エラーです",
        ),

        /** サーバーエラーが発生した場合に返すエラー */
        INTERNAL_SERVER_ERROR(
            code = HttpStatus.INTERNAL_SERVER_ERROR.value().toString(),
            message = "サーバーでエラーが発生しました",
        ),
    }
}
