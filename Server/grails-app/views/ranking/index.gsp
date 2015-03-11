<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>EnergyChallenge</title>
<asset:stylesheet src="RankingTable.css" />
</head>
<body>
	<h1>
		Hello from... <em>Ranking</em>!
	</h1>
	<table class="ranking">
		<thead>
			<tr>
				<td>Rang</td>
				<td>${typeprefix}name</td>
				<td>Punkte</td>
			</tr>
		</thead>
		<tbody>
			<g:each in="${profils}" var="profil">
			<%-- TODO Link to Profil --%>
				<tr onklick="window.location.href = '/';">
					<td>
						${profil.getRank()}.
					</td>
					<td>
						${profil.getName()} 
					</td>
					<td>
						${profil.getPoints()}
					</td>

				</tr>
			</g:each>
		</tbody>
	</table>
</body>
</html>
