<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="public"/>
		<title>EnergyChallenge</title>
	</head>
	<body>
		<div id="right">
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
				<script>
					$(function() {
						$('#signInFormEmail').focus().change(function() {
						  // TODO: check validity
						});
						$("#signInForm").submit(function (e) {
							if($('#signInFormPassword').val()==""){
								  $('#signInFormPassword').focus();
								  return false;
							}
						});
					});
				</script>
			</div>
			<div id="register" >
				<g:form name="registerFrom" url="[action:'register',controller:'auth']">
					<input type="submit" value="Jetzt registrieren!" />		
				</g:form>
			</div>
		</div>
		<div id="left">
			<h1>EnergyChallenge</h1>
			<p>
				Die EnergyChallenge richtet sich an alle Studierende
				und Beschäftigte der CAU, die Spaß daran haben
				Energiesparmöglichkeiten am Arbeitsplatz auszutesten,
				hierfür Punkte zu sammeln und Preise zu gewinnen. Die
				EnergyChallenge ist ein Teil der Energiesparkampagne,
				welche von der CAU mit Unterstützung der EKSH
				durchgeführt wird, um energiesparendes Verhalten am
				Arbeitsplatz zu fördern.
			</p>
			<p>
				Die Teilnahme ist einfach: Online anmelden, eine Gruppe
				aussuchen (dies können Kollegen aus der Arbeitsgruppe
				sein oder auch willkürliche Zusammensetzungen) und die
				energiesparenden Aktivitäten regelmäßig in der
				Aktivitätenliste abhaken. Die Punkte werden dann
				automatisch dem Benutzerkonto zugeschrieben und nach vier
				Wochen werden eine Gewinnergruppe und die besten
				Einzelgewinner im Rahmen einer feierlichen Siegerehrung
				gekürt.
			</p>
			<p>
				Damit sie ihre Aktivitäten auch komfortabel unterwegs erledigen
				können, holen sie sich jetzt unsere EnergyChallenge-App.
			</p>
		</div>
		<p style="margin-top:30px">
			<table class="list">
				<thead>
					<tr>
						<th colspan="2">Beliebteste Aktivitäten</th>
					</tr>
				</thead>
				<tbody>
					<g:each in="${teaser}" var="activity">
						<tr>
							<td>${activity.DESCRIPTION}</td>
							<td>${activity.N} Mal ausgeführt</td>
						</tr>
					</g:each>
				</tbody>
			</table>
		</p>
		<p style="text-align: right;">
			<g:link controller="statistics" action="index">Alle Statistiken anzeigen</g:link>
		</p>
	</body>
</html>