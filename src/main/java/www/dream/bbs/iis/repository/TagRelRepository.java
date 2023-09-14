package www.dream.bbs.iis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import www.dream.bbs.iis.model.TagRelId;
import www.dream.bbs.iis.model.TagRelVO;

public interface TagRelRepository extends JpaRepository<TagRelVO, TagRelId>{
}
