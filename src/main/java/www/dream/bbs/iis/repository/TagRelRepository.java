package www.dream.bbs.iis.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import www.dream.bbs.iis.model.TagRelId;
import www.dream.bbs.iis.model.TagRelVO;
import www.dream.bbs.iis.model.TagVO;

public interface TagRelRepository extends JpaRepository<TagRelVO, TagRelId>{
	//@Modifying(clearAutomatically = true, flushAutomatically = true)   //DB랑 맞춰줘
	@Query(nativeQuery = true, value = "delete from t_tgt_tag where tgt_name= 'T_reply' and tgt_id = :postId")
	void deleteAllByPostId(@Param("postId") String postId);
}
