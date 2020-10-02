<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="../../includes/head.jsp">
	<jsp:param name="title" value="Not authorized" />
</jsp:include>

<jsp:include page="../../includes/header.jsp">
	<jsp:param name="activeTag" value="not-authorized" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="d-flex justify-content-center mt-5">
	<h1> - Not authorized - </h1>
</div>

<!----------------- Getting the <footer> ---------------->
<%@include file="../../includes/footer.jsp"%>
<!-- Getting the foot -->
<%@include file="../../includes/foot.jsp"%>
<!------------------------------------------------------->

