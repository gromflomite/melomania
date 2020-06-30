<!-- Including head and header - Setting the page title and "active" tag to navbar -->
<jsp:include page="../../includes/head.jsp">
	<jsp:param name="title" value="Users" />
</jsp:include>

<jsp:include page="../../includes/header.jsp">
	<jsp:param name="activeTag" value="users" />
</jsp:include>
<!-- ----------------------------------------------------------------------------- -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<div class="container">

	<hr>
		
	<h2 class="my-3">Users</h2>
	
	<hr>

	<table id="table" class="tabla table table-striped">

		<thead>

			<tr>
				<td hidden="">User ID</td>
				<td>Name</td>
				<td>Email</td>
				<td hidden="">Role ID</td>
				<td>User type</td>
				<td>Manage</td>				
			</tr>

		</thead>

		<tbody>

			<c:forEach items="${users}" var="user">

				<tr>
					<td hidden="">${user.id}</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td hidden="">${user.role.id_role}</td>
					<td>${user.role.type_role}</td>					
					<td id="formOptionButtons">
						<a href="newuser?userid=${user.id}" class="mr-2">
							<i class="far fa-edit fa-1x" title="Edit"></i>
						</a>
						<a href="deletealbum?id=${a.id}" onclick="confirmDelete('${a.title}')">
							<i class="fas fa-trash fa-1x" title="Delete"></i>
						</a>
					</td>
				</tr>

			</c:forEach>

		</tbody>

	</table>






</div>



<!----------------- Getting the <footer> ---------------->
<%@include file="../../includes/footer.jsp"%>
<!-- Getting the foot -->
<%@include file="../../includes/foot.jsp"%>
<!------------------------------------------------------->
