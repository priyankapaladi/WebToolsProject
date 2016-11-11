<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>Update Book Details</title>

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

<form:form action="update.htm" method="post" commandName="books">
  <input id="bid" name="bid" type="hidden" value="${oneBook.bookID}"/>
<table>


<tr>
    <td>Name of the book:</td>
    <td><form:input path="bookName" value="${oneBook.bookName }" size="30" /> <font color="yellow"><form:errors path="bookName"/></font></td>
    
</tr>

<tr>
    <td>Author:</td>
    <td><form:input path="author" value="${oneBook.author }" size="30" /> <font color="yellow"><form:errors path="author"/></font></td>
    
</tr>
<tr>
    <td>Edition:</td>
    <td><form:input path="edition" value = "${oneBook.edition }" size="30" /> <font color="yellow"><form:errors path="edition"/></font></td>
    
</tr>

<tr>
    <td>Description:</td>
    <td><form:input path="description" value = "${oneBook.description }" size="30" /> <font color="yellow"><form:errors path="description"/></font></td>
    
</tr>

<tr>
    <td colspan="2"><input type="submit" value="Update book details" class="button"/></td>
</tr>
</table>


 </form:form>

</body>
</html>