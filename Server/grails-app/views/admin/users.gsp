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
						<th>Institut</th>
						<th>Punkte</th>
						<th>Verwaltung</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${users}" var="user">
						<tr>
							<td>
								<a href="${createLink(controller :'profile', action: 'user', id: user.id)}">${user.getName()}</a>
								<br />(<i>${user.getEmail()}</i>)
								<br /><g:if test="${user.getTeam()}">
									<i class="fa fa-users"></i> <a href="${createLink(controller :'profile', action: 'team', id: user.getTeam().getId())}"><i>${user.getTeam().getName()}</i></a>
									</g:if>
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
		<%-- TODO Temp --%>
		<div style="display: none;">
		<h2>TEMP-DATEN</h2>
		<table>
				<thead>
					<tr>
						<th>Benutzer</th>
						<th>E-Mail</th>
						<th>Team</th>
						<th>Institut</th>
						<th>Punkte</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${users}" var="user">
						<tr>
							<td>
								${user.getName()}
							</td>
							<td>
								${user.getEmail()}
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
						</tr>
					</g:each>
				</tbody>
			</table>
			</div>
	</body>
</html>
