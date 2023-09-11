package www.dream.bbs.bullitine_board.mapper;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import www.dream.bbs.board.mapper.PostMapper;
import www.dream.bbs.board.model.PostVO;
import www.dream.bbs.board.model.ReplyVO;

@ExtendWith(SpringExtension.class)
@MybatisTest
//실 데이터베이스에 연결 시 필요한 어노테이션
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostMapperTest {
    @Autowired
    private PostMapper mapper;
    
    //@Test
    @DisplayName("listAllPost Test")
    public void listAllPost() {
    	try {
    		List<PostVO> l = mapper.listAllPost("000n");
    		
    		for (PostVO p : l) {
    			System.out.println(p);
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @Test
    @DisplayName("FindPostById Test")
    public void testFindPostById() {
    	try {
    		List<ReplyVO> l = mapper.findById("p001");
    		
    		for (ReplyVO p : l) {
    			System.out.println(p);
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

}
