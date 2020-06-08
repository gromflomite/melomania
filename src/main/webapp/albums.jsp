<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="includes/head.jsp">
	<jsp:param name="title" value="Albums" />
</jsp:include>

<jsp:include page="includes/header.jsp">
	<jsp:param name="activeTag" value="albums" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container">
	
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

<!-- Getting the <footer> -->
<jsp:include page="includes/footer.jsp" />
<!-- Getting the foot -->
<jsp:include page="includes/foot.jsp" />
<!-- ----------------------------------------------------------------------------- -->