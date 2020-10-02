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
	
	<div id="personalized-jumbo" class="jumbotron jumbotron-fluid bg-info text-white text-center">
		<div class="container">
			<h2 class="display-4">Personal area</h2>
			<p class="lead">Welcome!</p>
		</div>
	</div>
	
</div>

<!----------------- Including footer ---------------->
<%@include file="../../includes/dashboard-foot.jsp"%>
<!------------------------------------------------------->
