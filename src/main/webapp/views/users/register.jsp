<!-- Including head and header -->
<jsp:include page="../../includes/head.jsp">
	<jsp:param name="title" value="Register" />
</jsp:include>

<%@include file="../../includes/header.jsp"%>
<!-- ----------------------------------------------------------------------------- -->

<!-- JSP additions -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- ----------------------------------------------------------------------------- -->


<form action="userRegister" id="register-form" method="post" accept-charset="UTF-8">
	<!-- onsubmit="cipherPassword()" -->

	<h2 class="my-5">Register</h2>

	<div class="form-group">
		<label for="userName">Username (nick)</label>
		<input type="text" class="form-control" name="userName" id="userName" value="${user.name}" required="required" autofocus="autofocus">
	</div>

	<div class="form-group">
		<label for="userName" class="mt-3">Email</label>
		<input type="text" class="form-control" name="email" id="email" value="${user.email}" required="required">
	</div>

	<div class="form-group">
		<label for="password" class="mt-3">Password</label>
		<input type="password" class="form-control" name="password" id="password" required="required">
	</div>

	<div class="form-group">
		<label for="passwordConfirm" class="mt-3">Confirm password</label>
		<input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm" required="required">
	</div>

	<button type="submit" class="btn btn-info mt-3">Sign up</button>

</form>

<!----------------- Getting the <footer> ---------------->
<%@include file="../../includes/footer.jsp"%>
<!-- Getting the foot -->
<%@include file="../../includes/foot.jsp"%>
<!------------------------------------------------------->