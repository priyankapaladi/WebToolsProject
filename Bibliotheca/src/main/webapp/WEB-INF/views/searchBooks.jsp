<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SearchBooks</title>

<style>
body {
	vertical-align: middle;
	color: white;
	margin-left: 50px;
	margin-top: 50px;
}

div{
background-color: white;
color: black;
width: 160px;
z-index: 999;

}
.button {
	width: 200px;
	height: 30px;
	background-color: orange;
	border: solid 1px #000;
	border-radius: 50%;
	margin-left: 50px;
	margin-top: 10px;
	font-weight: bold;
}

p {
	float: right;
	font-size: large;
	margin-right: 50px;
}

a {
	color: black;
}

tr {
	padding: 30px;
	margin: 100px;
}
</style>



<script type="text/javascript">
	//AJAX

	var xmlHttp;
	xmlHttp = GetXmlHttpObject();
	function checkBooks() {

		//	document.getElementById("keyword").onkeyup = null;
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}
		var key = document.getElementById("keyword").value;
		var query = "key=" + key;

		xmlHttp.onreadystatechange = function stateChanged() {

			if (xmlHttp.readyState == 4) {

				document.getElementById("results").innerHTML = "";
				alert(xmlHttp.responseText);
				var json = JSON.parse(xmlHttp.responseText);
				
			/* 	for (var count = 0; count < json.bookList.length; count++) {
					
					var data = document.createElement("datalist");
			        var option = document.createElement('option');
					option.value = json.bookList[count];
			        data.appendChild(option);
					document.getElementById("results").append(data);
				} */

				var x = document.createElement("TABLE");
				x.setAttribute("id", "myTable");
				document.getElementById("results").appendChild(x);
				for (var count = 0; count < json.bookList.length; count++) {

					json.bookList[count];
					var y = document.createElement("TR");
					y.setAttribute("id", "myTr" + count);
					document.getElementById("myTable").appendChild(y);

					var t = document.createTextNode(json.bookList[count]);
					y.appendChild(t);

				}
 		}
		};
		xmlHttp.open("POST", "searchForKeyWord.htm", true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(query);
		return false;
	}

	function GetXmlHttpObject() {
		var xmlHttp = null;
		try {
			// Firefox, Opera 8.0+, Safari
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			// Internet Explorer
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}

		return xmlHttp;
	}
</script>

</head>
<body background="<c:url value='/resources/images/books.jpg' />">
	<p>
		<strong><a href="home.htm">Home Page</a></strong>
	</p>

	<form action="searchBooks.htm" name="beforeSearch" method="post">

		<h3>You can search for a book by entering the Name of the book or
			the Author!</h3>

		<!-- <table>
	<tr>
	<td>Keyword: <input type="text" name="keyword" id="keyword" value="" size="30" onkeyup="return stateChanged()"/></td>
	<td></td>
	</tr>
	<tr>
	<td><input type="radio" name="searchOption" value="nameOfTheBook" checked="checked"/>     Search By Book Name</td>
	
	</tr>
	<tr>
	<td>   <input type="radio" name="searchOption" value="nameOfAuthor" />     Search By Author   </td>
	
	</tr>
	<tr>
	<td>  <input type="submit" value="Search Books" class="button" /></td>
	</tr>
	</table> -->
		<h3>Keyword:</h3>
		 <input type="text" name="keyword" id="keyword" value=""
			onkeyup="return checkBooks()" />
			
		<div id="results"></div>
		<br /> <br /> Search By Book Name <input type="radio"
			name="searchOption" value="nameOfTheBook" checked="checked" /> <br />
		<br /> Search By Author <input type="radio" name="searchOption"
			value="nameOfAuthor" /> <br /> <br /> <br /> <br /> <input
			type="submit" value="Search Books" class="button" />


	</form>

</body>
</html>