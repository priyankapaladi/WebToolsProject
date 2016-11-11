<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    


<html>
<head>
<title>Forum</title>

<style type="text/css">
body{
background-size: 1000px 500px;

}
p{
float: right;
font-size: large;
margin-right: 50px;



}
a{
color: blue;

}

.link{
color: white;


}


</style>
</head>

<body background="<c:url value='/resources/images/forum.jpg' />">
<h3>Welcome to student Forum!           <a href="home.htm">Home Page</a></h3>

<p><strong><a href="postQuestion.htm">Post a question!</a></strong></p>
<table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <td><b>Question</b></td>
                <td><b>Answers</b></td>
                <td><b>Add your answer</b></td>
            </tr>
       
	 <c:forEach var="questionList" items="${requestScope.questions}">
                <tr>
                    <td>${questionList.question}</td>
                    
                    
                   	<td><a href="answersToQuestion.htm?quesid=<c:out value="${questionList.questionID}"/>"> Check for answers</a></td>
                    <td><a href="yourAnswer.htm?questionid=<c:out value="${questionList.questionID}"/>"> Add your answer</a></td>
                    
                </tr>
                
            </c:forEach>
	</table>



</body>
</html>