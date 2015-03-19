<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>EnergyChallenge</title>
<asset:stylesheet src="table.css"/>
</head>
<body>
	<h1>${proposal.getDescription()}</h1><br>
	<h2>Punkte: ${proposal.getPoints()}</h2>
	<g:form action="addComment">
                <input type="hidden" name="id" value="${id}" />
		<table class="table">
			<tbody>
				<tr>
					<td>Bewertung:</td>
					<td><select name="rating" >
                                                <option value="1">1 Stern</option>
                                                <option value="2">2 Sterne</option>
                                                <option value="3">3 Sterne</option>
                                                <option value="4">4 Sterne</option>
                                                <option value="5">5 Sterne</option>
                                        </select></td>
				</tr>
				<tr>
					<td>Kommentar:</td>
					<td><input type="text" name="text" value="" /></td>
				</tr>
				<tr>
					<td />
					<td><input type="submit" value="Kommentieren " /></td>
				</tr>
			</tbody>
		</table>
	</g:form>
	<table class="list">
		<thead>
			<tr>
				<td>Kommentar</td>
				<td>Bewertung</td>
			</tr>
		</thead>
		<tbody>
			<g:each in="${comments}" var="comment">
				<%-- TODO Link to Proposal --%>
				<tr>
					<td>
                                                ${comment.getText()}
					</td>
					<td>
						${comment.getRating()}
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>
</body>
</html>
