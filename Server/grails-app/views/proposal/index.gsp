<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<asset:stylesheet src="proposal.css" />
	<title>EnergyChallenge</title>
</head>
<body>
	<h1>Energiesparvorschläge</h1>
	<p>
		Reichen Sie neue Vorschläge zum Energie sparen ein! Als Belohnung gibt es für einen
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
	</p>
	<h1>Vorhandene Vorschläge</h1>
	<p>
		<table class="list">
			<thead>
				<tr>
					<th>Beschreibung</th>
					<th>Punkte</th>
					<th>&#216;Bewertung</th>
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
					</tr>
				</g:each>
			</tbody>
		</table>
	</p>
</body>
</html>
