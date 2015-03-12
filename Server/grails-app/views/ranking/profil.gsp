<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>EnergyChallenge</title>
<asset:stylesheet src="RankingTable.css" />
</head>
<body>
	<h1>
		Hello from... <em>${profil.getName()})</em>!
	</h1>
	<g:if env="development">
	This is only in development env: ID=${profil.getID()}
	</g:if>
	
	${profil.getAvatar()}
	${profil.getPoints()}
	${profil.getRank()}
	<g:if test="${profil.isTeam()}">
	Members
	</g:if>
	<g:if test="${profil.isUser()}">
	Team
	</g:if>
	<g:each in="${profil.getLinked()}" var="linked">
		${linked.getName()}
	</g:each>
	
	<g:each in="${profil.getRecentCompletedActivitys()}" var="activity">
		${activity.getName()}
	</g:each>
</body>
</html>
