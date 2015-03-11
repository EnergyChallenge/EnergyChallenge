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
				<td>Name</td>
				<td>Punkte</td>
			</tr>
	</thead>
		<tbody>
			
			<g:each in="${users}" var="user">
				<tr onklick="window.location.href = '/';">
					<td>
						${user.getRank()}.
					</td>
					<td>
						${user.getName()}
					</td>
					<td>
						${user.getPoints()}
					</td>

				</tr>
			</g:each>
		</tbody>
	</table>
</body>
</html>
