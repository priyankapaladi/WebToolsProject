<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File list</title>
</head>
<style>

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

<body background="<c:url value='/resources/images/books.jpg' />">
<p><strong><a href="home.htm">Home Page</a></strong> </p>
 <h3>Here are the documents which might help you!</h3>

<c:out value="${requestScope.noSearchResult }"></c:out>

 <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <td><b>File Name</b></td>
                <td><b>Description</b></td>
                <td><b>File Link</b>
            </tr>
       
	 <c:forEach var="documentResult" items="${requestScope.fileList}">
                <tr title="${documentResult.description}">
                    <td>${documentResult.fileName}</td>
                    <td>${documentResult.description}</td>
                    <td><a href="documentLink.htm?docid=<c:out value="${documentResult.docID}"/>" target="_blank"> Link</a></td>
            </tr>
                
            </c:forEach>
	</table>

</body>
</html>