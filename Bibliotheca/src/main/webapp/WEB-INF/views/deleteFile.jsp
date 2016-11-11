<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>Update</title>
<style type="text/css">

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

<body background="<c:url value='/resources/images/books.jpg' />" style="color: white; margin: 50px;">

<form:form action="deleteFile.htm" method="post" commandName="document">
<p><strong><a href="home.htm">Home Page</a></strong> </p>
  <input id="docid" name="docid" type="hidden" value="${oneDocument.docID}"/>
  <h3>Are you sure you want to delete this book from the collection?</h3>
  
<table >

<tr>
    <td>Name of the file:</td>
   
    <td style="color: yellow;"><c:out value="${oneDocument.fileName }"/> </td>
</tr>

<tr>
    <td>Description:</td>
   
    <td style="color: yellow;"><c:out value="${oneDocument.description }"/> </td>
</tr>

<tr>
    <td colspan="2"><input type="submit" value="Delete file" /></td>
</tr>
</table>


 </form:form>

</body>
</html>