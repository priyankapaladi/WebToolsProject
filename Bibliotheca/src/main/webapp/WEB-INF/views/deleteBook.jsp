<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>Delete Book</title>

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

<form:form action="delete.htm" method="post" commandName="books">
  <input id="bid" name="bid" type="hidden" value="${oneBook.bookID}"/>
  <h3>Are you sure you want to delete this book from the collection?</h3>
  
<table>

<tr>
    <td>Name of the book:</td>
   
    <td><c:out value="${oneBook.bookName }"/> </td>
</tr>

<tr>
    <td>Author:</td>
   
    <td><c:out value="${oneBook.author }"/> </td>
</tr>
<tr>
    <td>Edition:</td>
  
    <td><c:out value="${oneBook.edition }"/> </td>
</tr>

<tr>
    <td colspan="2"><input type="submit" value="Delete book" class="button"/></td>
</tr>
</table>


 </form:form>

</body>
</html>