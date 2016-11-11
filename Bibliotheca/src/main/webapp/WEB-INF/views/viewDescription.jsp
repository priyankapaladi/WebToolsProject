<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>

Update</title>
</head>

<body>
<h1>Do you want to reserve this book?</h1>


<form:form action="reserve.htm" method="post" commandName="books">
  <input id="bid" name="bid" type="hidden" value="${oneBook.bookID}"/>
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
    <td>Description:</td>
   
    <td><c:out value="${oneBook.description }"/> </td>
</tr>
</table>


 </form:form>

</body>
</html>