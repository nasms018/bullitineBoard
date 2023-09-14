package www.dream.bbs.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import www.dream.bbs.security.service.SignService;

//@Slf4j    //(Simple Logging Facade for Java) 퍼싸드 패턴을 수단으로하는 자바 로깅 제공
@RestControllerAdvice
public class GlobalExceptionHandler {
	private final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * BusinessException에서 발생한 에러
     *
     * @param ex BusinessException
     * @return ResponseEntity
     */
    @ExceptionHandler({BusinessException.class})
    protected ResponseEntity<ErrorResponse> handleCustomException(BusinessException ex) {
		LOGGER.info("===================글로벌익셉션=================================");


        final ErrorResponse response = ErrorResponse.of(ErrorCode.BUSINESS_EXCEPTION_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}