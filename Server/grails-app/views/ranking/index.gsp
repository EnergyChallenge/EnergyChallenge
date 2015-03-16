<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>EnergyChallenge</title>
</head>
<body>
	<h1>Rangliste</h1>
	<div class="tabbar">
		<a href="${createLink(action:'users')}">Benutzer</a>
		<a href="${createLink(action:'teams')}">Teams</a>
	</div>
	<table class="ranking">
		<thead>
			<tr>
				<th>Rang</th>
				<th>${tableTitle}</th>
				<th>Punkte</th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${ranking}" var="profil">
				<tr>
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
