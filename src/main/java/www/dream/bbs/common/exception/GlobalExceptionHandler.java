package www.dream.bbs.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * BusinessException에서 발생한 에러
     *
     * @param ex BusinessException
     * @return ResponseEntity
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(BusinessException ex) {
        log.debug("===========================================================");
        log.debug("여기로 오는가?!");
        log.debug("===========================================================");

        final ErrorResponse response = ErrorResponse.of(ErrorCode.BUSINESS_EXCEPTION_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}