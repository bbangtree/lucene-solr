package com.grove.tree.config.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.grove.tree.consts.Consts;

@Component("fileUtils")
public class FileUtils {

	public List<com.grove.tree.model.File> parseInsertFileInfo(HttpServletRequest request) throws Exception, Exception {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileNm = null;
		String originalFileExtesion = null;
		String storedFileNm = null;
		String uploadFullPath = Consts.WEB_PATH;
		String serverFilePath = "/upload/file/";
		
		List<com.grove.tree.model.File> list = new ArrayList<com.grove.tree.model.File>();
		Map<String, Object> listMap = null;
		
		File file = new File(Consts.WEB_PATH);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				originalFileNm = multipartFile.getOriginalFilename();
				originalFileExtesion = originalFileNm.substring(originalFileNm.lastIndexOf("."));
				storedFileNm = CmUtils.getRandomString() + originalFileExtesion;
				
				file = new File(uploadFullPath+ serverFilePath + storedFileNm);
				multipartFile.transferTo(file);

				com.grove.tree.model.File board_file = new com.grove.tree.model.File();
				board_file.setOriginal_file_nm(originalFileNm);
				board_file.setStored_file_nm(storedFileNm);
				board_file.setFile_size((int)multipartFile.getSize());
//				board_file.setFile_path(file.getPath());
				board_file.setFile_path(serverFilePath + storedFileNm);
				list.add(board_file);
			}
		}
		return list;
	}
}
