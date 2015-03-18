<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>EnergyChallenge</title>
	</head>
	<body>
		<h1>Aktivitätenliste</h1>
		<table class="activity" >
			<thead>
			<tr>
				<th>Beschreibung</th>
				<th>Punkte</th>
			</tr>
			</thead>
			<tbody>
			<g:each in="${activities}" var="activity"> 
			<tr>
				<td>${activity.description}</td>
				<td>${activity.points}</td>
				<td>
					<g:form name="completeActivityForm" url="[action:'completeActivity',controller:'activity',id: activity.getId()]">
					<input type="submit" value="Aktivität erledigen" />
					</g:form>
				</td>
				<td>
					<g:form name="addToFavoritesForm" url="[action:'addToFavorites',controller:'activity',id: activity.getId()]">
					<input type="submit" value="favorisieren" />
					</g:form>
				</td>
			</tr>
			</g:each>
			</tbody>
		</table>
	</body>
</html>
