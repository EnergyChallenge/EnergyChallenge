<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>EnergyChallenge</title>
</head>
<body>
	<h1>
		Hello from... <em>Proposal</em>!
	</h1>
	<g:form action="add">
		<table>
			<tbody>
				<tr>
					<td>Beschreibung:</td>
					<td><input type="text" name="description" value="" /></td>
				</tr>
				<tr>
					<td>Punkte:</td>
					<td><input type="number" name="points" value="" /></td>
				</tr>
				<tr>
					<td />
					<td><input type="submit" value="add" /></td>
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
			</tr>
		</thead>
		<tbody>
			<g:each in="${proposals}" var="proposal">
				<%-- TODO Link to Proposal --%>
				<tr>
					<td>
						${proposal.getDescription()}
					</td>
					<td>
						${profil.getRating()}
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>
</body>
</html>
