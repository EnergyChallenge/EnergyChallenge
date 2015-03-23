<%-- --%>
<!DOCTYPE html>
<head>
	<meta name="layout" content="page"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<!-- TODO Title -->
	<title><g:layoutTitle default="Grails"/> - EnergyChallenge</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- TODO Favicons -->
	<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
	<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
	<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
	<!--<asset:stylesheet src="base.css" /> TODO DELETE -->
	<asset:stylesheet src="basic.css" />
	<asset:stylesheet src="fontawesome/css/font-awesome.min.css" />
	<g:layoutHead />
</head>
<body class="${pageProperty(name: 'body.class')}">
	<header>
		<div class="inside">
			<!-- TODO Header -->
			<div class="topLeft">
				<!-- <a href="http://grails.org"><asset:image src="grails_logo.png" alt="Grails"/></a>  -->
				<h1>EnergyChallenge (Header)</h1>
			</div>
			<g:pageProperty name="page.userInfo"/>
		</div>
	</header>
	<section id="container" >
		<div class="inside">  		
	  		<g:layoutBody />
  		</div>
	</section>
	<!-- TODO role benötigt?! -->
	<footer class="footer" role="contentinfo">
		<div class="inside">
		</div>
	</footer>
</body>
</html>
