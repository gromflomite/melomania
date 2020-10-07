<div id="layoutSidenav">
	<div id="layoutSidenav_nav">
		<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
			<div class="sb-sidenav-menu">
				<div class="nav">
					<div class="sb-sidenav-menu-heading">Menu</div>
					<a class="nav-link" href="home">
						<div class="sb-nav-link-icon">
							<i class="fas fa-home"></i>
						</div>
						Home
					</a>

					<a class="nav-link" href="views/backoffice/bouserprofile">
						<div class="sb-nav-link-icon">
							<i class="fas fa-id-card"></i>
						</div>
						Profile
					</a>

					<div class="sb-sidenav-menu-heading">Management</div>

					<a class="nav-link" href="views/backoffice/albums">
						<div class="sb-nav-link-icon">
							<i class="fas fa-podcast"></i>
						</div>
						Albums
					</a>

					<a class="nav-link" href="#">
						<div class="sb-nav-link-icon">
							<i class="fas fa-users"></i>
						</div>
						Users
					</a>

				</div>

			</div>
			<div class="sb-sidenav-footer">
				<div class="small">Logged in as:</div>
				${userLogin.name} (${userLogin.role.type_role})
			</div>
		</nav>
	</div>

	<div id="layoutSidenav_content">
		<main>

			<!-- Including the .jsp to show feedback -->
			<%@ include file="feedback.jsp"%>

			<div class="container-fluid mt-2">

				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item active">Personal area</li>
				</ol>

			</div>
		</main>