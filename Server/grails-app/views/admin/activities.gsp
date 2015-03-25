<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>EnergyChallenge</title>
	</head>
	<body>
		<h1>Aktivitätsverwaltung</h1>
		<p>
			<table class="list">
				<thead>
					<tr>
						<th>Beschreibung</th>
						<th>Punkte</th>
						<th>Dauer</th>
						<th>Verwaltung</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${activities}" var="activity">
						<%-- TODO Link to Activities --%>
						<tr>
							<td>
								${activity.getDescription()}
							</td>
							<td>
								${activity.getPoints()}
							</td>
							<td>
								${activity.getDuration()}
							</td>
							<td class="admin">
								<a class="button" href="<g:createLink action="editActivity" params="[activityId: "${activity.id}", description: "${activity.getDescription()}", points: "${activity.getPoints()}"]"/>">bearbeiten</a> 
								<a class="button" href="<g:createLink action="deleteActivity" params="[activityId: "${activity.id}"]"/>">loeschen</a> 
							</td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</p>
	</body>
</html>
