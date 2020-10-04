<!-- --------------------- Includes --------------------- -->
<!-- head and header -->
<jsp:include page="/includes/dashboard-head.jsp" />
<!-- navbar -->
<jsp:include page="/includes/dashboard-navbar.jsp" />
<!-- content -->
<jsp:include page="/includes/dashboard-content.jsp" />
<!-- ----------------------------------------------------- -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div class="container">

	<div id="personalized-jumbo" class="jumbotron jumbotron-fluid bg-info text-white">
		<div class="container">
			<h3 class="display-5">Your profile</h3>
			<p class="lead">Check or update your details</p>
		</div>
	</div>

	<div class="table-responsive">
		<table class="table table-hover profile-table-details">

			<tr>
				<th scope="col">Name</th>
				<td>${userName}</td>
			</tr>
			<tr>
				<th scope="col">Email</th>
				<td>${userMail}</td>
			</tr>
			<tr>
				<th scope="col">Web role</th>
				<td>${userRole}</td>
			</tr>

		</table>
	</div>

	<a class="btn btn-link ml-0 pl-0 my-3" data-toggle="collapse" href="#edit-user-details" role="button">Change your details</a>

	<div class="collapse" id="edit-user-details">

		<form id="edit-user-details" action="views/frontoffice/fouserprofile" method="post" onsubmit="cipherPassword()">

			<div class="form-group">
				<label for="userName">Your name:</label>
				<input type="text" name="userName" class="form-control" id="userName" value="${userName}" placeholder="Your name" required>
			</div>

			<div class="form-group">
				<label for="userMail">Email:</label>
				<input type="text" name="userMail" class="form-control" id="userMail" value="${userMail}" placeholder="Your email">
			</div>

			<a class="btn btn-link ml-0 pl-0 mb-3 text-warning" data-toggle="collapse" href="#passwordChange" role="button">I want to change my password</a>

			<br>

			<div class="collapse" id="passwordChange">

				<div class="form-group">
					<label for="passwordChange">New password:</label>
					<!--  id used by JS cipher, name used by Java controller -->
					<input type="password" class="form-control" name="passwordChange" id="password" value="" placeholder="New password">
				</div>

				<div class="form-group">
					<label for="passwordChangeConfirm">Confirm new password:</label>
					<!--  id used by JS cipher, name user by Java controller -->
					<input type="password" class="form-control" name="passwordChangeConfirm" id="passwordChangeConfirm" value="" placeholder="Type again your new password">
				</div>

			</div>

			<button type="submit" class="btn btn-info">Save details</button>

		</form>

	</div>

</div>

<!----------------- Including footer ---------------->
<%@include file="../../includes/dashboard-foot.jsp"%>
<!------------------------------------------------------->
