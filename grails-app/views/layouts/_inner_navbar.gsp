
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" href="${createLink(uri : '')}" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</a>
			<a class="brand" href="${createLink(uri : '')}">Schedulez</a>
			<div class="nav-collapse collapse" id="main-menu">
				<ul class="nav" id="main-menu-left">
					<li><a id="help-link" href="#">How It Works</a></li>
					<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">How To<b class="caret"></b></a>
						<ul class="dropdown-menu" id="swatch-menu">
							<li><a href="../default/">Create a schedule</a></li>
							<li class="divider"></li>
							<li><a href="../amelia/">View by month</a></li>
							<li><a href="../cerulean/">View by week</a></li>
							<li><a href="../cosmo/">View by day</a></li>
						</ul>
					</li>
					<li><a id="help-link" href="#">Contact Us</a></li>
					<li><a id="help-link" href="#">Help</a></li>
				</ul>
				<ul class="nav pull-right" id="main-menu-right">
					<sec:ifLoggedIn>
						<li><a rel="tooltip" href="#" data-original-title="Showcase of Bootstrap sites &amp; apps">My profile</a></li>
						<li><a rel="tooltip" href="#" data-original-title="Showcase of Bootstrap sites &amp; apps">My schedules</a></li>
						<li><a rel="tooltip" onclick="$('#logoutForm').submit();" href="#" data-original-title="Logout">Logout</a></li>
							<g:form uri="/logout" name="logoutForm" class="hidden" method="POST"></g:form>
						</sec:ifLoggedIn>	
						<sec:ifNotLoggedIn>
							<li><g:link uri="/login">Login</g:link></li>
							<li><a class="btn btn-success" href="${createLink(uri : '/register')}" style="line-height:9px;">Register</a></li>
						</sec:ifNotLoggedIn>
					</ul>
				</div>
			</div>
		</div>
