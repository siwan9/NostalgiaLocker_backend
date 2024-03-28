package Nostalgia.backend.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * @Validated로 바인딩 시 생기는 예외
     * @ModelAttribute
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException e){
        log.error("BindException", e);
        BindingResult bindingResult = e.getBindingResult();
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(errorResponse, ErrorCode.INVALID_INPUT_VALUE.getHttpstatus());
    }

    /**
     * @Validated로 바인딩 시 생기는 예외
     * @RequestBody
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException", e);
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(errorResponse, ErrorCode.INVALID_INPUT_VALUE.getHttpstatus());
    }

    /**
     * 권한이 없을 경우 생기는 에러
     */
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(HttpClientErrorException.Unauthorized e){
        log.error("UnauthorizedException", e);
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.UNAUTHORIZED);
        return new ResponseEntity<>(errorResponse, ErrorCode.UNAUTHORIZED.getHttpstatus());
    }
}
