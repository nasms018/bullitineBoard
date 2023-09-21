package www.dream.bbs.fileattachment.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Embeddable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import www.dream.bbs.fileattachment.model.dto.AttachFileDTO;
import www.dream.bbs.iis.model.TagVO;

public interface AttachFileRepository extends JpaRepository<AttachFileDTO, String>{
	/** list up */ 
	/** create
	@Query(nativeQuery = true, value="insert into T_attach(owner_type, owner_id, uuid, path, name, type_ordinal)"
			                      + "values(:obj.ownerType, :obj.ownerId, :obj.uuid, :obj.uploadPath, :obj.originalFilePureName, :obj.typeOrdinal, )")
	int saveAttachFileDTO(@Param("obj") AttachFileDTO obj);
 	*/ 
	/** delete 그림 한개 지우기*/ 
	/** delete all 게시글 지우기*/ 

}
/*
	@Query(value="select NEXT_PK(:idType)", nativeQuery = true)
	String getId(@Param("idType") String idType);
	
	//단어 목록을 기반으로 TagVO 목록 찾기
	@Query(nativeQuery = true, value="SELECT * from t_tag where word IN (:words)")
	List<TagVO> findByWord(@Param("words") Set<String> words);
}



@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
class AttachFilelId implements Serializable {
	private String tgt_name;
	private String tgt_id;
	private String tag_id;
}
*/
