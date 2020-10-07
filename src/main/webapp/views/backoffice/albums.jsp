<!-- --------------------- Includes --------------------- -->
<!-- head and header -->
<jsp:include page="/includes/dashboard-head.jsp" />
<!-- navbar -->
<jsp:include page="/includes/dashboard-navbar.jsp" />
<!-- content -->
<jsp:include page="/includes/dashboard-bo-content.jsp" />
<!-- ----------------------------------------------------- -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div class="container">

	<div id="personalized-jumbo" class="jumbotron jumbotron-fluid bg-success text-white">
		<div class="container">
			<h3 class="display-5">All albums</h3>
		</div>
	</div>

	<table id="table" class="tabla table table-striped">

		<thead>

			<tr>
				<td hidden="hidden">Album ID</td>
				<td>Title</td>
				<td>Artist</td>
				<td>Year</td>
				<td>Genre</td>
				<td>Comments</td>
				<td>Cover</td>
				<td>User</td>
				<td>Approved</td>
				<td>Manage</td>
			</tr>

		</thead>

		<tbody>

			<c:forEach items="${allAlbums}" var="a">

				<tr>
					<td hidden="hidden">${a.id}</td>
					<td>${a.title}</td>
					<td>${a.artist}</td>
					<td>${a.year}</td>
					<td>${a.genre.genre}</td>
					<td>${a.comments}</td>
					<td>
						<img id="album-cover" src="${a.cover}" alt="Album cover">
					</td>
					<td>${a.user.name}</td>
					<td>
						<div class="album-approved-icon">
							<c:if test="${empty a.dateApproved}">
								<span style="font-size: 1.5em; color: #B22222;">
									<i class="fas fa-times" title="Not approved"></i>
								</span>
							</c:if>
							<c:if test="${not empty a.dateApproved}">
								<span style="font-size: 1.5em; color: #228B22;">
									<i class="fas fa-check" title="${a.dateApproved}"></i>
								</span>
							</c:if>
						</div>
					</td>
					<td id="formOptionButtons">
						<div class="formManageButtons">

							<c:if test="${empty a.dateApproved}">
								<a href="views/backoffice/album-approve?id=${a.id}" onclick="confirmApprove('${a.title}')">
									<span style="color: #008000;">
										<i class="far fa-thumbs-up" title="Approve"></i>
									</span>
								</a>
							</c:if>
							<c:if test="${not empty a.dateApproved}">
								<a href="views/backoffice/album-disapprove?id=${a.id}" onclick="confirmDisapprove('${a.title}')">
									<span style="color: #FF0000;">
										<i class="far fa-thumbs-down" title="Disapprove"></i>
									</span>
								</a>
							</c:if>

							<a href="views/backoffice/album-delete?id=${a.id}" onclick="confirmDelete('${a.title}')">
								<span style="color: #000000;">
									<i class="fas fa-trash fa-1x" title="Delete"></i>
								</span>
							</a>
							<a href="#" class="mr-2">
								<span style="color: #000000;">
									<i class="far fa-edit fa-1x" title="Edit"></i>
								</span>
							</a>
						
						</div>
					</td>
				</tr>

			</c:forEach>

		</tbody>

	</table>

</div>

<!----------------- Including footer ---------------->
<%@include file="../../includes/dashboard-foot.jsp"%>
<!------------------------------------------------------->