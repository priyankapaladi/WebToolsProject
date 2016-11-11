<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>The answers to the question:</title>
</head>
<body>

	<c:out value="${requestScope.noSearchResult }"></c:out>
	<c:out value="${requestScope.question.question }"></c:out>
	<a href="home.htm">Home</a>
 <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <td><b>Answers</b></td>
                
            </tr>
       
	 <c:forEach var="answers" items="${requestScope.answerList}">
                <tr>
                    <td>${answers.answer}</td>
                    
                </tr>
                
            </c:forEach>
	</table>





</body>
</html>