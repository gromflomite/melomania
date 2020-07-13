<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="../../includes/head.jsp">
	<jsp:param name="title" value="New user" />
</jsp:include>

<jsp:include page="../../includes/header.jsp">
	<jsp:param name="activeTag" value="new-user" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">

	<hr>

	<h2>Add or edit users</h2>

	<hr>

	${user}

	<div class="jumbotron">
		<div class="container">
			<ul>
				<li>User is not able to edit the ID field</li>
				<li>Initial number 0 in ID if user comes here via "Create new user" from index</li>
				<li>ID number field filled with value from the DB if user uses "Edit" option from "See users table"</li>
			</ul>
		</div>
	</div>

	<form action="newuser" method="post" onsubmit="cipherPassword()">

		<div class="form-group">
			<label for="id">ID:</label>
			<input type="text" class="form-control" name="id" id="id" value="${user.id}" placeholder="Id" readonly>
		</div>
		
		<div class="form-group">
			<label for="userName">User name:</label>
			<input type="text" class="form-control" name="userName" id="userName" value="${user.name}" placeholder="User name" required>
		</div>
		
		<div class="form-group">
			<label for="userName">Email:</label>
			<input type="text" class="form-control" name="userEmail" id="userEmail" value="${user.email}" placeholder="User email" required>
		</div>
		
		<!-- If new user (id == 0) show just the initial password field -->
		<c:if test="${user.id == 0}">

			<div class="form-group">
				<label for="password">Password:</label>
				<!-- id used by JS cipher, name by Java controller -->
				<!-- Using the same id (for JS cipher) than below because just one password field will appear in user view (because if's) -->
				<input type="password" name="password" id="password" value="" class="form-control" placeholder="Password" required>
			</div>

		</c:if>
		
		<div class="form-group">
			<label for="idRol">User role:</label>
			<select class="form-control" name="idRol" id="idRol">
				<!-- Getting the role id value and using ternary operators to add "selected" tag in the select form -->				
				<option value="4" ${ ( 4 == user.role.id_role ) ? "selected" : "" }>Administrator</option>
				<option value="5" ${ ( 5 == user.role.id_role ) ? "selected" : "" }>Moderator</option>
				<option value="6" ${ ( 6 == user.role.id_role ) ? "selected" : "" }>Listener</option>
			</select>
		</div>
		
		<!-- If editing user (id == user id on DB) show the option to change password -->
		<c:if test="${user.id != 0}">

			<a class="btn btn-link ml-0 pl-0 mb-3" data-toggle="collapse" href="#passwordChange" role="button">Change password</a>

			<br>

			<div class="collapse" id="passwordChange">

				<div class="form-group">
					<label for="passwordChange">New password:</label>
					<!--  id used by JS cipher, name by Java controller -->
					<!-- Using the same id (for JS cipher) than above because just one password field will appear in user view (because if's) -->
					<input type="password" class="form-control" name="passwordChange" id="password" value="" placeholder="New password">
				</div>

				<div class="form-group">
					<label for="passwordChangeConfirm">Confirm new password:</label>
					<!--  id used by JS cipher, name user by Java controller -->
					<input type="password" class="form-control" name="passwordChangeConfirm" id="passwordChangeConfirm" value="" placeholder="New password confirmation">
				</div>

			</div>

		</c:if>
		
		<button type="submit" class="btn btn-primary">Save user</button>

	</form>

</div>

<!----------------- Getting the <footer> ---------------->
<%@include file="../../includes/footer.jsp"%>
<!-- Getting the foot -->
<%@include file="../../includes/foot.jsp"%>
<!------------------------------------------------------->