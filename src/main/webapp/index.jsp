<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="includes/head.jsp">
	<jsp:param name="title" value="Home" />
</jsp:include>

<jsp:include page="includes/header.jsp">
	<jsp:param name="activeTag" value="home" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">

	<h1 class="my-5">Welcome to melomania</h1>
	
	<h3>In this page will appear your last five Albums (work in progress)</h3>

</div>

<!-- Getting the <footer> -->
<jsp:include page="includes/footer.jsp" />
<!-- Getting the foot -->
<jsp:include page="includes/foot.jsp" />