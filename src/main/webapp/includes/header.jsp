<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>

	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-info">

		<a class="navbar-brand" href="index.jsp">melomania</a>

		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarCollapse">

			<ul class="navbar-nav mr-auto">

				<li class="nav-item">
					<a class="nav-link ${ ('home' eq param.activeTag) ? 'active' : '' }" href="index.jsp">Home</a>
				</li>

				<li class="nav-item">
					<a class="nav-link ${ ('collection' eq param.activeTag) ? 'active' : '' }" href="album">Collection</a>
				</li>

				<li class="nav-item">
					<a class="nav-link ${ ('new-album' eq param.activeTag) ? 'active' : '' }" href="newalbum?id=0">+Album</a>
				</li>

				<li class="nav-item">
					<div class="dropdown genres-dropdown">
						<button class="btn btn-info dropdown-toggle nav-link" type="button" id="dropdownMenuButton" data-toggle="dropdown">Genres</button>
						<div class="dropdown-menu">

							<c:forEach items="${genres}" var="genre">
								<a class="dropdown-item" href="home?idGenre=${genre.id}">${genre.genre}</a>
							</c:forEach>
							
						</div>
					</div>
				</li>

			</ul>

		</div>

	</nav>

	<!-- Including the .jsp to show feedback -->
	<jsp:include page="feedback.jsp"></jsp:include>

</header>