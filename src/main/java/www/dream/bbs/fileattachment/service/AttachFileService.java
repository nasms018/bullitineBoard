package www.dream.bbs.fileattachment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.dream.bbs.board.model.PostVO;
import www.dream.bbs.fileattachment.model.MappedTableDef;
import www.dream.bbs.fileattachment.model.dto.AttachFileDTO;
import www.dream.bbs.fileattachment.repository.AttachFileRepository;

@Service
public class AttachFileService {
	
	@Autowired
	private AttachFileRepository attachFileRepository;
	
	public List<AttachFileDTO> getAttachFileList(MappedTableDef owner){
		List<AttachFileDTO> ret = attachFileRepository.findByOwnerTypeAndOwnerId(owner.getMappedTableName(), owner.getId());
		return ret;
		//attachFileRepository.findAllById(null);
	}
	
	public void createAttachFiles(MappedTableDef owner) {

		
		List<AttachFileDTO> list = owner.getListAttachFile();
		list.forEach(e->{e.setOwnerType(owner.getMappedTableName());
			e.setOwnerId(owner.getId());
		});
		/* 두개 다 아래와 동일
		for (AttachFileDTO e : list) {
			attachFileRepository.save(e);
		}
		
		list.forEach(e->attachFileRepository.save(e));
		*/
		attachFileRepository.saveAll(list);
	}

}
