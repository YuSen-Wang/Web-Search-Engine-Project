<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <form action="MyServlet" method="GET">
	 Enter Key Word: <input type="text" name="url">
	 <input type="submit" value="Search Keyword">
	 </form>
	 <form action="MyServlet" method="POST">
	 <input type="submit" value="View History">
	 </form>
	 
	 <ul>
	 <c:forEach var="tempFeed" items="${feed_list}">
	     <li>${tempFeed.snippet}</li> 
 		 <li><a href="${tempFeed.url}">Read full article</a></li> 
	 </c:forEach>
	 </ul>
</body>
</html>