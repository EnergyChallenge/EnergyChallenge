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
		Reichen Sie neue Vorschläge zum Energiesparen ein! Als Belohnung gibt es
		für einen angenommen Vorschlag <strong>2 Punkte</strong>.
	<p>
		<em>
			Es werden nur Vorschläge übernommen die an der CAU und im Rahmen der
			EnergyChallenge umgesetzt werden können. Alle anderen Vorschläge
			werden automatisch gelöscht. Damit Ideen gewertet und freigeschaltet
			werden könne, müssen Sie im Zeitraum der EnergyChallenge durchgesetzt
			werden und das Individuelle Verhalten betreffen.
		</em>
	</p>
	<g:form action="add" class="addproposal">
		<div class="proposaltext">
			<input type="text" name="description" value="" placeholder="Ihr Vorschlag..." />
		</div>
		<div class="proposalpoints">
			Punktevorschlag:
			<select name="points" >
				<option value="1">1 Punkt</option>
				<option value="2">2 Punkte</option>
				<option value="3">3 Punkte</option>
				<option value="4">4 Punkte</option>
				<option value="5">5 Punkte</option>
			</select>
		</div>
		<div class="proposalsubmit">
			<input type="submit" value="Vorschlag einreichen" />
		</div>
		<div class="clear"></div>
	</g:form>
	<h1>Vorhandene Vorschläge</h1>
	<p>
		<table class="list">
			<thead>
				<tr>
					<th>Beschreibung</th>
					<th>Punkte</th>
					<th>&#216;&nbsp;Bewertung</th>
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
						<td style="width: 6em;">
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
		<div class="paginate">
			<g:paginate controller="proposal" action="index" total="${count}" max="${paginateMax}" />
		</div
	</p>
</body>
</html>
