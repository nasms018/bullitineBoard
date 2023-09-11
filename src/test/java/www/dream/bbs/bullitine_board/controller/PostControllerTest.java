package www.dream.bbs.bullitine_board.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import www.dream.bbs.board.controller.PostController;
import www.dream.bbs.board.service.PostService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostController.class)
@DisplayName("PostController 테스트")
public class PostControllerTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;
	  
	@MockBean
	private PostService postService;

	@BeforeEach
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(new PostController(postService))
				.addFilters(new CharacterEncodingFilter("UTF-8", true)) // utf-8 필터 추가
				.build();
	}

	@Test
	@DisplayName("게시글 목록 테스트")
	void listAllPost() {
		try {
			MvcResult mvcResult = mvc.perform(get("/post/listAll/000n")
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8"))
					.andExpect(status().isOk())
					.andReturn();
			MockHttpServletResponse res = mvcResult.getResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
