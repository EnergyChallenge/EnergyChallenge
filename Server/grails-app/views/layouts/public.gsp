<g:applyLayout name="base">
	<head>
	<asset:stylesheet src="main.css" />
	<asset:stylesheet src="navigation.css" />
	<g:layoutHead />

	</head>
	<content tag="topRight"> 
	<g:form name="signInForm" url="[action:'signIn',controller:'auth']">
		<input type="hidden" name="targetUri" value="${targetUri}" />
		<table>
			<tbody>
				<tr>
					<td>Username:</td>
					<td><input type="text" name="username" value="${username}" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" value="" /></td>
				</tr>
				<tr>
					<td>Remember me?:</td>
					<td><g:checkBox name="rememberMe" value="${rememberMe}" /></td>
				</tr>
				<tr>
					<td />
					<td><input type="submit" value="Sign in" /></td>
				</tr>
			</tbody>
		</table>
	</g:form> 
	<g:form name="signInForm" url="[action:'forgotPassword',controller:'authorisation']">
		<input type="submit" value="ForgotPassword" />
	</g:form> 
	<g:form name="signInForm" url="[action:'register',controller:'authorisation']">
		<input type="submit" value="registrieren" />		
	</g:form> 
	</content>
	<body>
		<div class="mainBody">
			<nav>
				<ul class="ix">
					<li><a
						href="<g:createLink controller="Landing" action="index" />">Startseite</a></li>
					<li><a
						href="<g:createLink controller="Activity" action="index" />">Aktivitaeten</a></li>
					<li><a
						href="<g:createLink controller="Ranking" action="index" />">Rangliste</a></li>
					<li><a
						href="<g:createLink controller="Proposal" action="index" />">Vorschlaege</a></li>
					<li><a
						href="<g:createLink controller="Statistics" action="index" />">Statistiken</a></li>
				</ul>
			</nav>
			<div class="pageBody">
				<g:layoutBody />
			</div>
		</div>
	</body>
</g:applyLayout>
