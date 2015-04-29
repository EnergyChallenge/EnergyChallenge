<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Rangliste</title>
	<asset:stylesheet src="ranking.css"/>
</head>
<body>
	<h1>Rangliste</h1>
	<div class="tabbar">
		<g:if test="${action == 'users'}">
     		<span class="active">Benutzer</span><a href="${createLink(action:'teams')}">Teams</a>
		</g:if>
		<g:else>
			<a href="${createLink(action:'users')}">Benutzer</a><span class="active">Teams</span>
		</g:else>
	</div>
	<p>
		<table class="list">
			<thead>
				<tr>
					<th>Rang</th>
					<th>${tableTitle}</th>
					<th>Punkte</th>
				</tr>
			</thead>
			<tbody>
				<g:each status="pos" in="${ranking}" var="profile">
					<tr>
						<td class="numeration">
							${profile.rank}.
						</td>
						<td>
							<g:if test="${action == 'users'}">
								<a href="${createLink(controller :'profile',
														action: 'user',
														id: profile.id)}">
									${profile.name}
								</a>
							</g:if>
							<g:else>
								<a href="${createLink(controller :'profile',
														action: 'team',
														id: profile.id)}">
									${profile.name}
								</a>
							</g:else>
						</td>
						<td>
							${profile.points}
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</p>
	<g:paginate controller="ranking" action="${action}" total="${count}" max="50" />
</body>
</html>
