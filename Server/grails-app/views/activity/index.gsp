<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>EnergyChallenge</title>
	</head>
	<body>
	<style>
	#td {margin-left: 30px}
	</style>
		<h1>Aktivitätenliste</h1>
		<table class="activity" >
			<thead>
			<tr>
				<th>Beschreibung</th>
				<th>Punkte</th>
				<th>Erledigen</th>
				<th>Favorisieren</th>
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
					<g:actionSubmitImage value="Aktivität erledigen" action="completeActivity"
                     src="${resource(dir: 'images', file: 'complete.png')}"/>	<!-- actionsubmitImage creates an imagebutton for the method completeActivity -->
					</g:form>
				</g:if>
				<g:else>${act.countdown}</g:else>
				</td>
				<td>
				<g:if test="${!act.favorite}" >
					<g:form name="addToFavoritesForm" url="[action:'addToFavorites',controller:'activity',id: act.activity.getId()]">
					<g:actionSubmitImage value="favorisieren" action="addToFavorites"
                     src="${resource(dir: 'images', file: 'nofavorite.png')}"/> <!-- actionsubmitImage creates an imagebutton for the method addToFavorites, the image shows an empty heartcontainer, after pressing the button the heartcontainer becomes filled up -->
					</g:form>
				</g:if>
				<g:else>
					<g:form name="removeFromFavoritesForm" url="[action:'removeFromFavorites',controller:'activity',id: act.activity.getId()]">
					<g:actionSubmitImage value="defavorisieren" action="removeFromFavorites"
                     src="${resource(dir: 'images', file: 'favorite1.png')}"/> <!-- actionsubmitImage creates an imagebutton for the method removeFromFavorites, the image shows a filled heartcontainer, after pressing the button the heartcontainer becomes empty -->
					</g:form>
				</g:else>
				</td>
			</tr>
			</g:each>
			</tbody>
		</table>
	</body>
</html>
