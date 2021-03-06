<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>LMS</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }" method="get">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>타이틀</th>
						<th>카테고리</th>
						<th>&nbsp;</th>
					</tr>
					<c:forEach items="${map.page.content }" var="item" varStatus="status">
						<tr>
							<td>[${map.page.totalElements - ((map.page.number) * map.itemLimit + status.index) }]</td>
							<td>${item.title }</td>
							<td>${item.category.name }</td>
							<td>
								<c:choose>
									<c:when test="${item.rented eq false }">
										<a href="${pageContext.servletContext.contextPath }/rent?no=${item.no }" class="btn">대여</a>
									</c:when>
									<c:when test="${item.rented eq true }">
										<a href="${pageContext.servletContext.contextPath }/rent?no=${item.no }" class="btn">예약</a>
									</c:when>
								</c:choose>
							</td>
						</tr>	
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<c:choose>
							<c:when test="${map.startPage gt 1}">
								<li><a href="${pageContext.servletContext.contextPath }/">◀</a></li>
							</c:when>
						</c:choose>
							<c:forEach var="i" begin="${map.startPage }" end="${map.startPage + map.pageLimit - 1 }" step="1">
								<c:choose>
									<c:when test="${ i eq map.page.number + 1 }">
										<li class="selected">${i }</li>			
									</c:when>
									<c:when test="${i gt map.page.totalPages }">
											<li>${i }</li>
									</c:when>
									<c:otherwise>
										<li><a href="${pageContext.servletContext.contextPath }/${i }">${i }</a></li>			
									</c:otherwise>
								</c:choose>
							</c:forEach>
						<c:choose>
							<c:when test="${map.endPage lt map.page.totalPages}">
								<li><a href="${pageContext.servletContext.contextPath }/${map.startPage + 5 }">▶</a></li>
							</c:when>
						</c:choose>
					</ul>
				</div>				
				<div class="bottom">
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>