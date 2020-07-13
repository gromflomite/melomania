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


<div class="container">

	<h2 class="my-4">Log in</h2>

	<form action="login" method="post" onsubmit="cipherPassword()">

		<div class="form-group my-5">
			<label for="username">Username:</label>
			<input type="text" name="user" class="form-control" id="username" placeholder="Enter your username">
		</div>

		<div class="form-group">
			<label for="password">Password:</label>
			<input type="password" name="password" class="form-control" id="password" placeholder="Enter your password">
		</div>
		
		<button type="submit" class="btn btn-info mt-3">Log in</button>

	</form>

	<br>

</div>





<!----------------- Getting the <footer> ---------------->
<%@include file="../../includes/footer.jsp" %>
<!-- Getting the foot -->
<%@include file="../../includes/foot.jsp" %>
<!------------------------------------------------------->