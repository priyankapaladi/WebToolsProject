<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File list</title> 
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
</head>
<body background="<c:url value='/resources/images/books.jpg' />">
<p><strong><a href="home.htm">Home Page</a></strong> </p>


 <h3>Here are the documents which might help you!</h3>

<c:out value="${requestScope.noSearchResult }"></c:out>
 <table border="1" cellpadding="2" cellspacing="2" width="700">
            <tr>
                <td style="color: orange;"><b>File Name</b></td>
                <td style="color: orange;"><b>Description</b></td>
                <td style="color: orange;"><b>File Link</b></td>
				<td style="color: orange;" ><b>Update</b></td>
                <td style="color: orange;"><b>Delete</b></td>
                                
            </tr>
       
	 <c:forEach var="documentResult" items="${requestScope.fileList}">
                <tr title="${documentResult.description}">
                    <td>${documentResult.fileName}</td>
                    <td>${documentResult.description}</td>
                    <td><a href="documentLink.htm?docid=<c:out value="${documentResult.docID}"/>" target="_blank" class="link" > Link</a></td>
                    <td><a href="updateFile.htm?docid=<c:out value="${documentResult.docID}"/>" class="link" > Update Details</a></td>
                    <td><a href="deleteFile.htm?docidDelete=<c:out value="${documentResult.docID}"/>" class="link" > Delete file</a></td>
                </tr>
                
            </c:forEach>
	</table>

</body>
</html>