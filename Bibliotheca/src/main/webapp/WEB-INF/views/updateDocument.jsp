<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload</title>
<style >

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

.des {

height: 200px;
width: 500px;
}




</style>
</head>
<body background="<c:url value='/resources/images/books.jpg' />">
<p><strong><a href="home.htm">Home Page</a></strong> </p>

 
        <form method="post" action="updateFile.htm" enctype="multipart/form-data" >
                <h3>Update the details of the files</h3>
        
        <input type="hidden" name="docid" value="${oneDocument.docID }"/>
            <table border="0">
                <tr>
                    <td>Name of the file:</td>
                    <td><input type="text" name="fileName" size="50" value="${oneDocument.fileName }" required/></td>
                    
                    
                </tr>
                <tr>
                    <td>Description:</td>
                    <td><input type="text" name="description" class="des" value="${oneDocument.description }" required/></td>
                        
                    
                </tr>
                <tr>
                    <td>File:</td>
                    <td><input type="file" name="fileData" size="50" required	/></td>
                    
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Upload New file" class="button" /></td>
                </tr>
            </table>
        </form>
    
</body>
</html>