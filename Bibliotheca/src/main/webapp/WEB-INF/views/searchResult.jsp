<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SearchBooks</title>

<style>

body {
	vertical-align: middle;
	color: white;
	margin-left: 50px;
	margin-top: 50px;
	background-repeat: repeat-x;
	
}
p{
float: right;
font-size: large;
margin-right: 50px;



}
a{
color: white;

}

.link{

color: black;
}

</style>
<script type="text/javascript"></script>
</head>
<body background="<c:url value='/resources/images/books.jpg' />">

<h3>List of Books!</h3>
<c:out value="${requestScope.noSearchResult }"></c:out>
<p><strong><a href="home.htm" class="link">Home Page</a></strong> </p>
 <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <td style="color: orange;"><b>Book Name</b></td>
                <td style="color: orange;"><b>Author</b></td>
                <td style="color: orange;"><b>Update Details</b></td>
                <td style="color: orange;"><b>Delete Book</b></td>
            </tr>
       
	 <c:forEach var="bookResult" items="${requestScope.searchResultList}">
                <tr>
                    <td>${bookResult.bookName}</td>
                    <td>${bookResult.author}</td>
                    <td><a href="update.htm?bid=<c:out value="${bookResult.bookID}"/>"> Update</a></td>
                    <td><a href="delete.htm?bidDelete=<c:out value="${bookResult.bookID}"/>"> Delete</a></td>
                    
                </tr>
                
            </c:forEach>
	</table>


</body>
</html>