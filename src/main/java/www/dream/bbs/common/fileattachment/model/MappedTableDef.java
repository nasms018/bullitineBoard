package www.dream.bbs.common.fileattachment.model;

import java.util.List;

import www.dream.bbs.common.fileattachment.model.dto.AttachFileDTO;

public interface MappedTableDef {
	public String getMappedTableName();
	public String getId();
	public List<AttachFileDTO> getListAttachFile();
}
