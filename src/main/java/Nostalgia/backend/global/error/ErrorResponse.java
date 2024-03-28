package Nostalgia.backend.global.error;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {
    private final String code;
    private final String status;
    private final String message;
    private final List<FieldError> fieldErrors;

    public ErrorResponse(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.status = errorCode.getHttpstatus().toString();
        this.message = errorCode.getMessage();
        this.fieldErrors = new ArrayList<>();
    }
    public ErrorResponse(ErrorCode errorCode, BindingResult bindingResult){
        this.code = errorCode.getCode();
        this.status = errorCode.getHttpstatus().toString();
        this.message = errorCode.getMessage();
        this.fieldErrors = bindingResult.getFieldErrors().stream()
                .map(FieldError::new).collect(Collectors.toList());
    }

    @Getter
    private class FieldError{
        private final String field;
        private final String rejectedValue;
        private final String defaultMessage;

        public FieldError(org.springframework.validation.FieldError fieldErrors){
            this.field = fieldErrors.getField();
            this.rejectedValue = fieldErrors.getRejectedValue() == null ? null : fieldErrors.getRejectedValue().toString();
            this.defaultMessage = fieldErrors.getDefaultMessage();
        }
    }
}
