<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="../../includes/head.jsp">
	<jsp:param name="title" value="Login" />
</jsp:include>

<%@include file="../../includes/header.jsp"%>
<!-- ----------------------------------------------------------------------------- -->

<!-- JSP additions -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- ----------------------------------------------------------------------------- -->

<form action="login" id="login-form" method="post" onsubmit="cipherPassword()">

	<h2 class="my-5">Log in</h2>

	<div class="form-group my-3">
		<label for="username">Username (nick)</label>
		<p id="nameCheck" style="display: none"></p>
		<input type="text" id="userName" name="userName" class="form-control" id="username" autofocus required="required" onkeyUp="searchUserByName(event)">
	</div>

	<div class="form-group">
		<label for="password" class="mt-3">Password:</label>
		<input type="password" name="userPassword" class="form-control" id="password" required="required">
	</div>

	<button type="submit" id="loginButton" class="btn btn-info mt-3" disabled>Log in</button>

</form>

<!----------------- Getting the <footer> ---------------->
<%@include file="../../includes/footer.jsp"%>
<!-- Getting the foot -->
<%@include file="../../includes/foot.jsp"%>
<!------------------------------------------------------->