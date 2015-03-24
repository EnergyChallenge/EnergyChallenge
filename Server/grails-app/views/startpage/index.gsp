<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="base"/>
		<title>EnergyChallenge</title>
	</head>
	<body>
		<div id="left">
		<style>
		#login {margin-left:30px; margin-top:30px;color:black;}
		#register {margin-left:30px; margin-top:30px;color:black;}
		</style>
			<div id="login" >
				<g:form name="signInForm" url="[action:'signIn',controller:'auth']">
					<input type="hidden" name="targetUri" value="${targetUri}" />
					<table>
						<tbody>
							<tr>
								<td>E-Mail:</td>
								<td><input type="text" name="email" value="${username}" /></td>
							</tr>
							<tr>
								<td>Passwort:</td>
								<td><input type="password" name="password" value="" /></td>
							</tr>
							<tr>
								<td>Angemeldet bleiben</td>
								<td><g:checkBox name="rememberMe" value="${rememberMe}" /></td>
							</tr>
							<tr>
								<td />
								<td><input type="submit" value="Anmelden" /></td>
							</tr>
						</tbody>
					</table>
				</g:form> 
				<g:form name="forgotPasswordForm" url="[action:'forgotPassword',controller:'auth']">
					<input type="submit" value="Passwort vergessen" />
				</g:form>
			</div>
			<div id="register" >
				<g:form name="registerFrom" url="[action:'register',controller:'auth']">
					<input type="submit" value="Jetzt registrieren!" />		
				</g:form>
			</div>
		</div>
		<h1>EnergyChallenge</h1>
		<p style="text-align:justify;">
			Die EnergyChallenge richtet sich an alle Studierende
			und Beschäftigte der CAU, die Spaß daran haben
			Energiesparmöglichkeiten am Arbeitsplatz auszutesten,
			hierfür Punkte zu sammeln und Preise zu gewinnen. Die
			EnergyChallenge ist ein Teil der Energiesparkampagne,
			welche von der CAU mit Unterstützung der EKSH
			durchgeführt, um energiesparendes Verhalten am
			Arbeitsplatz zu fördern.
		</p>
		<p style="text-align:justify;">
			Die Teilnahme ist einfach: online anmelden, eine Gruppe
			aussuchen (dies können Kollegen aus der Arbeitsgruppe
			sein oder auch willkürliche Zusammensetzungen) und die
			energiesparenden Aktivitäten regelmäßig in der
			Aktivitätenliste abhaken. Die Punkte werden dann
			automatisch dem Nutzerkonto zugeschrieben und nach vier
			Wochen werden eine Gewinnergruppe und die besten
			Einzelgewinner im Rahmen einer feierlichen Siegerehrung
			gekürt.
		</p>
		<h1>Beliebteste Aktivitäten:</h1>
		<p>${foo}</p>
		<table class="activityTeaser">
			<tbody>
				<g:each in="${teaser}" var="activity">
					<tr>
						<td>${activity.DESCRIPTION}</td>
						<td>${activity.N} Mal ausgeführt</td>
					</tr>
				</g:each>
			</tbody>
		</table>
		<p><g:link controller="statistics" action="index">Alle Statistiken anzeigen</g:link></p>
	</body>
</html>