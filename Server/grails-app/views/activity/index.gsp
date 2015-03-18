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
			<g:each in="${activities}" var="act"> 
			<tr>
				<td>${act.activity.description}</td>
				<td>${act.activity.points}</td>
				<td>
				<g:if test="${act.executable}" >
					<g:form name="completeActivityForm" url="[action:'completeActivity',controller:'activity',id: act.activity.getId()]">
						<input type="submit" value="Aktivität erledigen"/>
					</g:form>
				</g:if>
				<g:else>${act.countdown}</g:else>
				</td>
				<td>
					<g:form name="addToFavoritesForm" url="[action:'addToFavorites',controller:'activity',id: act.activity.getId()]">
					<input type="submit" value="favorisieren" />
					</g:form>
				</td>
			</tr>
			</g:each>
			</tbody>
		</table>
	</body>
</html>
