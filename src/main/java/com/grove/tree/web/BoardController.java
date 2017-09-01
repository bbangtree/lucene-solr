package com.grove.tree.web;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ko.utils.MorphUtil;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.grove.tree.common.ArirangAnalyzerHandler;
import com.grove.tree.common.SolrJDriver;
import com.grove.tree.common.SolrSearcher;
import com.grove.tree.config.utils.FileUtils;
import com.grove.tree.model.Board;
import com.grove.tree.model.File;
import com.grove.tree.service.board.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Resource(name="fileUtils")
	private FileUtils fileUtils;

	static final String INDEX_DIRECTORY = "/solr_project/workspace/index";
	
	@RequestMapping(value="/front/board/boardList.do", method = RequestMethod.GET)
	public String boardList(@ModelAttribute("board") Board board, Model model) throws Exception, Exception {
		//형태소 분석 테스트
		ArirangAnalyzerHandler aah = new ArirangAnalyzerHandler();
		String input = "올해 크리스마스에는 눈이 내리지 않고 비교적 포근할 전망이다.";
		String result1 = aah.morphAnalyze(input);
		model.addAttribute("result1", result1);
		
		String input2 = "하늘공원";
		String result2 = aah.compoundNounAnalyze(input2);
		model.addAttribute("result2" + result2);
		
		String input3 = "올 해크리스마스 에는 눈이내리지않고 비교적포근할전 망이다.";
		String result3 = aah.wordSpaceAnalyze(input3);
		model.addAttribute("result3" + result3);
		
		String input4 = "올해 크리스마스에는 눈이 내리지 않고 비교적 포근할 전망이다.";
		String result4 = aah.guideWord(input4);
		model.addAttribute("result4", result4);
		
		ArrayList<String> nuonList = aah.extrancNoun(input4);
		model.addAttribute("result5" + nuonList);
		
		//solr 검색 & 하이라이팅
		String q = board.getSearch();
		if(q == null){
			q = "*";
		} else {
			q = q + "*";
		}

	    if(!"".equals(q)) {
	        SolrQuery query = new SolrQuery();
	        query.setQuery("contents:" + q);
	        query.setParam("hl.fl", "contents");
	        query.setParam("sort", "seq desc");
	        query.setParam("rows", "30");
	        query.setHighlight(true).setHighlightSnippets(1);
	 
	        QueryResponse responseSolr = SolrJDriver.solr.query(".", query);
	        System.out.println("========query========");
	        System.out.println(query);
	        SolrDocumentList results = responseSolr.getResults();
	        Map<String, Map<String, List<String>>> snippets = responseSolr.getHighlighting();
	        
	        List<Board> list = new ArrayList<Board>();
	        for(int i=0; i<results.toArray().length; i++) {
	        	SolrDocument data = results.get(i);
	        	
	        	Board item = new Board();
	        	item.setSeq(NumberUtils.toInt(String.valueOf(data.get("seq"))));
	        	item.setTitle(String.valueOf(data.get("title")));
	        	item.setContents(String.valueOf(data.get("contents")));
	        	item.setReg_seq(String.valueOf(data.get("reg_seq")));
	        	
	        	// highlight text  
	        	Map<String, List<String>> snippet = snippets.get(String.valueOf(data.get("seq")));
	        	//snippet get 해당 filed
	        	item.setHighlight(snippet.get("contents").toString());
	        	item.setSnippets(snippet.get("contents"));
	        	
	        	list.add(item);
	        }
	        
	        model.addAttribute("q", q);
	        model.addAttribute("boardList", list);
//	        model.addAttribute("boardList", results.toArray());
	    }

//		model.addAllAttributes(boardService.getBoardList(board));
		return "front/board/boardList";
	}
	
	@RequestMapping(value = "/front/board/boardReg.do", method = RequestMethod.GET)
	public String boardReg(@ModelAttribute("board") Board board) {
		
		return "front/board/boardReg";
	}
	
	
	@RequestMapping(value = "/front/board/boardSave.do", method = RequestMethod.POST)
	public String boardSave(@ModelAttribute("board") Board board, Model model, HttpServletRequest request) throws Exception {
		ArirangAnalyzerHandler aah = new ArirangAnalyzerHandler();
		Directory index = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
		File file = new File();
		int affectedRows = 0;
		switch (board.getEdit()) {
		case 1:
			List<File> list = fileUtils.parseInsertFileInfo(request);
			if(list.size() != 0) {
				file = list.get(0);
				System.out.println("------------- file start -------------");
				System.out.println("name : "+file.getStored_file_nm());
				System.out.println("filename : "+file.getOriginal_file_nm());
				System.out.println("size : "+ file.getFile_size());
				System.out.println("path : " + file.getFile_path());
				System.out.println("-------------- file end --------------\n");
			}
			
			affectedRows = boardService.putBoard(board, file);
			break;
		case 2:
			board.setMod_seq("bbangtree");
			affectedRows = boardService.updateBoard(board);
			break;
		case 3:
			board.setMod_seq("bbangtree");
			affectedRows = boardService.deleteBoard(board);
			break;
		}
		
		if(affectedRows > 0 && board.getEdit() != 3) {
			MorphUtil mUtil = new MorphUtil(); 
			String chosungs = "";
			char[] title = board.getTitle().toCharArray();
			
			for(int j = 0; j < title.length; j++) {
				char[] decompose = mUtil.decompose(title[j]);
				char chosung = decompose[0];
				chosungs = chosungs + chosung;
			}
			
			Document doc = new Document();
			Analyzer analyzer = new StandardAnalyzer();
		    IndexWriterConfig config = new IndexWriterConfig(analyzer);
		    
		    //Lucene 색인
		    IndexWriter iwriter = new IndexWriter(index, config);
		    String seq = Integer.toString(board.getSeq());
		    doc.add(new Field("seq", seq, TextField.TYPE_STORED));
		    doc.add(new Field("title", board.getTitle(), TextField.TYPE_STORED));
		    doc.add(new Field("contents", board.getContents(), TextField.TYPE_STORED));
		    doc.add(new Field("chosung", chosungs, TextField.TYPE_STORED));
		    doc.add(new Field("index", aah.guideWord(board.getContents()), TextField.TYPE_STORED));
		    iwriter.addDocument(doc);
		    iwriter.close();
		} 
		return "front/index";
	}
	
	@RequestMapping(value = "/front/board/board.do", method = RequestMethod.GET)
	public String board(@ModelAttribute("board") Board board, Model model) {
		model.addAllAttributes(boardService.getBoard(board));
		return "front/board/boardReg";
	}
	
	
	@RequestMapping(value = "/front/board/boardTest.do", method = RequestMethod.GET)
	public String boardTest(@ModelAttribute("board") Board board, Model model) throws Exception {
		Directory index = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
		ArirangAnalyzerHandler aah = new ArirangAnalyzerHandler();
		MorphUtil mUtil = new MorphUtil();
		String chosungs = "";
		List<Board> boardList = new ArrayList<>();
		
		if(board.getSearch() != null) {
			char[] search = board.getSearch().toCharArray();
			for(int j = 0; j < search.length; j++) {
				char[] decompose = mUtil.decompose(search[j]);
				char chosung = decompose[0];
				chosungs = chosungs + chosung;
			}
			System.out.println("chosungs : " + chosungs);
			
			DirectoryReader ireader = DirectoryReader.open(index);
			//색인디렉토리에 연결 검색
			IndexSearcher isearch = new IndexSearcher(ireader);
			//텍스트 분석기
			Analyzer analyzer = new StandardAnalyzer();
			
			//검색 쿼리
			if(board.getSearch() != null) {
				Term t = new Term("chosung", chosungs);
				Query q = new TermQuery(t);
				q = new PrefixQuery(t);
				TopDocs docs = isearch.search(q, 10);
				ScoreDoc[] hits = docs.scoreDocs;
				System.out.println("hits length : " + hits.length);
				
				for(int i= 0; i < hits.length; i++) {
					Document hitDoc = isearch.doc(hits[i].doc);
					System.out.println("--------------------------------");
					System.out.println(hitDoc.get("id"));
					System.out.println(hitDoc.get("title"));
					System.out.println(hitDoc.get("contents"));
					System.out.println(hitDoc.get("chosung"));
					Board bd = new Board();
					bd.setSeq(Integer.parseInt(hitDoc.get("seq")));
					bd.setTitle(hitDoc.get("title"));
					bd.setContents(hitDoc.get("contents"));
					bd.setChosung(hitDoc.get("chosung"));
					bd.setIndex(hitDoc.get("index"));
					boardList.add(bd);
				}
			}
			ireader.close();
		}
		
		model.addAttribute("list", boardList);
		model.addAttribute("board", board);
		
//		directory.close();
		return "front/board/boardTest";
	}
	
	@RequestMapping(value = "front/board/auto.do", method = RequestMethod.GET)
	public void auto(HttpServletResponse response, HttpServletRequest request) throws IOException {
		String query = request.getParameter("query");
		SolrSearcher searcher = new SolrSearcher();
		JSONArray json = searcher.auto(query);
		JSONArray jArr = new JSONArray();
		
		for(int i = 0; i<json.size(); i++) {
			JSONObject jObj = new JSONObject();
			jObj.put("name", json.get(i));
			jObj.put("value", json.get(i));
			jArr.add(jObj);
		}
		
		response.setContentType("application/json ; charset=UTF-8");
		response.getWriter().write(jArr.toString());
	}
	
	
}
