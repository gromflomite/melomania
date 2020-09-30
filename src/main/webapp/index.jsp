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

	<div class="container">

		<div id="card-deck-title">

			<h3>${cardDeckTitle}</h3>

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



<!----------------- Getting the <footer> ---------------->
<%@include file="../includes/footer.jsp"%>
<!-- Getting the foot -->
<%@include file="../includes/foot.jsp"%>
<!------------------------------------------------------->