<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>EnergyChallenge</title>
<asset:stylesheet src="RankingTable.css" />
</head>
<body>
	<h1>
		Hello from... <em>${profile.getName()})</em>!
	</h1>
	<g:if env="development">
	This is only in development env: ID=${profile.getID()}
	</g:if>
	
	${profile.getAvatar()}
	${profile.getPoints()}
	${profile.getRank()}
	<g:if test="${profile.isTeam()}">
	Members
	</g:if>
	<g:if test="${profile.isUser()}">
	Team
	</g:if>
	<g:each in="${profile.getLinked()}" var="linked">
		${linked.getName()}
	</g:each>
	
	<g:each in="${profile.getRecentCompletedActivitys()}" var="activity">
		${activity.getName()}
	</g:each>
</body>
</html>
