<!DOCTYPE html>
<html><head>
		<title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
		<meta name="layout" content="main">
		<g:if env="development"><asset:stylesheet src="main.css"/></g:if>
	</head>
	<body>
	<title> "Profil von <em>${profil.getName()})</em>!" </title>
		
	<g:if env="development">
	This is only in development env: ID=${profil.getID()}
	</g:if>
	<p>
	${profil.getAvatar()}
	${profil.getPoints()}
	</p>
	
	<g:if test="${profil.isTeam()}">
	Members
	</g:if>
	<g:if test="${profil.isUser()}">
	Team
	</g:if>
	
	<g:each in="${profil.getLinked()}" var="linked">
		${linked.getName()}
	</g:each>
	
	<g:each> in="${profil.getRecentCompletedActivitys()}" var="activity">
		${activity.getName()}
	</g:each>
	</body>
</html>