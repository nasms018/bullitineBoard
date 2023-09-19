package www.dream.bbs.common.fileattachment.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import www.dream.bbs.common.exception.BusinessException;
import www.dream.bbs.party.model.PartyVO;

@RestController		//Container에 담기도록 지정
@RequestMapping("/")
public class FileAttachController {
	@Value("${upload.file.dir}")
	private String uploadDir = "C://React-App//upload";
	
	/**
	 * 게시글 등록 이전에 미리 첨부파일 전송 목적은?
	 * 무거운 것 미리 올려두자
	 */
	@PostMapping("/upload_multi")
	public ResponseEntity<Integer> uploadAttachedMultiFiles(@AuthenticationPrincipal PartyVO user,
			@RequestParam MultipartFile[] attachFiles) throws BusinessException {
		File uploadPath = new File(uploadDir ,getFolder());
		
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();  //여러 계층의 폴더를 한번에 만들기
		}
		
		for (MultipartFile aFile : attachFiles) {
			File saveFile = new File(uploadPath, aFile.getOriginalFilename());
			try {
				aFile.transferTo(saveFile);
			} catch (IllegalStateException|IOException e) {
				e.printStackTrace();
			}
		}
	
		return new ResponseEntity<>(4, HttpStatus.OK);}
	
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
	
		
}
