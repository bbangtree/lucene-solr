package com.grove.tree.common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.lucene.analysis.ko.morph.AnalysisOutput;
import org.apache.lucene.analysis.ko.morph.CompoundEntry;
import org.apache.lucene.analysis.ko.morph.CompoundNounAnalyzer;
import org.apache.lucene.analysis.ko.morph.MorphAnalyzer;
import org.apache.lucene.analysis.ko.morph.MorphException;
import org.apache.lucene.analysis.ko.morph.WordSegmentAnalyzer;

public class ArirangAnalyzerHandler {
	
	//형태소분석
	public String morphAnalyze(String source) throws MorphException {
		MorphAnalyzer maAnal = new MorphAnalyzer();
		StringBuilder result = new StringBuilder();
		
		StringTokenizer stok = new StringTokenizer(source, " ");
		while(stok.hasMoreTokens()) {
			String token = stok.nextToken();
			List<AnalysisOutput> outList = maAnal.analyze(token);
			for(AnalysisOutput o : outList) {
				result.append(o).append(" ");
			}
		}
		return result.toString();
	}
	
	public String wordSpaceAnalyze(String source, boolean force) throws MorphException {
		WordSegmentAnalyzer wsAnal = new WordSegmentAnalyzer();
		StringBuilder result = new StringBuilder();
		
		String s;
		if(force) { 
			s = source.replace(" ", "");
		} else {
			s = source;
		}
		List<List<AnalysisOutput>> outList = wsAnal.analyze(s);
		for(List<AnalysisOutput> o : outList) {
			for(AnalysisOutput analysisOutput : o) {
				result.append(analysisOutput.getSource()).append(" ");
			}
		}
		
		return result.toString();
	}
	
	//띄어쓰기
	public String wordSpaceAnalyze(String source) throws MorphException {
		return wordSpaceAnalyze(source, false);
	}
	
	//복합명사분해
	public String compoundNounAnalyze(String source) throws MorphException {
		CompoundNounAnalyzer cnAnal = new CompoundNounAnalyzer();
		StringBuilder result = new StringBuilder();
		
		List<CompoundEntry> outList = cnAnal.analyze(source);
		for(CompoundEntry o : outList) {
			result.append(o.getWord()).append(" ");
		}
		
		return result.toString();
	}
	
	
	//색인어추출
	public String guideWord(String source) throws MorphException {
		MorphAnalyzer maAnal = new MorphAnalyzer();
		StringTokenizer stok = new StringTokenizer(source, " ");
		StringBuilder result = new StringBuilder();
		
		while(stok.hasMoreTokens()) {
			String token = stok.nextToken();
			List<AnalysisOutput> outList = maAnal.analyze(token);
			for(AnalysisOutput o : outList) {
				result.append(o.getStem());
				
				for(CompoundEntry s : o.getCNounList()) {
					result.append("+" + s.getWord());
				}
				result.append(",");
			}
		}
		String s = result.toString();
		if(s.endsWith(",")) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}
	
	//명사추출
	public ArrayList<String	> extrancNoun(String searchQuery) throws MorphException {
		ArrayList<String> nounList = new ArrayList<String>();
		
		MorphAnalyzer maAnal = new MorphAnalyzer();
		StringTokenizer stok = new StringTokenizer(searchQuery, " ");
		
		while(stok.hasMoreTokens()) {
			String token = stok.nextToken();
			
			List<AnalysisOutput> indexList = maAnal.analyze(token);
			
			for(AnalysisOutput morpheme : indexList) {
				if(morpheme.getPos() == 'N') {
					nounList.add(morpheme.getStem());
				}
			}
		}
		return nounList;
	}
	
}
