package www.dream.bbs.fileattachment.model.dto;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import www.dream.bbs.fileattachment.model.PlaybleContentTypes;
import www.dream.bbs.fileattachment.service.AttachFileCleaner;

@Entity
@Table(name = "t_attach")
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

	// 서버에서 관리된 경로 정보
	@Column(name = "path")
	private String pathName;

	// 원본 파일 이름. 화면에 출력 용도
	// c:\sss/bb/aaa.txt => aaa.txt
	@Column(name = "name")
	private String originalFilePureName;

	@Column(name = "type_name")
	private PlaybleContentTypes contentType;

	public AttachFileDTO(String subPath, String originalFilePureName, String uuid) {
		this.pathName = subPath;
		this.originalFilePureName = originalFilePureName;
		this.uuid = uuid;
	}

	public File findThumnailFile(String uploadDir) {
		return new File(uploadDir + File.separator + convertToPath() + File.separator + thumbFileName());
	}

	public File findUploadedFile(String uploadDir) {
		return new File(uploadDir + File.separator + convertToPath() + File.separator + pureFileName());
	}

	public void setContentType(PlaybleContentTypes contentType) {
		this.contentType = contentType;
	}

	public void deleteUploadedFiles(String uploadDir) {
		findUploadedFile(uploadDir).delete();
		findThumnailFile(uploadDir).delete();
	}

	private String convertToPath() {
		return pathName.replace(AttachFileCleaner.DATE_STRING_DELIMETER, File.separator.charAt(0));
	}
	private String pureFileName() {
		return uuid + '_' + originalFilePureName;
	}
	private boolean hasThumbnail() {
		return contentType.isThumbnailTarget();
	}

	private String thumbFileName() {
		return THUMBNAIL_FILE_PREFIX + uuid + '_' + originalFilePureName;
	}
}
