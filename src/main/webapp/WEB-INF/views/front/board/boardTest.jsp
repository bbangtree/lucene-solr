<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/_taglib.jsf" %>

<html>
	<head>
		<title>Lucene Search</title>
	</head>
	<body>
		<form name="boardTestFrm">
			<input type="text" name="search" value="${board.search }">
		</form>
	
	
		<c:forEach items="${list }" var="item">
			[seq]${item.seq}<br> 
			[title]${item.title }<br>
			[contents]${item.contents }<br>
			[chosung]${item.chosung }<br>
			[index]${item.index }<br>
			------------------------------<br>
		</c:forEach>
	</body>
</html>