<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>${name}</title>
	</head>
	<body>
		<g:if test="${isCurrent == true}">
			<div>Bearbeiten</div>
		</g:if>
		<div class="head">
			<div class="image">
				<!-- Image here instead of div -->
				<div style="width: 100px; height: 100px; background-color: lime;"></div>
				<h1>${name}</h1>
				<g:if test="${type == 'user' && teamname != ''}">
					<h2>${teamname}</h2>
				</g:if>
			</div>
		</div>
		<div class="left">
			<ul>
				<g:if test="${type == 'user'}">
					<li>${institute}</li>
				</g:if>
				<li>Gesammelte Punkte: 1024</li>
				<li>Position: 78</li><!-- Eventuell Link hier -->
			</ul>
			<!-- Falls Team: -->
			<ul>
				<li>Teammitglied 1</li><!-- Link hier -->
				<li>Teammitglied 2</li><!-- Link hier -->
				<li>Teammitglied 3</li><!-- Link hier -->
			</ul>
		</div>
		<div class="right">
			<ul>
				<li>Letzte Aktivität 1</li><!-- Eventuell User, falls Team -->
				<li>Letzte Aktivität 2</li><!-- Eventuell User, falls Team -->
				<li>Letzte Aktivität 3</li><!-- Eventuell User, falls Team -->
			</ul>
		</div>
		
	</body>
</html>
