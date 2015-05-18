<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="public"/>
		<title>EnergyChallenge</title>
		<asset:javascript src="countdownStart.js" />
	</head>
	<body>
		<div id="right">
			<%-- TODO: create an include of the login form --%>
			<g:if test="${enableLogin}">
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
			</g:if>
			<g:elseif test="${showCountdown}">
				<div id="countdown" data-seconds="${countdown.dataSeconds}">
					<p>Die Energy|Challenge startet in</p>
					<span class="entity days">
						<span class="value">
							<g:if test="${countdown.days < 10}">
								0${countdown.days}
							</g:if>
							<g:else>
								${countdown.days}
							</g:else>
						</span>
						Tagen
					</span>
					<span class="entity hours">
						<span class="value">
							<g:if test="${countdown.hours < 10}">
								0${countdown.hours}
							</g:if>
							<g:else>
								${countdown.hours}
							</g:else>
						</span>
						Stunden
					</span>
					<span class="entity minutes">
						<span class="value">
							<g:if test="${countdown.minutes < 10}">
								0${countdown.minutes}
							</g:if>
							<g:else>
								${countdown.minutes}
							</g:else>
						</span>
						Minuten
					</span>
					<span class="entity seconds">
						<span class="value">
							<g:if test="${countdown.seconds < 10}">
								0${countdown.seconds}
							</g:if>
							<g:else>
								${countdown.seconds}
							</g:else>
						</span>
						Sekunden
					</span>
				</div>
			</g:elseif>
			<g:elseif test="${challengeIsOver}">
				Das war's! Challenge ist vorbei :(
			</g:elseif>
			<g:if test="${enableReg}">
				<div id="register" >
					<g:form name="registerFrom" url="[action:'register',controller:'auth']">
						<input type="submit" value="Jetzt registrieren!" />		
					</g:form>
				</div>
			</g:if>
			<%-- TODO: create an include of the login form --%>
		</div>
		<div id="left">
			<h1>Ideenpower für smarte Energienutzung</h1>
			<p>
				Die EnergyChallenge richtet sich an alle Studierende und Beschäftigte
				der CAU, die Spaß daran haben Energiesparmöglichkeiten am Arbeitsplatz
				auszutesten, hierfür Punkte zu sammeln und Preise zu gewinnen.
			</p>
			<p>
				Die EnergyChallenge ist ein Teil der Energiesparkampagne, welche von der
				CAU mit Unterstützung der EKSH durchgeführt wird, um energiesparendes
				Verhalten am Arbeitsplatz zu fördern. Jede/r Mitarbeiter/in hat täglich
				die Möglichkeit in vielfältiger Weise Energie einzusparen. Das Ziel der
				EnergyChallenge ist es, das Durchführen von energiesparenden Aktivitäten
				genauso zu fördern, wie neue Ideenimpulse.
			</p>
			<p>
				Die Teilnahme ist einfach: Online anmelden, eine Gruppe aussuchen
				(dies können Kollegen aus der Arbeitsgruppe sein oder auch willkürliche
				Zusammensetzungen) und die energiesparenden Aktivitäten regelmäßig in der
				Aktivitätenliste abhaken. Die Punkte werden dann automatisch dem
				Benutzerkonto zugeschrieben.
			</p>
			<p>
				Nach Ablauf der EnergyChallenge am 30. Juni werden Gewinnergruppen,
				die besten Einzelteilnehmer und herausragende Energiesparvorschläge
				im Rahmen einer Preisverleihung am 2. Juli prämiert. Es stehen Preise
				im Gesamtwert von 10.000 Euro zur Verfügung.
			</p>
			<p>
				Wir freuen uns über Ihre Teilnahme und wünschen Ihnen viel Spaß!
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
						<g:if test="${activity != null}">
						<tr>
							<td>${activity.DESCRIPTION}</td>
							<td>${activity.N} Mal ausgeführt</td>
						</tr>
						</g:if>
					</g:each>
				</tbody>
			</table>
		</p>
		<p style="text-align: right;">
			<g:link controller="statistics" action="index">Alle Statistiken anzeigen</g:link>
		</p>
	</body>
</html>
