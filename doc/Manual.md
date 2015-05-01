Handbuch - EnergyChallenge

Gruppe 2

Sören Henning
Samuel Barnes
Wolfgang Ramos
Marco Osterloh
Lennard Hammer
Nick Schulz

Inhaltsverzeichnis

1. Hinweis
2. Installation der Serversoftware
a) Voraussetzung
b) Installation
c) Administration
3. Installation der Android Applikation
a) Voraussetzung
b) Installation über den App-Store
c) Installation über die Webseite
d) Installieren in Virtualbox mit vagrant

1. Hinweis

Die hier beschriebenen Anleitungen gelten lediglich, für die vorausgesetzten Softwareversionen. Daher ist die Anwendbarkeit der Anleitungen nur bei Nutzung genau dieser Versionen gewährleistet.


2. Installation der Serversoftware

a) Voraussetzung
Für die Installation der Serversoftware wird ein Apache Tomcat7-Server auf einem Linux-basierten Betriebssystem benötigt (z.B. Debian- oder Ubuntu-Server). Der Server sollte mindestens über einen Intel Xeon E3 1230, Intel Xeon E5 oder höher verfügen. Des Weiteren wird ein Arbeitsspeicher von mindestens 8 Gigabyte RAM benötigt, sowie zwei Festplatten im RAID-Verbund mit gemeinsamen 500 Gigabyte Speicher. Außerdem muss eine Netzwerkanbindung, mit einer Geschwindigkeit von mindestens 750 Mbit/s, zur Verfügung stehen, genauso wie Peripheriegeräte bzw. Möglichkeiten zum Remotezugriff.

b) Installation
Starten sie nach der Installation ihren Server und geben sie in der Navigationsleiste ihres Browsers die IP-Adresse ihres Servers ein. Nun gelangen sie auf die Login-Seite ihres Servers. Hier gehen sie auf Manager App und melden sie sich mit ihrem Benutzernamen und Passwort an. Scrollen sie nun zum Punkt „Deploy“->“WAR file to deploy“ und klicken hier auf „Datei auswählen“. Navigieren sie nun zur Datei energy_challenge_server_dev.war oder energy_challenge_server_prod.war, markieren diese und klicken auf „Deploy“. Im nächsten Fenster erscheint "Deployed application at context path /energychallenge" oder ähnliches, somit haben sie die war-Datei erfolgreich installiert. Jetzt ist ihr Server fertig eingerichtet. Sie können nun mit ihrer Server-IP und dem angegebenen Context Path ihre betriebsbereite Webseite erreichen.
Die Datei energy_challenge_server_dev.war stellt eine Umgebung mit einem Administrator-Konto (s. Administration), sowie zufällig generierten Benutzern und Teams bereit. Die Datei energy_challenge_server_prod.war stellt eine Umgebung mit lediglich einem Administrator-Konto (s. Administration) bereit.

c) Administration
Zur Administration ist ein vordefiniertes Administrator-Konto eingerichtet. Die Login-Daten sind:
	Email: admin@example.com
	Passwort: password
3. Installation der Android Applikation

a) Voraussetzung
Für die Installation der Applikation benötigen sie ein mobiles Endgerät mit Android 5 oder höher als Betriebssystem. Des Weiteren muss man zu Nutzung der Applikation bereits auf der EnergyChallenge-Webseite registriert sein.

b) Installation  über den App-Store
Gehen sie mit Hilfe von „Google Play Store“ in den App-Store und suchen sie über die Suchleiste die „EnergyChallenge“-App und installieren diese. Nach dem automatischen Download und Installation ist ihre Applikation einsatzbereit.

c) Installation über die Webseite
Schalten sie ihr Endgerät ein und öffnen sie einen Internetbrowser ihrer Wahl. Geben sie nun folgenden Link in der Adresszeile ihres Browsers ein: https://www.klik.uni-kiel.de/de/energiesparkampagne/EnergyChallenge/EnergyChallenge.apk
Bestätigen sie nun, das sie der Webseite vertrauen und das sie die Datei herunterladen möchten. Nach Abschluss des Downloads, öffnen sie das Fenster "Downloads" und klicken sie auf "EnergyChallenge.apk". Falls ihr Gerät an dieser Stelle eine Warnung anzeigt, klicken sie auf "Einstellungen". Aktivieren sie hier "Unbekannte Herkunft" und bestätigen sie mit "OK". Navigieren sie nun wieder zu ihren Downloads und klicken sie erneut auf "EnergyChallenge.apk". Wählen sie nun einfach "installieren" und warten sie den Vorgang ab. Anschließend ist die App einsatzbereit.

d) Installieren in Virtualbox mit vagrant

Unter Ubuntu die neueste version von Vagrant ist nötig von https://www.vagrantup.com/downloads.html, dann diese befehle eingeben:

    vagrant box add hashicorp/precise32
    vagrant up

Dies startet die VM und führt das script `bootstrap.sh`
