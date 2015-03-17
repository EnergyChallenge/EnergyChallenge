<g:applyLayout name="base">
	<head>
		<asset:stylesheet src="main.css" />
		<g:layoutHead />
	</head>
	<content tag="topRight"> 
	<g:form name="signInForm" url="[action:'signIn',controller:'auth']">
		<input type="hidden" name="targetUri" value="${targetUri}" />
		<table>
			<tbody>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" value="${username}" /></td>
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
	<g:form name="forgotPasswordForm" url="[action:'forgotPassword',controller:'auth']">
		<input type="submit" value="ForgotPassword" />
	</g:form> 
	<g:form name="registerFrom" url="[action:'register',controller:'auth']">
		<input type="submit" value="registrieren" />		
	</g:form> 
	</content>
	<body>
		<div class="mainBody">
			<div class="pageBody">
				<g:layoutBody />
			</div>
		</div>
	</body>
</g:applyLayout>
