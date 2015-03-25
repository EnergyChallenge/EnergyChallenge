<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<title>EnergyChallenge</title>
	</head>
	<body>
		<h1>Vorschlagsverwaltung</h1>
		<p>
			<table class="list">
				<thead>
				<tr>
					<th>Beschreibung</th>
					<th>Punkte</th>
					<th>Autor</td>
					<th>Erstellungsdatum</th>
					<th>&#216; Bewertung</th>
					<th>Verwaltung</th>
				</tr>
				</thead>
				<tbody>
				<g:each in="${proposals}" var="proposal">
					<%-- TODO Link to Proposals --%>
					<tr>
						<td>
							${proposal.getDescription()}
						</td>
						<td>
							${proposal.getPoints()}
						</td>
						<td>
							${proposal.getAuthor().getName()}
						</td>
						<td>
							${fmt.print(proposal.getDateCreated())}
						</td>
						<td>
							${proposal.getRating()}
						</td>
						<td class="admin">
							<a class="button" href="<g:createLink action="editActivity" params="[proposalId: "${proposal.id}", description: "${proposal.getDescription()}", points: "${proposal.getPoints()}"]"/>">umwandeln</a>
							<a class="button" href="<g:createLink action="deleteProposal" params="[proposalId: "${proposal.id}"]"/>">löschen</a>
						</td>
					</tr>
				</g:each>
				</tbody>
			</table>
		</p>
	</body>
</html>
