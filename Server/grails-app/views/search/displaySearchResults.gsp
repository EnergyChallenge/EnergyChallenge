<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Suche</title>
	<asset:stylesheet src="search.css"/>
	<script>
		$(document).ready(function() {
			$('#showAll').click(function() {
				$(this).siblings().removeClass('active');
				$(this).addClass('active');
				$('#searchResult tr.user, #searchResult tr.team').show();
				return false;
			});
			$('#showUser').click(function() {
				$(this).siblings().removeClass('active');
				$(this).addClass('active');
				$('#searchResult tr.user').show();
				$('#searchResult tr.team').hide();
				return false;
			});
			$('#showTeams').click(function() {
				$(this).siblings().removeClass('active');
				$(this).addClass('active');
				$('#searchResult tr.user').hide();
				$('#searchResult tr.team').show();
				return false;
			});
		});
	</script>
</head>
<body>
	<g:if test="${query == ''}">
		<g:form action="searchForm" url="[action:'displaySearchResults',controller:'search']">
			<input type="search" name="query" value="${query}" placeholder="Suchen" />
			<button class="fa fa-search"></button>
		</g:form>
	</g:if>
	<g:else>
	
		<h1>Suche</h1>
		<div class="tabbar">
			<a id="showAll" href="#" class="active">Alle</a><a id="showUser" href="#">Benutzer</a><a id="showTeams" href="#">Teams</a>
		</div>
		
		<p>Ihre Suche nach <em>${query.replaceAll(/_/,"*")}</em> ergab folgende Treffer:</h2>
		<table id="searchResult" class="list">
			<thead>
				<tr>
					<th style="width:10px" title="User oder Team">&nbsp;</th>
					<th>Name</th>
					<th class="position" title="Platz in der Rangliste">Platz</th>
				</tr>
			</thead>
			<tbody>
				<g:each status="pos" in="${results}" var="res">
					<tr class="${res.type == 'user' ? 'user' : 'team'}">
						<g:if test="${res.type == 'user'}">
							<td><i class="fa fa-user fa-fw"></i></td>
							<td>
								<a href="${createLink(controller :'profile',
														action: 'user',
														id: res.id)}">
									${res.name}
								</a>
							</td>
							<td class="position">
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
							<td class="position">
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