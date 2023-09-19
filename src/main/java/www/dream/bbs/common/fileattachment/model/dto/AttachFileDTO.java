package www.dream.bbs.common.fileattachment.model.dto;

import java.io.File;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import www.dream.bbs.common.fileattachment.service.PlaybleContentTypes;
import www.dream.bbs.framework.util.JsonUtil;


@NoArgsConstructor
@Getter 
@Setter
public class AttachFileDTO {
	public static final String THUMBNAIL_FILE_PREFIX = "thumb_";
	public static final String THUMBNAIL_FILE_POSTFIX = ".png";
	//서버에서 관리된 경로 정보
	@Expose(serialize = true)
	private String uploadPath;
	//원본 파일 이름. 화면에 출력 용도 // c:\sss/bb/aaa.txt  => aaa.txt
	@Expose(serialize = true)
	private String originalFilePureName;
	@Expose(serialize = true)
	private String fileExt;
	@Expose(serialize = true)
	private String uuid;
	@Expose(serialize = true)
	private PlaybleContentTypes contentType;
	@Expose(serialize = true)
	private String jsonRepresentation;  //반영할때쓰이는 데이터 (react에선 안씀?)
	
	public AttachFileDTO(String uuid) {
		this.uuid = uuid;
	}

	public AttachFileDTO(String path, String originalFilePureName, String fileExt, String uuid) {

		this.uploadPath = path;
		this.originalFilePureName = originalFilePureName;
		this.fileExt = fileExt;
		this.uuid = uuid;
		
		
	}

	public String getJsonRepresentation() throws Exception { 
		return JsonUtil.getJsonRepresentation(this);
	}

	public static AttachFileDTO deserialize(String jsonStr) {
		try {
			return (AttachFileDTO) JsonUtil.deserialize(jsonStr, AttachFileDTO.class);
		} catch (Exception e) {
			return null;
		}
	}

	public File findThumnailFile() {
		return new File(uploadPath + File.separator + buildThumnailFileName(uuid + "_" + originalFilePureName));
	}
	
	public File findUploadedFile() {
		return new File(uploadPath + File.separator + uuid + "_" + originalFilePureName);
	}

	public static String buildThumnailFileName(String uploadFilePureName) {
		return THUMBNAIL_FILE_PREFIX + uploadFilePureName + THUMBNAIL_FILE_POSTFIX;
	}

	public void setContentType(PlaybleContentTypes contentType) {
		this.contentType = contentType;
	}

	public void deleteUploadedFiles() {
		findUploadedFile().delete();
		findThumnailFile().delete();
	}

	public void makeJsonRepresentation() {
		try {
			jsonRepresentation = JsonUtil.getJsonRepresentation(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
