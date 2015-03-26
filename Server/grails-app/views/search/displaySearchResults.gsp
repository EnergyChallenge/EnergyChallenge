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
				<th style="width:10px"></th>
				<th>Name</th>
			</tr>
		</thead>
		<tbody>
			<g:each status="pos" in="${results}" var="res">
				<tr>
					<g:if test="${res.type == 'user'}">
						<td><i class="fa fa-user fa-fw"></i></td>
						<td>
							<a href="${createLink(controller :'profile',
													action: 'user',
													id: res.id)}">
								${res.name}
							</a>
						</td>
					</g:if>
					<g:else>
						<td><i class="fa fa-users fa-fw"></i></td>
						<td>
							<a href="${createLink(controller :'profile',
													action: 'team',
													id: res.id)}">
								${res.name}
							</a>
						</td>
					</g:else>
				</tr>
			</g:each>
		</tbody>
	</table>
</body>
</html>