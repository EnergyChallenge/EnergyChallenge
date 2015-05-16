<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="page"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="ENERGY|CHALLENGE"/></title>
		<link href="/energy-challenge/assets/googleapi/api.css" rel="stylesheet" type="text/css">
		<%-- http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800 --%>
		<!-- Favicons -->
		<link rel="apple-touch-icon" sizes="57x57" href="${assetPath(src: 'favicons/apple-touch-icon-57x57.png')}">
		<link rel="apple-touch-icon" sizes="60x60" href="${assetPath(src: 'favicons/apple-touch-icon-60x60.png')}">
		<link rel="apple-touch-icon" sizes="72x72" href="${assetPath(src: 'favicons/apple-touch-icon-72x72.png')}">
		<link rel="apple-touch-icon" sizes="76x76" href="${assetPath(src: 'favicons/apple-touch-icon-76x76.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'favicons/apple-touch-icon-114x114.png')}">
		<link rel="apple-touch-icon" sizes="120x120" href="${assetPath(src: 'favicons/apple-touch-icon-120x120.png')}">
		<link rel="apple-touch-icon" sizes="144x144" href="${assetPath(src: 'favicons/apple-touch-icon-144x144.png')}">
		<link rel="apple-touch-icon" sizes="152x152" href="${assetPath(src: 'favicons/apple-touch-icon-152x152.png')}">
		<link rel="apple-touch-icon" sizes="180x180" href="${assetPath(src: 'favicons/apple-touch-icon-180x180.png')}">
		<link rel="icon" type="image/png" href="${assetPath(src: 'favicons/favicon-32x32.png')}" sizes="32x32">
		<link rel="icon" type="image/png" href="${assetPath(src: 'favicons/android-chrome-192x192.png')}" sizes="192x192">
		<link rel="icon" type="image/png" href="${assetPath(src: 'favicons/favicon-96x96.png')}" sizes="96x96">
		<link rel="icon" type="image/png" href="${assetPath(src: 'favicons/favicon-16x16.png')}" sizes="16x16">
		<link rel="manifest" href="${assetPath(src: 'favicons/manifest.json')}">
		<meta name="msapplication-TileColor" content="#da532c">
		<meta name="msapplication-TileImage" content="${assetPath(src: 'favicons/mstile-144x144.png')}">
		<meta name="theme-color" content="#ffffff">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes" />
		<asset:stylesheet src="basic.css" />
		<asset:stylesheet src="fontawesome/css/font-awesome.min.css" />
		<asset:javascript src="jqplot/jquery.min.js" />
		<g:javascript library='jquery' />
		<asset:stylesheet src="public.css" />
		<g:layoutHead />
	</head>

	<body class="${pageProperty(name: 'body.class')}">
		<div id="wrapper">
			<header>
				<a id="logo">
					<asset:image src="klik_logo.png" alt="klik logo" />
				</a>
				<h1>Energy | Challenge</h1>
			</header>
			<section id="content" >
				<g:if test="${flash.error}">
					<div class="flasherror">${flash.error}</div>
					<g:javascript>
						$(".flasherror").hide();
						$(".flasherror").slideDown('slow');
						setTimeout(function() {$(".flasherror").fadeOut('slow');}, 2000);
					</g:javascript>
				</g:if>
				<g:layoutBody />
			</section>
			<footer>
				<a href="http://www.uni-kiel.de/suchen/impressum.shtml">Impressum</a>
				 &bull;
				<a href="http://www.uni-kiel.de/suchen/kontakt.shtml">Kontakt</a>
				 &bull;
				<a href="http://www.uni-kiel.de/suchen/impressum.shtml#datenschutz">Datenschutz</a>
			</footer>
		</div>
	</body>

</html>
