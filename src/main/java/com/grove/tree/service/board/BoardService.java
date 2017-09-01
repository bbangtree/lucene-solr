package com.grove.tree.service.board;

import java.util.Map;

import com.grove.tree.model.Board;
import com.grove.tree.model.File;

public interface BoardService {

	public Map<String, Object> getBoardList(Board board);
	
	public Map<String, Object> getBoard(Board board);
	
	public int putBoard(Board board, File file) throws Exception, Exception;

	public int updateBoard(Board board);
	
	public int deleteBoard(Board board) throws Exception, Exception ;
}
