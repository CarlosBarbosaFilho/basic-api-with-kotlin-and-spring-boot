package br.com.cbgomes.adviced.handler

import br.com.cbgomes.adviced.model.ApiErrorCustomHandler
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class CustomRestExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun emptyResultDataAccessException(ex: EmptyResultDataAccessException, status: HttpStatus ): ApiErrorCustomHandler {
        return ApiErrorCustomHandler(
                status = HttpStatus.NOT_FOUND,
                message = "Promotion not found",
                errors = ""
        )
    }
}