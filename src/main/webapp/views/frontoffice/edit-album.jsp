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
	
	<h3 class="my-5">Edit album</h3>
	
	<form id="newalbumform" action="views/frontoffice/fonewalbum" method="post">

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
			<label for="categoria_id">Genre:</label>
			<select class="custom-select" name="genre" id="genre">
				<c:forEach items="${genres}" var="genre">
					<option value="${genre.id}" ${ ( genre.id eq album.genre.id ) ? "selected" : "" }>${genre.genre}</option>
				</c:forEach>
			</select>
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

<!----------------- Including footer ---------------->
<%@include file="../../includes/dashboard-foot.jsp"%>
<!------------------------------------------------------->

