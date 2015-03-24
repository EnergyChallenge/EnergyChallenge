<!-- 

TODO

Not used anymore! Delete!

 -->


<!DOCTYPE html>
<html><head>
		<title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
		<meta name="layout" content="main">
		<g:if env="development"><asset:stylesheet src="RankingTable.css"/></g:if>
	</head>
	<body>
	<title> "Profile von <em>${profile.getName()})</em>!" </title>
		
	<g:if env="development">
	This is only in development env: ID=${profile.getID()}
	</g:if>
	<p>
	${profile.getAvatar()}
	${profile.getPoints()}
	${profile.getRecentCompletedActivitys()}
	${profile.getRank()}
	</p>
	
	<g:if test="${profile.isUser()}">
	Team
	</g:if>
	<g:if test="${profile.isTeam()}">
	Members
	</g:if>
	
	<g:each> in="${profile.getRecentCompletedActivitys()}" var="activity">
		${activity.getName()}
	</g:each>
	</body>
</html>