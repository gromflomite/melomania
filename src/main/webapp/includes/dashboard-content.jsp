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

					<a class="nav-link" href="index.html">
						<div class="sb-nav-link-icon">
							<i class="fas fa-id-card"></i>
						</div>
						Profile
					</a>

					<div class="sb-sidenav-menu-heading">Your albums</div>

					<a class="nav-link" href="views/frontoffice/fonewalbum">
						<div class="sb-nav-link-icon">
							<i class="fas fa-plus-square"></i>
						</div>
						Create new album
					</a>

					<a class="nav-link" href="views/frontoffice/user-albums?albumsrequest=approved">
						<div class="sb-nav-link-icon">
							<i class="fas fa-thumbs-up"></i>
						</div>
						Approved albums
					</a>

					<a class="nav-link" href="views/frontoffice/user-albums?albumsrequest=not-approved">
						<div class="sb-nav-link-icon">
							<i class="far fa-pause-circle"></i>
						</div>
						Pending albums
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



				<div class="row justify-content-md-center">

					<div class="col-xl-3 col-md-6">
						<div class="card bg-success text-white mb-4">
							<div class="card-body"><i class="fas fa-thumbs-up"></i> Approved albums: ${userAlbums.userAlbumsApproved}</div>
							<div class="card-footer d-flex align-items-center justify-content-between">
								<a class="small text-white stretched-link" href="views/frontoffice/user-albums?albumsrequest=approved">View approved albums</a>
								<div class="small text-white">
									<i class="fas fa-angle-right"></i>
								</div>
							</div>
						</div>
					</div>

					<div class="col-xl-3 col-md-6">
						<div class="card bg-warning text-white mb-4 text-dark">
							<div class="card-body"><i class="far fa-pause-circle"></i> Pending albums: ${userAlbums.userAlbumsPending}</div>
							<div class="card-footer d-flex align-items-center justify-content-between">
								<a class="small text-white stretched-link text-dark" href="views/frontoffice/user-albums?albumsrequest=not-approved">View pending albums</a>
								<div class="small text-dark">
									<i class="fas fa-angle-right"></i>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</main>