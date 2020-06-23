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

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="container">

	<div class="jumbotron mt-5">

		<h1 class="display-4">Welcome to melomania!</h1>

		<p class="lead">A simple app to manage your music collection</p>

		<hr class="my-5">

		<a class="btn btn-info btn-lg" href="album" role="button">Check your collection</a>

		<a class="btn btn-info btn-lg ml-5" href="newalbum?id=0" role="button">Add an album</a>

	</div>

	<div class="container">

		<div id="card-deck-title">
			<c:if test="${allalbums}">
				<h2>${cardDeckTitle}(${fn:length(albums)} albums):</h2>
			</c:if>
			<c:if test="${!allalbums}">
				<h2>${cardDeckTitle} albums - You have ${fn:length(albums)} albums of this genre:</h2>
			</c:if>
		</div>

		<div class="card-deck">

		<c:forEach items="${albums}" var="album">

			<div class="card" id="album-card">

				<img class="card-img-top" src="${album.cover}" alt="Album cover">

				<div class="card-body">
					<h5 class="card-title my-2">
						<span id="album-title">${album.title} </span>
						<span id="album-year"> (${album.year})</span>
					</h5>
					<hr>
					<h5 class="card-text mt-3">${album.artist}</h5>
					<hr>
					<h6 class="card-text mt-3">${album.genre.genre}</h6>
					<hr>
					<p class="card-text mt-3">${album.comments}</p>
				</div>

			</div>

		</c:forEach>

	</div>

</div>

</div>

<!----------------- Getting the <footer> ----------------->
<jsp:include page="includes/footer.jsp" />
<!-- Getting the foot -->
<jsp:include page="includes/foot.jsp" />
<!-------------------------------------------------------->