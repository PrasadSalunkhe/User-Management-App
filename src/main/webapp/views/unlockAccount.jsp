<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


</head>
<body>
	<div align="center">
		<h3>Unlock Account Here</h3>

		<font color='green'>${succMsg}</font> <font color='red'>${failMsg}</font>

<%-- @GetMapping("/unloackAccount") --%>
		<form:form action="unloackAccount" method="post"
			modelAttribute="userAcc">
			<table>
				<tr>
					<td>Email :</td>
					<td>${userAcc.email}</td>
				</tr>
				<tr>
					<td>Temporary Password :</td>
					<td><form:password path="tempPazzword" /></td>
				</tr>
				<tr>
					<td>Choose New Password :</td>
					<td><form:password path="pazzword" /></td>
				</tr>
				<tr>
					<td>Confirm Password :</td>
					<td><form:password path="confirmPazzword" /></td>
					
				</tr>
				
				<tr>
					<td><input type="reset" value="Reset" /></td>
					<td><input type="submit" value="Submit" id="submitBtn" /></td>
				</tr>
				

			</table>

		</form:form>


	</div>

</body>
</html>