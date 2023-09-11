package www.dream.bbs.iis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.dream.bbs.iis.model.TagVO;
import www.dream.bbs.iis.repository.TagRepository;

@Service
public class TagService {
	@Autowired
	private TagRepository tagRepository;
	
	public List<TagVO> getAll() {
		return tagRepository.findAll();
	}
	
	public Optional<TagVO> getTag(String id) {
		return tagRepository.findById(id);
	}
	
	public TagVO createTag(TagVO tag) {
		tag.setId(tagRepository.getId("s_tag"));
		return tagRepository.save(tag);
	}
}