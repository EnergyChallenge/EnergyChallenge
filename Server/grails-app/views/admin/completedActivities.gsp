<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>EnergyChallenge</title>
		<asset:stylesheet src="table.css"/>
	</head>
	<body>
		<h1>Aktivitätenliste von <i>${fullName}</i></h1>
		<table class="list" >
			<thead>
				<tr>
					<th>Beschreibung</th>
					<th>Punkte</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${completedActivities}" var="ca"> 
				<tr>
					<td>${ca.activity.description}</td>
					<td>${ca.activity.points}</td>
					<td><a class="button" href="<g:createLink action="deleteCompletedActivity" params="[userid: userid, completedActivityId: ca.id]"/>">löschen</a></td>
				</tr>
				</g:each>
			</tbody>
		</table>
		<g:if test="${completedActivities.size() > 0}" var="ca">
			<p style="text-align:center">
				<a class="button" href="<g:createLink action="deleteAllCompletedActivities" params="[userid: userid]"/>">Alle erledigten Aktivitäten von <i>${fullName}</i> löschen</a></td>
			</p>
		</g:if>
	</body>
</html>