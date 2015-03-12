<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>EnergyChallenge</title>
	</head>
	<body>
<g:if test="${user.avatar}">
  <img class="avatar" src="${createLink(controller:'user', action:'avatar_image', id:user.ident())}" />
</g:if>

</body>
</html>
