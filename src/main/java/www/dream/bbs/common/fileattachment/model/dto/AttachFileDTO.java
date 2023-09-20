package www.dream.bbs.common.fileattachment.model.dto;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import www.dream.bbs.common.fileattachment.service.PlaybleContentTypes;
import www.dream.bbs.framework.util.JsonUtil;

@Entity
@Table(name="T_attach")
@NoArgsConstructor
@Getter 
@Setter
public class AttachFileDTO {
	
	/*
	owner_type		varchar(255),	/* 테이블 이름 적는 곳 T_party, T_reply 
	owner_id		varchar(255),	/* 대상테이블의 기본키 
	uuid			char(4),
	path		varchar(2000),
	name			varchar(500),
	ext				varchar(255),
	type			varchar(50),
	primary key(owner_id,uuid)
	*/
	
	
	
	
	public static final String THUMBNAIL_FILE_PREFIX = "thumb_";
	public static final String THUMBNAIL_FILE_POSTFIX = ".png";
	
	@Id
	@Expose(serialize = true)
	private String uuid;
	
	//서버에서 관리된 경로 정보
	@Column(name="path")
	@Expose(serialize = true)
	private String uploadPath;
	
	//원본 파일 이름. 화면에 출력 용도 // c:\sss/bb/aaa.txt  => aaa.txt
	@Column(name="name")
	@Expose(serialize = true)
	private String originalFilePureName;
	
	@Column(name="ext")
	@Expose(serialize = true)
	private String fileExt;
	
	 //@Enumerated(EnumType.ORDINAL)
	@Column(name="")
	@Expose(serialize = true)
	private PlaybleContentTypes contentType;
	
	@Column(name="")
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
		return new File(uploadPath + File.separator + buildThumnailFileName(uuid + originalFilePureName));
	}
	
	public File findUploadedFile() {
		return new File(uploadPath + File.separator + uuid + originalFilePureName);
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
	
}
