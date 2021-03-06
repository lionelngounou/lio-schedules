<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> 
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><g:layoutTitle default="Ma Schedules"/></title>
	<r:require module="core_rs"/><!-- Le fav and touch icons -->
	<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
	<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
	<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
	<r:layoutResources />
	<g:layoutHead/>
</head>
<body>

	<div class="navbar navbar-fixed-top">
		<g:render template="/layouts/inner_navbar" />
	</div>
	
	<div class="container" >
		
		<header class="jumbotron subhead" id="overview">
		</header>
		
		<br><br><br><br>
		
		<section id="forms">
			<div class="row"></div>
			<div class="row">
				<div class="span11 offset1">
					<g:layoutBody/>
				</div>
			</div>
		</section>
		
		<br><br><br>
		
		<footer id="footer">
			<g:render template="/layouts/inner_footer" />
		</footer>
	</div>
	
	<r:layoutResources />
</body>
</html>
