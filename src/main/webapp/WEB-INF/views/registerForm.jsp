<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Spittr</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<h1>Register</h1>
	<form method="POST">
		First Name: <input type="text" name="firstName" /><br /> 
		Last Name:	<input type="text" name="lastName" /><br /> 
		Username: <input type="text" name="username" /><br /> 
		Password: <input type="password" name="password" /><br /> 
		Listing 5.14 Testing a
		form-displaying controller method Listing 5.15 JSP to render a
		registration form Handle GET requests for /spitter/register Set up
		MockMvc Assert registerForm view156 CHAPTER 5 Building Spring web
		applications 
		<input type="submit" value="Register" />
	</form>
</body>
</html>