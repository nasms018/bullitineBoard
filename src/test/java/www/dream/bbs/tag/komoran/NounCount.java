package www.dream.bbs.tag.komoran;

import java.util.List;

import www.dream.bbs.framework.nlp.pos.service.NounExtractor;

public class NounCount {

	public static void main(String[] args) {
		String doc = "우리집에는 강아지 네오가 있습니다. 네오는 밝은 성격입니다.";
		List<String> nlist = NounExtractor.extracteNoun(doc);
		for (String n : nlist)
			System.out.println(n);
	}

}
