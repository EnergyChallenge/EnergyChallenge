<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<asset:stylesheet src="activity.css" />
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
				<th></th>
				<th>Beschreibung</th>
				<th>Punkte</th>
				<th>Erledigen</th>
			</tr>
			</thead>
			<tbody>
			<g:each in="${activities}" var="act"> 
			<tr>
				<td>
				<g:if test="${!act.favorite}" >
					<a href="${createLink(controller:'activity', action:'addToFavorites', id: act.activity.getId())}" class="tooltipLeft">
						<i class="fa fa-star-o"></i>
						<span>zu Favoriten hinzufügen</span>
					</a>
				<%-- OLD
					<g:form name="addToFavoritesForm" url="[action:'addToFavorites',controller:'activity',id: act.activity.getId()]">
					<g:actionSubmitImage value="favorisieren" action="addToFavorites"
                     src="${resource(dir: 'images', file: 'nofavorite.png')}"/> <!-- actionsubmitImage creates an imagebutton for the method addToFavorites, the image shows an empty heartcontainer, after pressing the button the heartcontainer becomes filled up -->
					</g:form>
					--%>
				</g:if>
				<g:else>
					<a href="${createLink(controller:'activity', action:'removeFromFavorites', id: act.activity.getId())}" class="tooltipLeft">
						<i class="fa fa-star"></i>
						<span>aus Favoriten entfernen</span>
					</a>
				<%-- OLD
					<g:form name="removeFromFavoritesForm" url="[action:'removeFromFavorites',controller:'activity',id: act.activity.getId()]">
					<g:actionSubmitImage value="defavorisieren" action="removeFromFavorites"
                     src="${resource(dir: 'images', file: 'favorite1.png')}"/> <!-- actionsubmitImage creates an imagebutton for the method removeFromFavorites, the image shows a filled heartcontainer, after pressing the button the heartcontainer becomes empty -->
					</g:form> --%>
				</g:else>
				</td>
				<td>${act.activity.description}</td>
				<td>${act.activity.points}</td>
				<td>
				<g:if test="${act.executable}" >
					<a href="${createLink(controller:'activity', action:'completeActivity', id: act.activity.getId())}" class="tooltipRight">
						<i class="fa fa-square-o"></i>
						<span>jetzt erledigen</span>
					</a>
					<%--
					<g:form name="completeActivityForm" url="[action:'completeActivity',controller:'activity',id: act.activity.getId()]">
					<g:actionSubmitImage value="Aktivität erledigen" action="completeActivity"
                     src="${resource(dir: 'images', file: 'complete.png')}"/>	<!-- actionsubmitImage creates an imagebutton for the method completeActivity -->
					</g:form>
					--%>
				</g:if>
				<g:else>${act.countdown}</g:else>
				</td>
			</tr>
			</g:each>
			</tbody>
		</table>
	</body>
</html>
