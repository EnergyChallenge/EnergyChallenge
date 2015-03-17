<%-- --%>
<!DOCTYPE html>
<head>
	<meta name="layout" content="page"/>
	<!-- default Grails -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><g:layoutTitle default="Grails"/> - EnergyChallenge</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
	<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
	<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">

	<!--<asset:stylesheet src="base.css" /> DELETE -->
	<asset:stylesheet src="basic.css" />
	<g:layoutHead />
</head>
<body>
	<header>
		<div class="inside">
			<div class="topLeft">
				<!-- <a href="http://grails.org"><asset:image src="grails_logo.png" alt="Grails"/></a>  -->
				<h1>EnergyChallenge (Header)</h1>
			</div>
			<div class="topRight">
				<g:pageProperty name="page.topRight"/>
			</div>
		</div>
	</header>
	<section id="container" >
		<div class="inside">
			<g:if test="${flash.message}">
	    		<div class="message">${flash.message}</div>
	  		</g:if>
	  		
	  		<g:layoutBody />
  		</div>
	</section>
	<footer class="footer" role="contentinfo">
		<div class="inside">
		</div>
	</footer>
</body>
</html>
