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
	
	<h1 class="my-5">Approved albums</h1>
	
	<h3>${albums_approved}</h3>	
	
</div>

<!----------------- Including footer ---------------->
<%@include file="../../includes/dashboard-foot.jsp"%>
<!------------------------------------------------------->

