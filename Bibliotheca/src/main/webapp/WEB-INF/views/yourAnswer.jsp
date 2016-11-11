<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your answer to the question:</title>
<style type="text/css">

body {
	background-repeat: repeat-x repeat-y;
	margin: 50px;
}

p{
float: right;
font-size: large;
margin-right: 50px;

}
a{
color: black;

}


</style>
</head>
<body background="<c:url value='/resources/images/answers.jpg' />">
<p><strong><a href="home.htm">Home Page</a></strong> </p>

	<c:out value="${requestScope.question.question }"></c:out>
 <form action="yourAnswer.htm" method="post" >
<h2><input type="text" name="myAnswer" value="" style="width: 700px; height: 100px;" required/></h2>
<input type="hidden" name="qid" value="<c:out value="${requestScope.question.questionID }"></c:out>"/>
<input type="submit" value="Add answer"/>


</form>



</body>
</html>