<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

<title>Administrator</title>
     
<style>
ul#tab {
padding: 0;
}

ul#tab li {
	display: inline;
}

ul#tab li a {
	background-color: blue;
	color: white;
	padding: 10px 15px;
	text-decoration: none;
	border-radius: 15px 15px 0 0 ;
	text-align: center;
}

ul#tab li a:HOVER {
	background-color: orange;
}

p {
	color: white;
	text-align: center;
	font-style: oblique;
	font-size: medium;

}

body {
background-repeat: repeat-x;


}
</style>


</head>
<body background='<c:url value='/resources/images/bookImage.jpg' />'>

<p>Hi <c:out value="${sessionScope.name }" ></c:out>, welcome to Library Collection Management </p>

<ul id="tab">
  <li><a href="searchBooks.htm"  target="contents">Search Books</a></li>
  <li><a href="searchDocuments.htm"  target="contents">Search Documents</a></li>
  <li><a href="searchRequests.htm"  target="contents">Search Requests</a></li>
  <li> <a href="logOut.htm" target="contents">LogOut</a></li>
</ul>



</body>
</html>



