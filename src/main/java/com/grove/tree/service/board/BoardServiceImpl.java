package com.grove.tree.service.board;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grove.tree.common.SolrJDriver;
import com.grove.tree.config.utils.CmUtils;
import com.grove.tree.config.utils.MakeQrCode;
import com.grove.tree.config.vo.Page;
import com.grove.tree.consts.Consts;
import com.grove.tree.model.Board;
import com.grove.tree.model.File;
import com.grove.tree.repository.board.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;

	static final String INDEX_DIRECTORY = "/solr_project/workspace/index";
	
	public Map<String, Object> getBoardList(Board board) {
		Map<String, Object> result = new HashMap<>();
		int rows = board.getRows();
		int page = board.getPage();
		int totalCount = boardDao.selectBoardListCount(board);
		
		Page paging = new Page();
		paging = CmUtils.paging(totalCount, rows, page);
		board.setPage(paging.getPage());
		board.setRows(paging.getRows());
		result.put("boardList", boardDao.selectBoardList(board));
		result.put("paging", paging);
		result.put("board", board);
		return result; 
	}
	
	public int putBoard(Board board, File file) throws Exception, Exception {
		int affectedRows = 0;
		affectedRows += boardDao.insertBoard(board);
		if(affectedRows > 0	) {
			if(file.getOriginal_file_nm() != null) {
				file.setBoard_seq(board.getSeq());
				file.setReg_seq(board.getReg_seq());
				affectedRows += boardDao.insertBoardFile(file);
				System.out.println("==============FILE UPLOAD SUCCESS=============");
			}
			
			String file_nm = String.valueOf(board.getSeq());
			if(!"".equals(board.getUrl())) {
				File qrFile = new File();
				String uploadFullPath = Consts.WEB_PATH;
				String serverFilePath = "/upload/qr/";
				MakeQrCode.makeQRCode(board.getUrl(), 100, 100, uploadFullPath + serverFilePath , file_nm);
				qrFile.setBoard_seq(board.getSeq());
				qrFile.setOriginal_file_nm(file_nm);
				qrFile.setStored_file_nm(file_nm);
				qrFile.setReg_seq(board.getReg_seq());
				qrFile.setFile_path(serverFilePath+file_nm+".png");
				affectedRows += boardDao.insertBoardFile(qrFile);
			}
		}

		SolrInputDocument solrDoc = new SolrInputDocument();
		solrDoc.addField("seq", board.getSeq());
		solrDoc.addField("title", board.getTitle());
		solrDoc.addField("contents", board.getContents());
		solrDoc.addField("reg_dt", board.getReg_dt());
		solrDoc.addField("reg_seq", board.getReg_seq());
		solrDoc.addField("url", board.getUrl());
		
		Collection<SolrInputDocument> solrDocs = new ArrayList<SolrInputDocument>();
		solrDocs.add(solrDoc);
		
		SolrJDriver.solr.add(solrDocs);
		SolrJDriver.solr.commit();
		
		return affectedRows;
	}

	public Map<String, Object> getBoard(Board board) {
		Map<String, Object> result = new HashMap<>();
		board = boardDao.selectBoard(board);
		board.setBoard_seq(board.getSeq());
		result.put("board", board);
		result.put("boardFile", boardDao.selectBoardFile(board));
		return result;
	}
	
	
	public int updateBoard(Board board) {
		int affectedRows = 0;
		affectedRows = boardDao.updateBoard(board);
		return affectedRows;
	}
	
	public int deleteBoard(Board board) throws Exception, Exception {
		int affectedRows = 0;
		affectedRows = boardDao.deleteBoard(board);
		if(affectedRows > 0) {
			//Solr 색인 삭제
			SolrJDriver.solr.deleteById(String.valueOf(board.getSeq()));
			SolrJDriver.solr.commit();
			//Lucene 색인 삭제
			Directory index = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			IndexWriter iwriter = new IndexWriter(index, config);
			iwriter.deleteDocuments(new Term("seq", String.valueOf(board.getSeq())));
			iwriter.commit();
		}
		return affectedRows;
	}
	
	
}
