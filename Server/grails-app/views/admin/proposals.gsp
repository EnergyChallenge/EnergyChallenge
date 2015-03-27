<!DOCTYPE html>
<html>

	<table class="TFtable">
		<tr><td>Text</td><td>Text</td><td>Text</td></tr>
		<tr><td>Text</td><td>Text</td><td>Text</td></tr>
		<tr><td>Text</td><td>Text</td><td>Text</td></tr>
		<tr><td>Text</td><td>Text</td><td>Text</td></tr>
	</table>



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
					<th>&#216;&nbsp;Bewertung</th>
					<th>Verwaltung</th>
				</tr>
				</thead>
				<tbody>
				<g:each in="${proposals}" var="proposal">
					<tr>
						<td>
							${proposal.getDescription()}
							<table style="padding:0px">
								<tbody>
									<tr style="background: transparent;">
										<td style="padding:0px"><i class="fa fa-comments"></i></td>
										<td style="padding:0px"><a href="${createLink(controller: 'proposal', action: 'view', id: proposal.id)}">Kommentare ansehen</a></td>
									</tr>
									<tr style="background: transparent;">
										<td style="padding:0px"><i class="fa fa-user"></i></td>
										<td style="padding:0px"><a href="${createLink(controller :'profile', action: 'user', id: proposal.getAuthor().getId())}">${proposal.getAuthor().getName()}</a></td>
									</tr>
									<tr style="background: transparent;">
										<td style="padding:0px"><i class="fa fa-calendar"></i></td>
										<td style="padding:0px">${fmt.print(proposal.getDateCreated())}</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td>
							${proposal.getPoints()}
						</td>
						<td style="padding: 7px 5px; text-align: center;">
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
							<a class="button" href="<g:createLink action="editActivity" params="[proposalId: "${proposal.id}", description: "${proposal.getDescription()}", points: "${proposal.getPoints()}"]"/>">umwandeln</a><br />
							<a class="button" href="<g:createLink action="deleteProposal" params="[proposalId: "${proposal.id}"]"/>">löschen</a>
						</td>
					</tr>
				</g:each>
				</tbody>
			</table>
		</p>
	</body>
</html>
