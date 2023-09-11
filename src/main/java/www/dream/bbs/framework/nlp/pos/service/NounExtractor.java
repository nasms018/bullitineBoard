package www.dream.bbs.framework.nlp.pos.service;

import java.util.List;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

public class NounExtractor {
	private static Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
	
	public static List<String> extracteNoun(String document) {
		KomoranResult analyzeResultList = komoran.analyze(document);
		List<String> nounList = analyzeResultList.getNouns();
		return nounList;
	}

}
