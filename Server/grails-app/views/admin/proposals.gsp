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
					<th>&#216;&nbsp;Bewertung</th>
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
							<g:each var="i" in="${ (0..<5)}">
								<g:if test="${proposal.getRating() <= i}">
									<i class="fa fa-star-o"></i>
								</g:if>
								<g:elseif test="${proposal.getRating() <= i + 0.5}">
									<i class="fa fa-star-half-o"></i>
								</g:elseif>
								<g:else>
									<i class="fa fa-star"></i>
								</g:else>
							</g:each>
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
