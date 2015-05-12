<g:applyLayout name="base">
	<head>
		<asset:stylesheet src="public.css" />
		<g:layoutHead />
	</head>
	<content tag="logo">
		<a href="/" class="logo">
			<asset:image src="klik_logo.png" alt="klik logo" />
		</a>
		<h1 class="logofont">Energy | Challenge</h1>
	</content>
	
	<body>
		<div id="content">
			<div class="floater">
				<g:if test="${flash.error}">
					<div class="flasherror">${flash.error}</div>
					<g:javascript>
						$(".flasherror").hide();
						$(".flasherror").slideDown('slow');
						setTimeout(function() {$(".flasherror").fadeOut('slow');}, 2000);
					</g:javascript>
				</g:if>
				<g:layoutBody />
			</div>
			<div class="clear"></div>
		</div>
		<footer class="clear">
			<a href="http://www.uni-kiel.de/suchen/impressum.shtml">Impressum</a>
			 &bull;
			<a href="http://www.uni-kiel.de/suchen/kontakt.shtml">Kontakt</a>
			 &bull;
			<a href="http://www.uni-kiel.de/suchen/impressum.shtml#datenschutz">Datenschutz</a>
		</footer>
	</body>
</g:applyLayout>
