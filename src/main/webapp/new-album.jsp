<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="includes/head.jsp">
	<jsp:param name="title" value="New album" />
</jsp:include>

<jsp:include page="includes/header.jsp">
	<jsp:param name="activeTag" value="new-album" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container">

	<hr>

	<h2>Add or edit album of your collection</h2>

	<hr>

	<form id="newalbumform" action="newalbum" method="post">

		<div class="form-group">
			<label for="albumTitle">Album title:</label>
			<input type="text" name="albumTitle" class="form-control" id="albumTitle" value="${album.title}" placeholder="Album title" required>
		</div>

		<div class="form-group">
			<label for="artist">Artist:</label>
			<input type="text" name="artist" class="form-control" id="artist" value="${album.artist}" placeholder="Artist (minimum 2 characters)" required>
			
		</div>

		<div class="form-group">
			<label for="year">Publishing year:</label>
			<input type="text" name="year" class="form-control" id="year" value="${album.year}" placeholder="Year (between 1500 and 2050)" required>
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

<!-- Getting the <footer> -->
<jsp:include page="includes/footer.jsp" />
<!-- Getting the foot -->
<jsp:include page="includes/foot.jsp" />