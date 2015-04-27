<g:applyLayout name="base">
	<head>
		<asset:stylesheet src="public.css" />
		<g:layoutHead />
	</head>
	<content tag="logo">
		<a href="https://www.klik.uni-kiel.de/de" class="logo">
			<asset:image src="klik_Logo.png" alt="klik_Logo" />
		</a>
		<h1>EnergyChallenge</h1>
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

	</body>
</g:applyLayout>
