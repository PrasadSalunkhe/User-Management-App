<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#userEmail").blur(function() {
			 $("#errMsg").text("");
			$.ajax({
			    type : "GET",
			    url : "uniqueMailCheck?email=" + $("#userEmail").val(),
			    success: function(data){
			    //response from controller
			    if(data=="DUPLICATE"){
			    	 $("#errMsg").text("This email is already taken...!");
			    	//we have to desable submit btn when there is duplicate email
			    	 $("#submitBtn").prop("disabled", true);
			    }else{
			    	// Here submitBtn is id for submitBtn
			    	 $("#submitBtn").prop("disabled", false);
			    }
			    }
			});
		});
		
		 $("#countryid").change(function () {
			 //To remove first selected country states.e.g. USA selected first then India selected
			 // for india dropdown USA+Indias states were comes 
			 $("#stateid").find('(option:not(:first))').remove();
			 $("#cityid").find('(option:not(:first))').remove();
			 $.ajax({
				    type : "GET",
				    url : "countryChange?countryId=" + $("#countryid").val(),
				    success: function(data){// Data is coming from controller
				    //response from controller
				    // $.each(data, function (key, value) instead we can use
				    	 $.each(data, function (stateId, stateName) {
				             $('#stateid').append($('<option>').text(stateName).attr('value',stateId));
				         });
				    }
				});
		 });
		 
		 $("#stateid").change(function () {
			 // To remove first selected states cities.
			 $("#cityid").find('(option:not(:first))').remove();
			 $.ajax({
				    type : "GET",
				    url : "stateChange?stateId=" + $("#stateid").val(),
				    success: function(data){// Data is coming from controller
				    //response from controller
				    	 $.each(data, function (cityId, cityName) {
				             $('#cityid').append($('<option>').text(cityName).attr('value',cityId));
				         });
				    }
				});
		 });
		 
		 
	});
</script>
</head>
<body>
	<div align="center">
		<h3>Register Here</h3>

		<font color='green'>${succMsg}</font> <font color='red'>${failMsg}</font>

		<form:form action="userregistration" method="post"
			modelAttribute="userAcc">
			<table>
				<tr>
					<td>First Name :</td>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td>Last Name :</td>
					<td><form:input path="userLastName" /></td>
				</tr>
				<tr>
					<td>Email :</td>
					<%-- span we are taking to display unique or duplicate  --%>
					<td><form:input path="userEmail" id="userEmail" />
					<font color='red'><span id="errMsg"></span></font>
					</td>
				</tr>
				<tr>
					<td>Phone No :</td>
					<td><form:input path="userMobileNumber" /></td>
				</tr>
				<tr>
					<td>D.O.B</td>
					<td><form:input path="dateOfBirth" /></td>
				</tr>
				<tr>
					<td>Gender :</td>
					<td><form:radiobutton path="gender" value="M" />Male <form:radiobutton
							path="gender" value="F" />Fe-Male</td>
				</tr>
				<tr>
					<td>Select Country :</td>
					<td><form:select path="countryId" id="countryid">
							<form:option value="">-Select-</form:option>
							<form:options items= "${countries}" />

						</form:select></td>
				</tr>

				<tr>
					<td>Select State :</td>
					<td><form:select path="stateId" id="stateid">
							<form:option value="${countries}">-Select-</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>Select City :</td>
					<td><form:select path="cityId" id="cityid">
							<form:option value="">-Select-</form:option>
						</form:select></td>
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