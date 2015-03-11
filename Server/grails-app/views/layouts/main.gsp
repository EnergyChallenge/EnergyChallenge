<g:applyLayout name="base">
	<head>

<asset:stylesheet src="main.css" />
<asset:stylesheet src="navigation.css" />

<g:layoutHead />

	</head>
	<content tag="topRight"> 
		Informationen zum Benutzer... (Rechts oben)<br>
		<g:form name="logoutFrom" url="[action:'signOut',controller:'auth']">
			<input type="submit" value="Logout" />
		</g:form> 
	</content>
	<body>
	<div class="mainBody">
		<nav>
			<ul class="ix">
				<li><a href="<g:createLink controller="Landing" action="index" />" >Startseite</a></li>
				<li><a href="<g:createLink controller="Activity" action="index" />" >Aktivitaeten</a></li>
				<li><a href="<g:createLink controller="Ranking" action="index" />" >Rangliste</a></li>
				<li><a href="<g:createLink controller="Proposal" action="index" />" >Vorschlaege</a></li>
				<li><a href="<g:createLink controller="Statistics" action="index" />" >Statistiken</a></li>
			</ul>
		</nav>
		<div class="pageBody">
			<g:layoutBody />
		</div>
	</div>
	</body>
</g:applyLayout>
