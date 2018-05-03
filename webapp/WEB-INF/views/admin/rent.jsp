<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>lms</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/admin/rent.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/admin/include/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>타이틀</th>
						<th>카테고리</th>
						<th>대여일</th>
						<th>반납일</th>
					</tr>	
					<c:forEach items="${map.page.content }" var="rent" varStatus="status">
						<tr>
							<td>[${map.page.totalElements - ((map.page.number) * map.itemLimit + status.index) }]</td>
							<td>${rent.item.title }</td>
							<td>${rent.item.category.name }</td>
							<td>${rent.leaseDate }</td>
							<td>${rent.returnDate }</td>
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
										<li><a href="${pageContext.servletContext.contextPath }/admin/rent/${i }">${i }</a></li>			
									</c:otherwise>
								</c:choose>
							</c:forEach>
						<c:choose>
							<c:when test="${map.endPage lt map.page.totalPages}">
								<li><a href="${pageContext.servletContext.contextPath }/admin/rent/${map.startPage + 5 }">▶</a></li>
							</c:when>
						</c:choose>
					</ul>
				</div>
			</div>
			<c:import url="/WEB-INF/views/admin/include/navigation.jsp">
				<c:param name="menu" value="rent"/>
			</c:import>
		</div>
	</div>
</body>
</html>