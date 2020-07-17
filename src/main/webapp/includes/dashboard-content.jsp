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
							<i class="fas fa-id-card"></i></i>
						</div>
						Profile
					</a>

					<div class="sb-sidenav-menu-heading">Your albums</div>

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
			<div class="container-fluid">
				<h1 class="mt-4">Personal area</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item active">Personal area</li>
				</ol>
				<div class="row">
					<div class="col-xl-3 col-md-6">
						<div class="card bg-primary text-white mb-4">
							<div class="card-body">Primary Card</div>
							<div class="card-footer d-flex align-items-center justify-content-between">
								<a class="small text-white stretched-link" href="#">View Details</a>
								<div class="small text-white">
									<i class="fas fa-angle-right"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-md-6">
						<div class="card bg-warning text-white mb-4">
							<div class="card-body">Warning Card</div>
							<div class="card-footer d-flex align-items-center justify-content-between">
								<a class="small text-white stretched-link" href="#">View Details</a>
								<div class="small text-white">
									<i class="fas fa-angle-right"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-md-6">
						<div class="card bg-success text-white mb-4">
							<div class="card-body">Success Card</div>
							<div class="card-footer d-flex align-items-center justify-content-between">
								<a class="small text-white stretched-link" href="#">View Details</a>
								<div class="small text-white">
									<i class="fas fa-angle-right"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-md-6">
						<div class="card bg-danger text-white mb-4">
							<div class="card-body">Danger Card</div>
							<div class="card-footer d-flex align-items-center justify-content-between">
								<a class="small text-white stretched-link" href="#">View Details</a>
								<div class="small text-white">
									<i class="fas fa-angle-right"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>