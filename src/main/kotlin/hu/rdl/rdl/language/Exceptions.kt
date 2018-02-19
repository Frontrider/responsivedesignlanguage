package hu.rdl.rdl.language

class InvalidMediaTagException(message: String = "", val errorDetail: List<ErrorDetail>) : RuntimeException(message)

data class ErrorDetail(
        val message: String,
        val code: ErrorCode,
        val information: String
)

enum class ErrorCode {
    REQUIRED, INVALID
}