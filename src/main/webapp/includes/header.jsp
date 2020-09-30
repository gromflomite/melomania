<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>

	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-info">

		<a class="navbar-brand" href="home">melomania</a>

		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarCollapse">

			<ul class="navbar-nav mr-auto">

				<li class="nav-item">
					<a class="nav-link ${ ('home' eq param.activeTag) ? 'active' : '' }" href="home">Home</a>
				</li>
				
				<li class="nav-item">
					<div class="dropdown genres-dropdown">
						<button class="btn btn-info dropdown-toggle nav-link" type="button" id="dropdownMenuButton" data-toggle="dropdown">Genres</button>

	
						<!-- The dropdown values comes from a Java listener (AppListener.java) -->
						<div class="dropdown-menu">
							<a class="dropdown-item" href="home">Show all albums</a>

							<c:forEach items="${genres}" var="genre">
								<a class="dropdown-item" href="home?idGenre=${genre.id}&genre=${genre.genre}">${genre.genre}</a>
							</c:forEach>
						</div>

					</div>
				</li>

			</ul>

			<!-- User session button common to all users (logged or not) ------------------------------>
			<ul class="navbar-nav float-right">

				<c:if test="${empty userLogin}">

					<li class="nav-item">
						<a class="nav-link" href="views/login/login.jsp">Log in</a>
					</li>

				</c:if>

				<c:if test="${not empty userLogin}">

					<li class="nav-item">
						<a href="views/frontoffice/fohome" class="badge badge-warning mr-3 user-badge">${userLogin.name} (${userLogin.role.type_role})</a>
					</li>

					<li class="nav-item">
						<a class="nav-link" href="logout">Log out</a>
					</li>

				</c:if>

			</ul>
			<!-- User session button end ------------------------------------------------------------->

		</div>

	</nav>

	<!-- Including the .jsp to show feedback -->
	<%@ include file="feedback.jsp"%>

</header>