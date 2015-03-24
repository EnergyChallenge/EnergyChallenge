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
						${res.getName()}
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>
</body>
</html>