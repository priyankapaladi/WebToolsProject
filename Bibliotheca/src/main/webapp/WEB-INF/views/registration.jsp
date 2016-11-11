<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
    <title>Add User Form</title>
    <style >
    body{
    
    margin-left: 50px;
    
    background-repeat: no-repeat;
    }
    div {
	
	color: red;
}
    
    </style>
    <script type="text/javascript">
    

    var xmlHttp;
    xmlHttp = GetXmlHttpObject();
    function checkUser() {
       if (xmlHttp == null)
        {
            alert("Your browser does not support AJAX!");
            return;
        }
        var username = document.getElementById("userName").value;
        var query = "username="+username;

        xmlHttp.onreadystatechange = function stateChanged()
        {
            if (xmlHttp.readyState == 4)
            {
				document.getElementById("results").innerHTML =  xmlHttp.responseText;

               
            }
        };
        xmlHttp.open("POST", "searchForUsername.htm", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send(query);
       return false;
    }
    
    function checkNuid() {
        if (xmlHttp == null)
         {
             alert("Your browser does not support AJAX!");
             return;
         }
         var nuid = document.getElementById("nuID").value;
         var query = "nuid="+nuid;

         xmlHttp.onreadystatechange = function stateChanged()
         {
             if (xmlHttp.readyState == 4)
             {
 				document.getElementById("results").innerHTML =  xmlHttp.responseText;

                
             }
         };
         xmlHttp.open("POST", "searchForNuid.htm", true);
         xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
         xmlHttp.send(query);
        return false;
     }

    
     function GetXmlHttpObject()
    {
        var xmlHttp = null;
        try
        {
            // Firefox, Opera 8.0+, Safari
            xmlHttp = new XMLHttpRequest();
        } catch (e)
        {
            // Internet Explorer
            try
            {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e)
            {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        }
        return xmlHttp;
    }

	

    
    </script>
</head>
<body background="<c:url value='/resources/images/bookGlasses.jpg' />">

<p style="text-align:right; font-size:30; margin-right: 70px"><strong><a href="login.htm" style="color: red; ">Login</a></strong> </p>
<h2>Register a New User</h2>

<div id="results"></div>

<form:form action="register.htm" commandName="loginBean" method="post">
	   <div id="results"></div>
	   
	   
	   
<table>


<tr>
    <td>NUID:</td>
    <td><form:input path="nuID" size="30" onkeyup="return checkNuid()" pattern="[0-9]{9}"/> <font color="red"><form:errors path="nuID"/></font></td>
</tr>

<tr>
    <td>User Name:</td>
    <td><form:input path="userName" size="30" onkeyup="return checkUser()" pattern="[a-zA-Z]{1,10}"/> <font color="red"><form:errors path="userName" /></font></td>
</tr>

<tr>
    <td>Password:</td>
    <td><form:password path="password" size="30" pattern="[a-zA-Z]{1,10}"	/> <font color="red"><form:errors path="password"/></font></td>
</tr>

 
<tr>
    <td colspan="2"><input type="submit" value="Register" /></td>
</tr>
</table>

</form:form>

</body>
</html>