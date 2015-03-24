<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<asset:stylesheet src="table.css" />
	<title>Suche</title>
</head>
<body>
	<h1>Ihre Suche nach "${query}" ergab folgende Treffer:</h1>
	<table class="list">
		<thead>
			<tr>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>
			<g:each status="pos" in="${results}" var="res">
				<tr>
					<td>
						<g:if test="res.type == 'user'">
							<a href="${createLink(controller :'profil',
													action: 'user',
													id: res.id)}">
								${res.name}
							</a>
						</g:if>
						<g:else>
							<a href="${createLink(controller :'profil',
													action: 'team',
													id: res.id)}">
								${res.name}
							</a>
						</g:else>
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>
</body>
</html>