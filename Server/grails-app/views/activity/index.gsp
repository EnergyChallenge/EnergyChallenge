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
		<p>
			<table class="list" >
				<thead>
				<tr>
					<th colspan="2">Beschreibung</th>
					<th>Punkte</th>
					<th></th>
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
						</g:if>
						<g:else>
							<a href="${createLink(controller:'activity', action:'removeFromFavorites', id: act.activity.getId())}" class="tooltipLeft">
								<i class="fa fa-star"></i>
								<span>aus Favoriten entfernen</span>
							</a>
						</g:else>
					</td>
					<td>${act.activity.description}</td>
					<td style="text-align:center">${act.activity.points}</td>
					<td style="width:90px; text-align:center">
						<g:if test="${act.executable}" >
							<a class="button" href="${createLink(controller:'activity', action:'completeActivity', id: act.activity.getId())}">
								erledigen
							</a>
						</g:if>
						<g:else>
							<i class="fa fa-lock"></i>${act.countdown}
						</g:else>
					</td>
				</tr>
				</g:each>
				</tbody>
			</table>
		</p>
	</body>
</html>
