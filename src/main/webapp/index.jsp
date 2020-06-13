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

	<div class="jumbotron mt-5">
		
		<h1 class="display-4">Welcome to melomania!</h1>
		
		<p class="lead">A simple app to manage your music collection</p>
		
		<hr class="my-5">
		<p>Right now, you can :</p>

		<a class="btn btn-info btn-lg" href="album" role="button">Check your collection</a>

		<a class="btn btn-info btn-lg ml-5" href="newalbum?id=0" role="button">Add an album</a>

		<hr class="my-5">

		<p class="lead">Here will appear your last five Albums <span id="workinprogress">(a work in progress)</span></p>

	</div>

</div>

<!----------------- Getting the <footer> ----------------->
<jsp:include page="includes/footer.jsp" />
<!-- Getting the foot -->
<jsp:include page="includes/foot.jsp" />
<!-------------------------------------------------------->