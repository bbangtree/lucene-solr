<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/_taglib.jsf" %><html>
<html>
<head>
	<title>글을위한 글</title>
</head>
<body>
	<h2>board</h2>
	<form name="boardRegFrm" action="/front/board/boardSave.do" enctype="multipart/form-data" method="post">
		<input type="hidden" id="seq" name="seq" value="${board.seq }">
		<input type="hidden" id="edit" name="edit" value="${board.seq eq 0 ? 1 : 2 }">
		<table class="table table-condensed">
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" id="title" value="${board.title }" /></td>
			</tr>
			<tr>	
				<td>내용</td>
				<td><textarea name="contents" id="contents" rows="10" cols="80">${board.contents }</textarea></td>
			</tr>
			<tr>	
				<td>url</td>
				<td><input type="text" name="url" id="url" value="${board.url }" /></td>
			</tr>
			<tr>	
				<td>작성자</td>
				<td><input type="text" name="reg_seq" id="reg_seq" value="${board.reg_seq }" /></td>
			</tr>
			
		</table>
		
		<input type="file" name="file">
		
		<c:if test="${not empty boardFile }">
			<c:forEach items="${boardFile }" var="item">
				<div>
				<img src="${item.file_path }">
				<video src="${item.file_path }" controls="controls"></video>
				</div>
			</c:forEach>
		</c:if>
		
		<br>
		<a href="#" id="btnSubmit" class="btn">저장</a>
		<a href="#" id="btnDelete" class="btn" style="display : ${board.seq eq 0 ? 'none' : 'inline'}">삭제</a>		
		<a href="/front/board/boardList.do" class="btn">돌아가기</a>
		
	</form>
</body>
</html>