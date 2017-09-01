<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/_taglib.jsf" %>

<html>
<head>
	<title>Board List</title>
	
	<style type="text/css">
	p>em { color: blue; font-style: inherit;}
	</style>
</head>
<body>
	<h2>board</h2>
	<form name="boardFrm">
		<input type="hidden" name="search_type" value="${fn:escapeXml(board.search_type) }">
		<input type="text" id="searchInput" name="search" value="${fn:escapeXml(board.search) }" style="margin-bottom:30%;">
		
		<!--  
		<table class="table table-condensed">
			<tr>
				<td>No.</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
			
			<c:choose>
				<c:when test="${not empty boardList }">
					<c:forEach items="${boardList }" var="item">
						<tr>
							<td>${fn:escapeXml(item.seq) }</td>
							<td><a href="#" class="goView" id="${item.seq}">${fn:escapeXml(item.title) }</a></td>
							<td>${fn:escapeXml(item.reg_seq) }</td>
							<td><fmt:formatDate value="${item.reg_dt }" /></td>			
							<td><fmt:formatNumber value="${item.read_cnt }"/>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">작성된 게시물이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		-->
		
		<c:if test="${not empty boardList }">
			<c:forEach items="${boardList }" var="item">
				<c:set var="no" value='"${item.seq }"'/>
				<h3>[<c:out value="${item.seq }" />]<a href="#" class="goView" id="${item.seq}"><c:out value="${item.title }" /></a></h3>
				<h5>writer <c:out value="${item.reg_seq }" /> </h5>
				<!-- <p>... ${item.highlight } ...</p>-->
				<c:if test="${not empty item.snippets }">
				<p>
				<c:forEach items="${item.snippets }" var="snippet">
				... ${snippet }...
				</c:forEach>
				</p>
				</c:if>
			</c:forEach>
		<p>${snippets.get("21").get("contents").get(0) }</p>
		</c:if>
	
		<!--  
		<c:if test="${not empty result1 }">
			<h2>형태소분석 : ${result1 }</h2>
			<h2>복합명사분해 : ${result2 }</h2>
			<h2>띄어쓰기 : ${result3 }</h2>
			<h2>색인어 추출 : ${result4 }</h2>
			<c:forEach items="${result5 }" var="string">
				<h2>명사추출 : ${string }</h2>
			</c:forEach>
		</c:if>
		-->
		
		<br><br>
		<a href="/front/board/boardReg.do">글쓰기</a>
		
	</form>
</body>
</html>

	
		
