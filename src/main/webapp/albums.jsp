<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="includes/head.jsp">
	<jsp:param name="title" value="Your collection" />
</jsp:include>

<jsp:include page="includes/header.jsp">
	<jsp:param name="activeTag" value="collection" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container">
	
	<hr>
	
	<h2 class="my-3">My album collection</h2>
	
	<hr>

	<table id="table" class="tabla table table-striped">

		<thead>
			
			<tr>				
				<td>Title</td>
				<td>Artist</td>
				<td>Year</td>
				<td>Comments</td>
				<td>Cover</td>
				<td>Manage</td>
			</tr>
		
		</thead>

		<tbody>

			<c:forEach items="${albums}" var="a">

				<tr>					
					<td>${a.title}</td>
					<td>${a.artist}</td>
					<td>${a.year}</td>
					<td>${a.comments}</td>
					<td>
						<img src="${a.cover}" alt="Album cover">
					</td>					
					<td id="formOptionButtons">
						<a href="newalbum?id=${a.id}" class="mr-2"><i class="far fa-edit fa-1x" title="Edit"></i></a>
						<a href="deletealbum?id=${a.id}" onclick="confirmDelete('${a.title}')"><i class="fas fa-trash fa-1x" title="Delete"></i></a>
					</td>					
				</tr>

			</c:forEach>

		</tbody>

	</table>

</div>

<!----------------- Getting the <footer> ---------------->
<jsp:include page="includes/footer.jsp" />
<!-- Getting the foot -->
<jsp:include page="includes/foot.jsp" />
<!------------------------------------------------------->