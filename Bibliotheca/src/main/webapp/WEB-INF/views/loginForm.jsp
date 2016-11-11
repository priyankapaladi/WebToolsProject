<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
	<title>Welcome to world of Books!</title>
	<style >
	body {
	background-repeat: no-repeat;
	background-position: top right ;
	background-size: 900px 500px;
	margin-top: 100px;
	margin-left: 70px;
		}
	</style>
	<script type="text/javascript">
	function alphabetsOnly() {
		
	}
	
	
	</script>
	
</head>
<body  background='<c:url value='/resources/images/brainGear.png' />' >
<form:form action="login.htm"  method="post" commandName="loginBean">

<table>

<tr>
    <td >User Name:</td>
    <td><form:input path="userName" size="25" /> <font color="red" style="font-size: medium;"><form:errors path="userName"/></font></td>
</tr>

<tr>
    <td>Password:</td>
    <td><form:password path="password" size="25" /> <font color="red" style="font-size: medium;"><form:errors path="password"/></font></td>
</tr>

<tr>

    <td colspan="2"><input type="submit" value="Login" style="margin-left: 79px; margin-top: 20px; width: 196px"/></td>
</tr>
</table>

<h5 style="color: red; margin-left: 140px;"><c:out value="${requestScope.invalid }"></c:out></h5>

</form:form>
</body>
</html>
