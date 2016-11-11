<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LogOut Page</title>
        
        <SCRIPT type="text/javascript">
	window.history.forward(1);
	function noBack() { window.history.forward(); }
		</SCRIPT>
    
        <style >
        body{
        
    background-repeat: repeat-x repeat-y;
    background-size: 340px 430px;
        }
        div{
        text-align: center;
        color: white;
        }
        </style>
        
  </head>
	<body background='<c:url value='/resources/images/library.jpg' />'  onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">    
	<div>
	<h2>Enter the world of Science!</h2>
	
 	 <a class='innertab' href="login.htm" target="contents"><img src="<c:url value='/resources/images/key.jpg' />"
  alt="Unlock the mind"  width="120" height="100" border="0" />
 	 </a> 
 	 <h3>Login</h3>
 	 </div>
 	</body>
</html>