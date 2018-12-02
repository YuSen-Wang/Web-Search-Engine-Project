<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*, servlets.*" %>

<!DOCTYPE html>
<html>

<head>
  <title>Keyword Tracker App</title>
  
  <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
 <%
     List<keyword> theKeywords = 
         (List<keyword>) request.getAttribute("STUDENT_LIST");
   %>

<body>
  <p>
  	<a href="search.jsp">Back to Search</a>
  </p> 
  <div id="wrapper">
     <div id="header">
     	<h2>My records</h2>
   
     </div>
  
  	 <div id="container">
  	 	
  	 	<div id="content">
  	 		<table>
  	 		   
  	 		   <tr>
  	 		   		<th>Key Word</th>
  	 		   </tr>
  	 		
  	 		   <% for(keyword tempKeyword : theKeywords) {%>
  	 		   <tr>
  	 		      <td> <%= tempKeyword.getKeyWord() %>
  	 		   </tr>
  	 		   
  	 		   <%} %>
  	 		</table>
  	 	
  	 	</div>
  	 
  	 </div>
  
  </div>
  

  
</body>
</html>