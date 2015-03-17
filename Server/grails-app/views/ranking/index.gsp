<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Rangliste</title>
</head>
<body>
	<h1>Rangliste</h1>
	<div class="tabbar">
		<g:if test="${action == 'users'}">
     		<span class="active">Benutzer</span>
			<a href="${createLink(action:'teams')}">Teams</a>
		</g:if>
		<g:else>
			<a href="${createLink(action:'users')}">Benutzer</a>
			<span class="active">Teams</span>
		</g:else>
	</div>
	<table class="ranking">
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
					<td>
						${pos + 1}.
					</td>
					<td>
						<g:if test="${action == 'users'}">
							<a href="${createLink(controller :'profil',
													action: 'user',
													id: profile.id)}">
								${profile.name}
							</a>
						</g:if>
						<g:else>
							<a href="${createLink(controller :'profil',
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
</body>
</html>
