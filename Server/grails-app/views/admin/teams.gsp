<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<title>EnergyChallenge</title>
	</head>
	<body>
		<h1>Teamverwaltung</h1>
		<p>
			<table class="list">
				<thead>
				<tr>
					<th>Name</th>
					<th>Mitglieder</th>
					<th>Punkte</th>
					<th>Verwaltung</th>
				</tr>
				</thead>
				<tbody>
				<g:each in="${teams}" var="team">
					<tr>
						<td>
							<a href="${createLink(controller :'profile', action: 'team', id: team.getId())}">${team.getName()}</a>
						</td>
						<td>
							<ul>
							<g:each in="${team.getMembers().sort { it.id }}" var="member">
								<i class="fa fa-user"></i> <a href="${createLink(controller :'profile', action: 'user', id: member.getId())}">${member.getName()}</a><br />
							</g:each>
							</ul>
						</td>
						<td>
							${team.getPoints()}
						</td>
						<td class="admin">
							<g:if test="${team.blocked}">
								<a class="button" href="<g:createLink action="unblockTeam" id="${team.id}"/>">aktivieren</a>
							</g:if>
							<g:else>
								<a class="button" href="<g:createLink action="blockTeam" id="${team.id}"/>">blockieren</a>
							</g:else>
							<br />
							<a class="button" href="<g:createLink action="deleteTeam" id="${team.id}"/>">löschen</a>
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
					<th>Name</th>
					<th>Mitglieder</th>
					<th>Punkte</th>
				</tr>
				</thead>
				<tbody>
				<g:each in="${teams}" var="team">
					<tr>
						<td>
							${team.getName()}
						</td>
						<td>
							<ul>
							<g:each in="${team.getMembers().sort { it.id }}" var="member">
								${member.getName()}
							</g:each>
							</ul>
						</td>
						<td>
							${team.getPoints()}
						</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			</div>
	</body>
</html>