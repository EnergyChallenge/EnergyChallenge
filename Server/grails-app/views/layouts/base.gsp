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

	<asset:stylesheet src="base.css" />
	<g:layoutHead />
</head>
<body>
	<header id="header">
		<!-- Here is the Page head -->
		<div class="topLeft">
			<a href="http://grails.org"><asset:image src="grails_logo.png" alt="Grails"/></a>
			<h1>EnergyChallenge (Header)</h1>
		</div>
		<div class="topRight">
			<g:pageProperty name="page.topRight"/>
		</div>
		<div class="clear"></div>
	</header>
	
	<div class="message" >
		<g:if test="${flash.message}">
    		<div class="message">${flash.message}</div>
  		</g:if>
	</div>
	
	<g:layoutBody />
	
	<!-- This is the page Body -->
	<footer class="footer" role="contentinfo">
	
	</footer>
</body>
</html>
