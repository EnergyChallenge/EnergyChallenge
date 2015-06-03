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
  <h1>Jetzt registrieren!</h1>
  <div id="registerform">
	  <g:form action="signUp" params="[id: params.id]">
	    <input type="hidden" name="targetUri" value="${targetUri}" />
	    <table>
	      <tbody>
	        <tr>
	          <td>E-Mail:</td>
	          <td><input type="text" name="email" value="${params.email}" /></td>
	        </tr>
	        <tr>
	          <td>Vorname:</td>
	          <td><input type="text" name="firstName" value="${params.firstName}" /></td>
	        </tr>
	        <tr>
	          <td>Nachname:</td>
	          <td><input type="text" name="lastName" value="${params.lastName}" /></td>
	        </tr>
	        <tr>
	          <td>Passwort:</td>
	          <td><input type="password" name="password" value="" /></td>
	        </tr>
	        <tr>
	          <td>Passwort wiederholen:</td>
	          <td><input type="password" name="password2" value="" /></td>
	        </tr>
	        <tr>
	          <td>Fakultät/Institut:</td>
	          <td><select name="instituteId" >
	          <g:each in="${institutes}" var="institute">
	          <option value="${institute.getId()}">${institute.getName()}</option>
	          </g:each>
	          	
	          </select></td>
	        </tr>
	       </tbody>
	    </table>
	    <h3>Teilnahme- und Nutzungsbedingungen der EnergyChallenge</h3>
	    <div id="rules">
	    	<p>
	    		<strong>Was ist die EnergyChallenge?</strong>
	    	</p>
	    	<p>
	    		Die EngeryChallenge ist ein Online-Wettbewerb zur Förderung von
	    		energiesparendem Verhalten an der Christian-Albrechts-Universität
	    		zu Kiel (CAU). CAU-Angehörige können sich anmelden, aus einer Liste
	    		Energiesparaktivitäten auswählen und diese an der CAU umsetzen. Für jede
	    		ausgeführte Aktivität gibt es Punkte. Außerdem können sich Teilnehmende
	    		zu Teams zusammenschließen und selbst Vorschläge für Energiesparaktivitäten
	    		einreichen.
	    	</p>
	    	<p>
				Auf der Profilseite der Teilnehmer werden die ausgeführten Aktivitäten
				und die gesammelten Punkte dokumentiert. Die Teilnehmenden und Teams
				mit den meisten Punkten können Preise im Gesamtwert von 10.000 Euro
				gewinnen. Die Preisverleihung findet statt am 2. Juli 2015, um 17 Uhr
				im Wissenschaftszentrum, Fraunhoferstraße 13, 24118 Kiel.
			</p>
			<p>
				<strong>Ablauf des Wettbewerbs</strong> 
			</p>
			<p>
				Die EnergyChallenge beginnt am 01.06.2015 (00:00 Uhr) und läuft bis
				einschließlich 30.06.2015 (23:59 Uhr).
			</p>
			<p>
				<strong>Registrierung</strong>
			</p>
			<p>
				Alle Menschen, die ihren hauptsächlichen Arbeits- und Studienort
				an der Christian-Albrechts-Universität zu Kiel haben, sind herzlich
				eingeladen, an der EnergyChallenge teilzunehmen. Jede/r Teilnehmende
				darf sich nur einmal anmelden und ein Nutzerprofil erstellen.
				Die Mitgliedschaft in einem Team ist auf ein Team begrenzt.
				Die Teammitgliedschaft kann nur so lange gewechselt werden, solange
				keine Aktivitäten durchgeführt worden sind.
			</p> 
			<p>
				Alle anderen Interessierten möchten wir herzlich bitten, sich
				lediglich zu Anschauungszwecken für die EnergyChallenge zu registrieren.
				Die Gewinnberechtigung im Wettbewerb ist für externe Teilnehmende
				ausgeschlossen.
			</p>
			<p>
				Bitte nutzen Sie zur Registrierung Ihre CAU-E-Mail-Adresse.
				Die CAU-Angehörigkeit wird anhand der bei Anmeldung hinterlegten
				E-Mailadresse überprüft. 
			</p>
			<p>
				Für die Registrierung können Sie ein Bild für Ihr persönliches
				Profil oder Ihr Team hochladen. Bitte stellen Sie sicher, dass Sie
				für diese Bilder die entsprechenden Bildrechte zur Veröffentlichung 
				innehaben.
			</p>
			<p>
				<strong>Teilnahmeberechtigung am Wettbewerb</strong>
			</p>
			<p>
				Teilnahmeberechtigt am Wettbewerb und damit gewinnberechtigt sind
				alle Beschäftigten des administrativen und wissenschaftlichen Dienstes
				an der CAU sowie eingeschriebene Studierende der CAU. Dazu zählen auch
				Beschäftigte und Studierende in den An-Instituten der CAU und
				Medizinstudierende im vorklinischen Studienabschnitt.
			</p>
			<p>
				<strong>Punkte sammeln</strong>
			</p>
			<p>
				Jede/r Teilnehmende kann durch das Auswählen und Ausführen einer
				Aktivität aus der Aktivitätenliste Punkte sammeln. Diese Punkte
				werden dem persönlichen Profil gutgeschrieben und sind für alle
				Teilnehmenden sichtbar.
			</p>
			<p>
				Sofern der Teilnehmende auch Mitglied in einem Team ist, werden
				diese Punkte anteilig seinem Team zugerechnet. Dabei wird die
				Punktanzahl durch die Anzahl der Teammitglieder geteilt.
			</p>
			<p>
				<strong>Voraussetzung für die Bewertung</strong>
			</p>
			<p>
				Alle ausgewählten Aktivitäten des Wettbewerbs müssen am Arbeits-
				bzw. Studienplatz an der CAU umgesetzt werden, damit diese in die
				Gesamtbewertung eines Nutzers oder Teams eingehen können. Außerdem
				zählen nur Aktivitäten, die im Zeitraum der EnergyChallenge vom
				01.06.2015 bis zum 30.06.2015, erledigt wurden.
			</p>
			<p>
				<strong>Verpflichtungen</strong>
			</p>
			<p>
				Die Teilnehmenden verpflichten sich, die im Wettbewerb ausgewählten
				Aktivitäten auch wahrhaftig und im Sinne der Beschreibung an ihrem 
				Arbeits- bzw. Studienort ausgeführt zu haben.
			</p>
			<p>
				Zuwiderhandlungen und Verstöße werden mit Ausschluss aus dem
				Wettbewerb bestraft. Zudem behalten sich die Administratoren
				des Wettbewerbs vor, das Nutzerprofil des Teilnehmenden zu löschen und
				alle gesammelten Punkte zu annullieren.
			</p>
			<p>
				<strong>Vorschläge einreichen</strong>
			</p>
			<p>
				Jeder Teilnehmende kann eigene Vorschläge zur Energieeinsparung
				einreichen. Diese Vorschläge können von den anderen Teilnehmern bewertet
				und kommentiert werden.
			</p>
			<p>
				Die Administratoren entscheiden innerhalb von 48 Stunden, ob ein
				eingereichter Vorschlag in die Aktivitätenliste aufgenommen wird
				und somit im Wettbewerb zur Verfügung steht.
			</p>
			<p>
				Die Administratoren behalten sich redaktionelle Änderungen vor.
			</p> 
			<p>
				<strong>Ziel der EnergyChallenge/Gewinnmöglichkeiten</strong>
			</p>
			<p>
				Ziel der EnergyChallenge ist es, die meisten Punkte zu sammeln.
				Die Gewinnmöglichkeiten sind breit gefächert. Preise werden vergeben für:
			</p>
			<ol>
				<li>
					a)  1. bis 5. Platz in der Einzelwertung (gemessen am Punktestand auf
					der Profilseite)
				</li>
				<li>
					b)  1. bis 3. Platz in der Teamwertung (gemessen am Punktestand auf der Teamseite)
				</li>
				<li>
					c)  Beliebtester eingereichte Vorschlag eines Teilnehmenden
					(bewertet nach der Häufigkeit der Umsetzung im Gesamtwettbewerb)
				</li>
				<li>
					d)  Ökologischster Vorschlag (bewertet nach CO2 -Einsparpotenzial)
				</li>
				<li>
					e)  Kategoriewertung (Preis für den Teilnehmenden, der die meisten
					Aktivitäten innerhalb einer Kategorie ausgeführt hat)
				</li>
			</ol>
			<p>
				<strong>Entwicklung der EnergyChallenge</strong>
			</p>
			<p>
				Die EnergyChallenge findet dieses Jahr zum ersten Mal statt und
				befindet sich noch in der Entwicklung. Nichts ist perfekt und kleine
				Fehler in der Software sind nicht auszuschließen. Wir würden uns freuen,
				wenn Sie uns helfen, die EnergyChallenge noch besser zu machen.
				Melden Sie uns Fehler im System und wir versuchen diese, so schnell
				wie möglich zu beheben.
			</p>
	    </div>
	    <div>
	    	<input type="checkbox" name="agreeRules" id="agreeRules" value="1" />
	        <label for="agreeRules">Ich akzeptiere die Teilnahme- und Nutzungsbedingungen.</label>
	    	<input type="submit" value="Registrieren" />
	    </div>
	       
	  </g:form>
	  </div>
  </div>
</body>
</html>
