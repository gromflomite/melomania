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

	<div id="personalized-jumbo" class="jumbotron jumbotron-fluid bg-success text-white">
		<div class="container">
			<h3 class="display-5">Your collection</h3>
			<p class="lead">Your albums collection (these albums have been approved).</p>
		</div>
	</div>

	<table id="table" class="tabla table table-striped">

		<thead>

			<tr>
				<td hidden="">Album ID</td>
				<td>Title</td>
				<td>Artist</td>
				<td>Year</td>
				<td>Genre</td>
				<td>Comments</td>
				<td>Cover</td>
				<td>Manage</td>
			</tr>

		</thead>

		<tbody>

			<c:forEach items="${albums_approved}" var="a">

				<tr>
					<td hidden="">${a.id}</td>
					<td>${a.title}</td>
					<td>${a.artist}</td>
					<td>${a.year}</td>
					<td>${a.genre.genre}</td>
					<td>${a.comments}</td>
					<td>
						<img id="album-cover" src="${a.cover}" alt="Album cover">
					</td>
					<td id="formOptionButtons">
						<a href="views/frontoffice/foeditalbum?idAlbum=${a.id}" class="mr-2">
							<i class="far fa-edit fa-1x" title="Edit"></i>
						</a>
						<a href="views/frontoffice/fodeletealbum?id=${a.id}" onclick="confirmDelete('${a.title}')">
							<i class="fas fa-trash fa-1x" title="Delete"></i>
						</a>
					</td>
				</tr>

			</c:forEach>

		</tbody>

	</table>

</div>

<!----------------- Including footer ---------------->
<%@include file="../../includes/dashboard-foot.jsp"%>
<!------------------------------------------------------->

