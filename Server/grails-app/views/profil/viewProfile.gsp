<!DOCTYPE html>
<html><head>
		<title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
		<meta name="layout" content="main">
		<g:if env="development"><asset:stylesheet src="profile.css"/></g:if>
	</head>
	<body>
	<title> "Profil von ID=${profil.getID()}" </title>
		
		
	<p>
	${profil.getAvatar()}
	${profil.getPoints()}
	</p>
	
	
	<g:each> in="${profil.getRecentCompletedActivitys()}" var="activity">
		${activity.getName()}
	</g:each>
	</body>
</html>