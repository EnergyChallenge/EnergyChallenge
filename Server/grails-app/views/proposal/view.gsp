<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<asset:stylesheet src="proposal.css" />
	<title>EnergyChallenge</title>
</head>
<body>
	<h1>${proposal.getDescription()}</h1><br>
	<h2>Punkte: ${proposal.getPoints()}</h2>
	<p>
		<g:form action="addComment">
					<input type="hidden" name="id" value="${id}" />
			<table class="table">
				<tbody>
					<tr>
						<td>Bewertung:</td>
						<td><div class="rating">
    <input type="radio" id="star5" name="rating" value="5" /><label for="star5" ></label>
    <input type="radio" id="star4" name="rating" value="4" /><label for="star4" ></label>
    <input type="radio" id="star3" name="rating" value="3" /><label for="star3" ></label>
    <input type="radio" id="star2" name="rating" value="2" /><label for="star2" ></label>
    <input type="radio" id="star1" name="rating" value="1" /><label for="star1" ></label>
</div></td>
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
	</p>
	<p>
		<table class="list">
			<thead>
				<tr>
					<th>Kommentar</th>
					<th>Bewertung</th>
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
	</p>
</body>
</html>
