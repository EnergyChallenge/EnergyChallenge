<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>EnergyChallenge</title>
	</head>
	<body>
		<h1>Benutzerverwaltung</h1>
		<p>
			<table class="list">
				<thead>
					<tr>
						<th>Benutzer</th>
						<th>Team</th>
						<th>Institut</th>
						<th>Punkte</th>
						<th>Verwaltung</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${users}" var="user">
						<%-- TODO Link to Users --%>
						<tr>
							<td>
								${user.getName()}<br />(${user.getEmail()})
							</td>
							<td>
								<g:if test="${user.getTeam()}">
									${user.getTeam().getName()}
								</g:if>
							</td>
							<td>
								${user.getInstitute().getName()}
							</td>
				
							<td>
								${user.getPoints()}
							</td>
							<td class="admin">
								<a class="button" href="<g:createLink action="completedActivities" params="[userid:user.id]"/>">Aktivitäten</a>
								
								<g:if test="${user.blocked}">
									<a class="button" href="<g:createLink action="unblockUser" id="${user.id}"/>">aktivieren</a>
								</g:if>
								<g:else>
									<a class="button" href="<g:createLink action="blockUser" id="${user.id}"/>">blockieren</a>
								</g:else>
								
								<a class="button" href="<g:createLink action="deleteUser" id="${user.id}"/>">löschen</a>
							</td>
						</tr>
					</g:each>
				</tbody>
			</table>   
		</p>
	</body>
</html>
