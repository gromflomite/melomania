<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		
		<a class="navbar-brand" href="home"><i class="fas fa-headphones-alt"></i> melomania</a>		
		
		<button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle">
			<i class="fas fa-bars"></i>
		</button>		
		<!-- Navbar-->
		<ul class="navbar-nav ml-auto mr-0 mr-md-3 my-2 my-md-0">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<i class="fas fa-user fa-fw"></i>
				</a>
				<div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
					<!-- <a class="dropdown-item" href="views/frontoffice/fouserprofile">Profile</a> -->
					<a class="dropdown-item" ${('Administrator' eq userLogin.role.type_role)? 'href="views/backoffice/bouserprofile"' : 'href="views/frontoffice/fouserprofile"'}>Profile</a>					
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="logout">Logout</a>
				</div>
			</li>
		</ul>
	</nav>
	