package www.dream.bbs.fileattachment.model.dto;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import www.dream.bbs.fileattachment.model.PlaybleContentTypes;

@Entity
@Table(name="T_attach")
@NoArgsConstructor
@Getter 
@Setter
public class AttachFileDTO {
	public static final String THUMBNAIL_FILE_PREFIX = "thumb_";
	public static final String THUMBNAIL_FILE_POSTFIX = ".png";
	
	@Id
	private String uuid;
	
	private String ownerType;
	private String ownerId;
	
	//서버에서 관리된 경로 정보
	@Column(name="path")
	private String uploadPath;
	
	//원본 파일 이름. 화면에 출력 용도 // c:\sss/bb/aaa.txt  => aaa.txt
	@Column(name="name")
	private String originalFilePureName;
	
    @Column(name="type_name")
    private PlaybleContentTypes contentType;

	public AttachFileDTO(String uuid) {
		this.uuid = uuid;
	}

	public AttachFileDTO(String path, String originalFilePureName, String uuid) {
		this.uploadPath = path;
		this.originalFilePureName = originalFilePureName;
		this.uuid = uuid;
	}

	public File findThumnailFile() {
		return new File(uploadPath + File.separator + THUMBNAIL_FILE_PREFIX + uuid + THUMBNAIL_FILE_POSTFIX);
	}
	
	public File findUploadedFile() {
		return new File(uploadPath + File.separator + uuid + '_' + originalFilePureName);
	}

	public void setContentType(PlaybleContentTypes contentType) {
		this.contentType = contentType;

	}

	public void deleteUploadedFiles() {
		findUploadedFile().delete();
		findThumnailFile().delete();
	}
	
}
