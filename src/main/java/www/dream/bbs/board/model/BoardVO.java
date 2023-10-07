package www.dream.bbs.board.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import www.dream.bbs.framework.model.Entity;

@Getter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BoardVO extends Entity {
	private String name;
	private String descrip;
	private String postCnt;
	public BoardVO(String id) {
		super(id);
	}
	
	/**
	 * 사용자로 부터 정보가 만들어 질 때 사용
	 */
	public BoardVO(String name, String descrip,String postCnt) {
		this.name = name;
		this.descrip = descrip;
		this.postCnt = postCnt;
	}

	@Override
	public String toString() {
		return "BoardVO [" + super.toString() + ", name=" + name + ", descrip=" + descrip + "]";
	}
}
