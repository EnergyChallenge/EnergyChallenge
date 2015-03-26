<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>EnergyChallenge</title>
	</head>
	<body>
		<h1>Aktivitätsverwaltung</h1>
		<p>
			<a class="button" href="${createLink(action: 'editActivity')}">neue Aktivität erstellen</a>
		</p>
		<p>
			<table class="list">
				<thead>
					<tr>
						<th>Beschreibung</th>
						<th>Punkte</th>
						<th>Dauer(Std.)</th>
						<th>Verwaltung</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${activities}" var="activity">
						<%-- TODO Link to Activities --%>
						<tr>
							<td>
								${activity.description}
							</td>
							<td>
								${activity.points}
							</td>
							<td>
								${activity.duration}
							</td>
							<td class="admin">
								<a class="button" href="<g:createLink action="editActivity" params="[activityId: "${activity.id}", description: "${activity.description}", points: "${activity.points}"]"/>">bearbeiten</a> 
								<a class="button" href="<g:createLink action="deleteActivity" params="[activityId: "${activity.id}"]"/>">loeschen</a> 
							</td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</p>
	</body>
</html>
