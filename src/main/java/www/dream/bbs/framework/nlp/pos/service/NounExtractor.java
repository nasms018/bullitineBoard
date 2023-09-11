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
		//nounList.addAll(analyzeResultList.getMorphesByTags("SL"));
		return nounList;
	}

	/*
	public static void main(String[] args) {
		String data = "Don't be fooled by the dark sounding name, Mr. Jone's Orphanage is as cheery as cheery goes for a pastry shop";
				List<String > list =extracteNoun(data);
				for (String v : list) {
					System.out.println(v);
				}
	}*/
}

