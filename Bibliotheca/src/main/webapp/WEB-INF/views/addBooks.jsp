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
	color: white;
	margin-left: 50px;
	margin-top: 50px;
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
</style>
</head>

<body background="<c:url value='/resources/images/books.jpg' />">
<p><strong><a href="home.htm">Home Page</a></strong> </p>
<form:form action="addBooks.htm" method="post" commandName="books">
 
 <h3>You can add a book by entering the details!</h3>
 
<table >

<tr >
    <td>Name of the book:</td>
    <td><form:input path="bookName" size="60" /> <font color="yellow"><form:errors path="bookName"/></font></td>
</tr>

<tr>
    <td>Author:</td>
    <td><form:input path="author" size="60" /> <font color="yellow"><form:errors path="author"/></font></td>
</tr>
<tr>
    <td>Edition:</td>
    <td><form:input path="edition" size="60" /> <font color="yellow"><form:errors path="edition"/></font></td>
</tr>

<tr>
    <td>Description:</td>
    <td><form:input path="description" size="60"/> <font color="yellow"><form:errors path="description"/></font></td>
</tr>

<tr align="center">
    <td colspan="2"><input type="submit" value="Add books" class="button"/></td>
</tr>
</table>


 </form:form>

</body>
</html>