<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="../../includes/head.jsp">
	<jsp:param name="title" value="Login" />
</jsp:include>

<jsp:include page="../../includes/header.jsp">
	<jsp:param name="activeTag" value="login" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<!-- JSP additions -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- ----------------------------------------------------------------------------- -->


<div class="container w-25">

	<h2 class="mt-5">Log in</h2>

	<form action="login" method="post" onsubmit="cipherPassword()">

		<div class="form-group my-3">
			<label for="username">Username:</label>
			<p id="nameCheck"></p>
			<input type="text" id="userName" name="userName" class="form-control" id="username" placeholder="Enter your username" onkeyUp="searchUserByName(event)">
		</div>

		<div class="form-group">
			<label for="password">Password:</label>
			<input type="password" name="userPassword" class="form-control" id="password" placeholder="Enter your password">
		</div>

		<button type="submit" class="btn btn-info mt-3">Log in</button>

	</form>

	<br>

</div>

<!----------------- Getting the <footer> ---------------->
<%@include file="../../includes/footer.jsp"%>
<!-- Getting the foot -->
<%@include file="../../includes/foot.jsp"%>
<!------------------------------------------------------->