<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="../includes/head.jsp">
	<jsp:param name="title" value="New album" />
</jsp:include>

<jsp:include page="../includes/header.jsp">
	<jsp:param name="activeTag" value="new-album" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container">

	<hr>

	<h2>Add or edit album of your collection</h2>

	<hr>

	<form id="newalbumform" action="newalbum" method="post">

		<div class="form-group" hidden="">
			<label for="albumId">Album ID:</label>
			<input type="text" name="albumId" class="form-control" id="albumId" value="${album.id}" placeholder="Album title" readonly>
		</div>


		<div class="form-group">
			<label for="albumTitle">Album title:</label>
			<input type="text" name="albumTitle" class="form-control" id="albumTitle" value="${album.title}" placeholder="Album title" required>
		</div>

		<div class="form-group">
			<label for="artist">
				Artist
				<span class="formRestriction"> (2 characters minimum) </span>	
				:
			</label>
			<input type="text" name="artist" class="form-control" id="artist" value="${album.artist}" placeholder="Artist" required>

		</div>

		<div class="form-group">
			<label for="year">
				Publishing year
				<span class="formRestriction"> (between 1900 and 2025) </span>
				:
			</label>
			<input type="text" name="year" class="form-control" id="year" value="${album.year}" placeholder="Year" required>
		</div>

		<div class="form-group">
			<label for="comments">Comments:</label>
			<input type="text" name="comments" class="form-control" id="comments" value="${album.comments}" placeholder="Comments (optional)">
		</div>

		<div class="form-group">
			<label for="cover">Album cover URL:</label>
			<input type="text" name="cover" class="form-control" id="cover" value="${album.cover}" placeholder="Album cover URL (optional)">
		</div>

		<button type="submit" class="btn btn-info">Save album</button>

	</form>

</div>

<!----------------- Getting the <footer> ---------------->
<%@include file="../includes/footer.jsp" %>
<!-- Getting the foot -->
<%@include file="../includes/foot.jsp" %>
<!------------------------------------------------------->