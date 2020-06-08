<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>melomania | Home</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- Datatables plugin -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">

</head>

<body>
	<div class="container">
	
		<h1 class="my-5">Welcome to melomania</h1>

		<hr>

		<h2 class="my-3">Albums</h2>		

		<table id="table" class="tabla table table-striped">

			<thead>
				<tr>
					<td>Id</td>
					<td>Title</td>
					<td>Artist</td>
					<td>Year</td>
					<td>Comments</td>
					<td>Cover</td>
				</tr>
			</thead>

			<tbody>

				<c:forEach items="${albums}" var="a">

					<tr>
						<td>${a.id}</td>
						<td>${a.title}</td>
						<td>${a.artist}</td>
						<td>${a.year}</td>
						<td>${a.comments}</td>
						<td>
							<img src="${a.cover}" alt="Album cover">
						</td>
					</tr>

				</c:forEach>

			</tbody>

		</table>

	</div>

	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

	<!-- Datatables plug in -->
	<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
	
	<!-- Own custom JS's -->
	<script src="js/custom.js"></script>

</body>

</html>