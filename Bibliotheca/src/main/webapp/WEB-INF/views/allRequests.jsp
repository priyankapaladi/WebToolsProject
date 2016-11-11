<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SearchBooks</title>
<style type="text/css">
body {
	vertical-align: middle;
	color: white;
	margin-left: 50px;
	margin-top: 50px;
}
p{
float: right;
font-size: large;
margin-right: 50px;



}
a{
color: black;

}

.link{
color: white;


}



</style>
</head>
<body background="<c:url value='/resources/images/books.jpg' />">
<p><strong><a href="home.htm">Home Page</a></strong> </p>


 <h3>Here are the requests!</h3>

	<c:out value="${requestScope.noSearchResult }"></c:out>
 <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <td><b>Requested By </b></td>
                <td><b>Book ID</b></td>
                <td><b>Request ID</b>
            </tr>
       
	 <c:forEach var="bookRequest" items="${requestScope.bookRequest}">
                <tr>
                    <td>${bookRequest.requestBy}</td>
                    <td>${bookRequest.bookIDRequested.bookID}</td>
                    <td><a href="deleteRequest.htm?requestid=<c:out value="${bookRequest.rid}"/>"> Book returned</a></td>
                  
                    
                </tr>
                
            </c:forEach>
	</table>




</body>
</html>