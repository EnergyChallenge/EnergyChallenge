<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<asset:stylesheet src="table.css" />
	<title>Suche</title>
</head>
<body>
	<g:if test="${query == ''}">
		<g:form action="searchForm" url="[action:'displaySearchResults',controller:'search']">
			<input type="search" name="query" value="${query}" placeholder="Suchen" />
			<button class="fa fa-search"></button>
		</g:form>
	</g:if>
	<g:else>
		<h2>Ihre Suche nach "${query.replaceAll(/_/,"*")}" ergab folgende Treffer:</h2>
		<table class="list">
			<thead>
				<tr>
					<th style="width:10px" title="User oder Team">&nbsp;</th>
					<th>Name</th>
					<th title="Platz in der Rangliste">Platz</th>
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
							<td>
								${res.rankingPosition}
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
							<td>
								${res.rankingPosition}
							</td>
						</g:else>
					</tr>
				</g:each>
			</tbody>
		</table>
	</g:else>
</body>
</html>