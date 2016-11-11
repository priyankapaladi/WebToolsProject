<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>

Add Books
</title>
<style>
body {
	vertical-align: middle;
	color: black;
	margin-left: 50px;
	margin-top: 50px;
	background-repeat: repeat-x repeat-y; 
}

.button {
    width: 300px;
    height: 30px;
    background-color: orange;
    border: solid 1px #000;
    border-radius: 50%;
    margin-left: 110px;
     
  font-weight: bold;
   
}

p{
float: right;
font-size: large;
margin-right: 50px;



}
a{
color: black;

}

.des{
height: 100px;
width: 700px;

}
</style>
</head>

<body background="<c:url value='/resources/images/answers.jpg' />">
<p><strong><a href="home.htm">Home Page</a></strong> </p>
<form:form action="postQuestion.htm" method="post" commandName="question">
 
 <h3>Please post your question here!</h3>
 
<table >

<tr >
    <td>Question:</td>
    <td><form:input path="question"  class="des" /> <font color="red"><form:errors path="question"/></font></td>
</tr>

<tr align="center">
    <td colspan="2"><input type="submit" value="Post Question" class="button"/></td>
</tr>
</table>


 </form:form>

</body>
</html>