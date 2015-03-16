<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>EnergyChallenge</title>
</head>
<body>
	<h1>Rangliste</h1>
	<div class="tabbar">
		<g:if test="${action == 'users'}">
     		<span class="active">Benutzer</span>
			<a href="${createLink(action:'teams')}">Teams</a>
		</g:if>
		<g:else>
			<a href="${createLink(action:'users')}">Benutzer</a>
			<span class="active">Teams</span>
		</g:else>
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
			<g:each status="pos" in="${ranking}" var="profil">
				<tr>
					<td>
						${pos}.
					</td>
					<td>
						${profil.name} 
					</td>
					<td>
						${profil.points}
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>
</body>
</html>
