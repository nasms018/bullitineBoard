package www.dream.bbs.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.dream.bbs.security.dto.SignInDTO;
import www.dream.bbs.security.dto.SignInResultDto;
import www.dream.bbs.security.service.SignService;

// 예제 13.28
@RestController
@RequestMapping("/sign-api")
public class SignController {

	private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
	private final SignService signService;

	@Autowired
	public SignController(SignService signService) {
		this.signService = signService;
	}

	@GetMapping("/hi")
	public String sayHi() {
		return "helllllo";
	}
	
	@PostMapping(value = "/sign-in")
	public SignInResultDto signIn(@RequestBody SignInDTO signInDTO) throws RuntimeException {
		LOGGER.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", signInDTO.getId());
		SignInResultDto signInResultDto = signService.signIn(signInDTO);

		if (signInResultDto.getCode() == 0) {
			LOGGER.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}", signInDTO.getId(), signInResultDto.getToken());
		}
		return signInResultDto;
	}

	/** CustomAccessDeniedHandler */
	@GetMapping(value = "/exception")
	public void exceptionTest() throws RuntimeException {
		throw new RuntimeException("접근이 금지되었습니다.");
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
		HttpHeaders responseHeaders = new HttpHeaders();
		// responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		LOGGER.error("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

		Map<String, String> map = new HashMap<>();
		map.put("error type", httpStatus.getReasonPhrase());
		map.put("code", "400");
		map.put("message", "에러 발생");

		return new ResponseEntity<>(map, responseHeaders, httpStatus);
	}

}