package www.dream.bbs.iis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import www.dream.bbs.iis.model.TagVO;

public interface TagRepository extends JpaRepository<TagVO, String>{
	@Query(value="select NEXT_PK(:idType)", nativeQuery = true)
	String getId(@Param("idType") String idType);
}
