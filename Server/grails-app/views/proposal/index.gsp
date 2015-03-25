<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>EnergyChallenge</title>
<asset:stylesheet src="table.css"/>
</head>
<body>
	<h1>Energiesparvorschläge</h1>
	<p>
		Reichen Sie neue Vorschläge zum Erergiesparen ein! Als Belohnung gibt es für einen
		angenommen Vorschlag <strong>2 Punkte</strong>.
	<g:form action="add">
		<table class="table">
			<tbody>
				<tr>
					<td>Beschreibung:</td>
					<td><input type="text" name="description" value="" /></td>
				</tr>
				<tr>
					<td>Punkte:</td>
					<td><select name="points" >
                                                <option value="1">1 Punkt</option>
                                                <option value="2">2 Punkte</option>
                                                <option value="3">3 Punkte</option>
                                                <option value="4">4 Punkte</option>
                                                <option value="5">5 Punkte</option>
                                        </select></td>
				</tr>
				<tr>
					<td />
					<td><input type="submit" value="Einreichen" /></td>
				</tr>
			</tbody>
		</table>
	</g:form>

	<table class="list">
		<thead>
			<tr>
				<td>
					Vorschlag Beschreibung
				</td>
				<td>Punkte</td>
				<td>Bewertung</td>
			</tr>
		</thead>
		<tbody>
			<g:each in="${proposals}" var="proposal">
				<tr>
					<td>
                                                <a href="${createLink( action: 'view', id: proposal.id)}">
                                                                ${proposal.getDescription()}
                                                </a>
					</td>
					<td>
						${proposal.getPoints()}
					</td>
					<td>
						${proposal.getRating()}
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>
</body>
</html>
