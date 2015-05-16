<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="layout" content="public" />
</head>
<body>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  	<div id="center">
		<%-- TODO: create an include of the login form --%>
			  <div id="login" >
				<g:form id="signInForm" name="signInForm" url="[action:'signIn',controller:'auth']">
					<input type="hidden" name="targetUri" value="${targetUri}" />
					<table>
						<tbody>
							<tr>
								<td class="left">E-Mail:</td>
								<td class="right">
									<input id="signInFormEmail" type="email" name="email" value="${username}" />
								</td>
							</tr>
							<tr>
								<td class="left">Passwort:</td>
								<td class="right">
									<input id="signInFormPassword"  type="password" name="password" value="" />
								</td>
							</tr>
							<tr>
								<td colspan="2" class="both">
									<g:checkBox name="rememberMe" value="${rememberMe}" />
									<label for="rememberMe">Angemeldet bleiben</label>
								</td>
							</tr>
						</tbody>
					</table>
					<input type="submit" value="Anmelden" />
				</g:form> 
				<g:form name="forgotPasswordForm" url="[action:'forgotPassword',controller:'auth']">
					<input type="submit" value="Passwort vergessen" />
				</g:form>
				<g:javascript>
					$(function() {
						$('#signInFormEmail').focus().change(function() {
						  <%-- TODO: check validity --%>
						});
						$("#signInForm").submit(function (e) {
							if($('#signInFormPassword').val()==""){
								  $('#signInFormPassword').focus();
								  return false;
							}
						});
					});
				</g:javascript>
			</div>
			<div id="register" >
				<g:form name="registerFrom" url="[action:'register',controller:'auth']">
					<input type="submit" value="Jetzt registrieren!" />		
				</g:form>
			</div>
		<%-- TODO: create an include of the login form --%>
	</div>
</body>
</html>
