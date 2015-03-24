<g:applyLayout name="base">
	<head>
		<asset:stylesheet src="public.css" />
		<g:layoutHead />
	</head>
	<content tag="logo">
	<div class="topLeft">
				<%-- <a href="http://grails.org"><asset:image src="grails_logo.png" alt="Grails"/></a>  --%>
				<figure><asset:image src="klik_Logo.png" alt="klik_Logo" align="left" width="200px" heigth="auto" /></figure>
				<h1>EnergyChallenge</h1>
			</div>
	</content>
	
	<body>
		<div class="mainBody">
			<div class="pageBody">
				<g:layoutBody />
			</div>
		</div>
	</body>
</g:applyLayout>
