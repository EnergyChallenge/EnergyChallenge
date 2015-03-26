<g:applyLayout name="base">
	<head>
		<asset:stylesheet src="public.css" />
		<g:layoutHead />
	</head>
	<content tag="logo">
	<div class="topLeft">
				<a href="https://www.klik.uni-kiel.de/de"><asset:image src="klik_Logo.png" alt="klik_Logo" align="left" width="200px" heigth="auto" /></a>
				<h1>EnergyChallenge</h1>
			</div>
	</content>
	
	<body>
		<div id="content">
                                <div class="floater">
                                        <g:if test="${flash.message}">
                                                <div class="flashmessage">${flash.message}</div>
                                        </g:if>
					<g:layoutBody />
				</div>
                               <div class="clear"></div>
                </div>

	</body>
</g:applyLayout>
