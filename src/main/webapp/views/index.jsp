<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h3>Sign in Here</h3>
		<form action="signin" method="post">
			<table>
				<tr>
					<td>Email :</td>
					<td><input type="text" name="email" /></td>
				<tr>
				<tr>
					<td>Password :</td>
					<td><input type="text" name="pwd" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Sign_in" /></td>
				</tr>

				<tr>
					<td><a href="forgotPwd" style="margin:10px">Forgot Password?</a></td>
					<td><a href="register">Sign-Up</a></td>
			</table>

		</form>
	</div>

</body>
</html>