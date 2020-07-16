<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="../../includes/head.jsp">
	<jsp:param name="title" value="Backoffice" />
</jsp:include>

<jsp:include page="../../includes/header.jsp">
	<jsp:param name="activeTag" value="backoffice" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="container">
	<h1 class="mt-5">Backoffice (admin zone)</h1>
	
	<h3 class="mt-5">Albums approved: ${albums_approved}</h3>
	<h3>Albums pending: ${albums_pending}</h3>
	
</div>

<!----------------- Getting the <footer> ---------------->
<%@include file="../../includes/footer.jsp"%>
<!-- Getting the foot -->
<%@include file="../../includes/foot.jsp"%>
<!------------------------------------------------------->

