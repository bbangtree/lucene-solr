package com.grove.tree.repository.board;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.grove.tree.model.Board;
import com.grove.tree.model.File;

@Repository
public interface BoardDao {

	public int selectBoardListCount(Board board);
	
	public List<Board> selectBoardList(Board board);
	
	public int insertBoard(Board board);

	public int insertBoardFile(File file);
	
	public Board selectBoard(Board board);
	
	public List<File> selectBoardFile(Board board);
	
	public int updateBoard(Board board);
	
	public int deleteBoard(Board board);
}
