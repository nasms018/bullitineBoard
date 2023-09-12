package www.dream.bbs.board.controller;
/**
 * RestController는 client 요청에 대한 분배 기능
 * service로 처리 위임하여 그 결과를
 * client에게 반환. 
 * 길게 업무적 처리가 들어와 있으면 실수한 것임
 */
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import www.dream.bbs.board.model.PostVO;
import www.dream.bbs.board.model.ReplyVO;
import www.dream.bbs.board.service.PostService;
import www.dream.bbs.framework.model.PagingDTO;
import www.dream.bbs.framework.model.Pair;
import www.dream.bbs.framework.nlp.pos.service.NounExtractor;

@RestController		//Container에 담기도록 지정
@RequestMapping("/post")
//@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Requestor-Type", exposedHeaders = "x-auth-token")
public class PostController {
	@Autowired
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	// /post/anonymous/listAll/0001/1
	@GetMapping("/anonymous/listAll/{boardId}/{page}")
	public ResponseEntity<Pair<List<PostVO>, PagingDTO>> listAllPost(@PathVariable String boardId, @PathVariable int page) {
		Pair<List<PostVO>, PagingDTO> result = postService.listAllPost(boardId, page);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// /post/anonymous/search/{boardId}/{search}/{page}
	@GetMapping("/anonymous/search/{boardId}/{search}/{page}")
	public ResponseEntity<Pair<List<PostVO>, PagingDTO>> search(@PathVariable String boardId, @PathVariable String search, @PathVariable int page) {
		Pair<List<PostVO>, PagingDTO> result = postService.search(boardId, search, page);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	/** 원글 상세. 첨부파일 목록, 댓글, 대댓 목록이 내부 변수에 채워짐 */
	// /post/anonymous/getPost/p001
	@GetMapping("/anonymous/getPost/{id}")
	public ResponseEntity<PostVO> findById(@PathVariable String id) {
		PostVO post = postService.findById(id);
		if (post == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	//  /post/extractTag?docs=안녕하세요&docs=데이터분석
	// 유일한 단어 집합
	@GetMapping("/extractTag")
	public ResponseEntity<Set<String>> extractTag(String[] docs) {
		Set<String> ret = new HashSet<>();
		for (String doc : docs) {
			ret.addAll(NounExtractor.extracteNoun(doc));
		}
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/** 게시판에 원글 달기 /post/createPost */
	@PostMapping("/anonymous/createPost")
	public ResponseEntity<Integer> createPost(@RequestBody PostVO post) {
		return new ResponseEntity<>(postService.createPost(post), HttpStatus.OK);
	}

	/** 댓글 달기. parent의 hid의 연결된 hid 만들기 */
	@PostMapping("/createReply")
	public ResponseEntity<Integer> createReply(ReplyVO parent, ReplyVO reply) {
		return new ResponseEntity<>(postService.createReply(parent, reply), HttpStatus.OK);
	}
	
	/** */
	@PutMapping("/updatePost")
	public ResponseEntity<Integer> updatePost(PostVO post) {
		return new ResponseEntity<>(postService.updatePost(post), HttpStatus.OK);
	}

	/** */
	@PutMapping("/updateReply")
	public ResponseEntity<Integer> updateReply(ReplyVO reply) {
		return new ResponseEntity<>(postService.updateReply(reply), HttpStatus.OK);
	}

	/** hid like로 지우기 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Integer> deleteReply(@PathVariable String id) {
		return new ResponseEntity<>(postService.deleteReply(id), HttpStatus.OK);
	}
}
