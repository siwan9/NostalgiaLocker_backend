package Nostalgia.backend.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 파라미터 바인딩 오류
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "Invalid Input Value"),
    // 권한이 없을 때
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "C002", "Unauthorized"),
    // 접근이 불가능 할 때
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "C003", "Access Denied"),
    // 리소스를 찾을 수 없을 때
    NOT_FOUND(HttpStatus.NOT_FOUND, "C004", "Not Found");

    private final HttpStatus httpstatus;
    private final String code;
    private final String message;

}
