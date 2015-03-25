package de.unikiel.klik

import grails.transaction.Transactional
import de.unikiel.klik.model.*
import java.util.Random

@Transactional
class InitService {

	static def ACTIVITY_DESCRIPTIONS = [
		"Eine neue Klik-Aktivit�t beisteuern",
		"Mit dem Fahrrad zur Uni fahren bis 3km",
		"Mit dem Fahrrad zur Uni fahren bis 5km",
		"Mit dem Fahrrad zur Uni fahren über 5km",
		"Mobilität auf dem Campus zwischen den Sektoren zu Fuß oder mit Fahrrad",
		"Fahrgemeinschaft mit dem eigenen Auto zur Uni anbieten",
		"Fahrgemeinschaft zur Uni in Anspruch nehmen",
		"Immer das Licht ausschalten bei Verlassen des Raumes",
		"Immer den Monitor ausschalten bei Verlassen des Raumes",
		"Rechner herunterfahren bei Terminen und längeren Pausen",
		"Rechner herunterfahren bei Feierabend",
		"Kühltruhen ausmisten und abschalten bei Bedarf",
		"Kühlschränke auf den niedrigsten Wert stellen",
		"Nicht genutzte Kühlschränke ausschalten und melden (Fr. Steinwender -4990)",
		"Lüftungsanlagen in Laborgebäuden optimal einstellen",
		"Händewaschen mit kaltem Wasser",
		"Wasserkocher nur mit der benötigten Menge Wasser füllen (z.B. eine Tasse)",
		"Wenn möglich das Tageslicht statt elektrisches Licht nutzen",
		"Schreibtischlampe statt Deckenbeleuchtung nutzen",
		"Warmwasserboiler nur auf max. 60 Grad einstellen",
		"Energieberatungsangebot der CAU wahrnehmen (Fr. Steinwender -4990)",
		"Energetische Verbesserungsmaßnahme auf dem Campus melden (Fr. Steinwender -4990)",
		"Büroeinrichtung so anordnen, dass Heizkörper frei stehen und Tageslicht optimal genutzt werden kann",
		"Sparsam drucken (doppelseitig, s/w, mehrere Seiten auf einem Blatt)",
		"Treppe statt Fahrstuhl nutzen pro Tag",
		"Steckerleisten im Büro für elektronische Geräte nutzen (erhältlich bei Fr. Steinwender unter -4990)",
		"Herunterfahren von Abzugsanlagen in Laborgebäuden bei Nichtnutzung",
		"Nutzung von Ventilatoren einschränken",
		"Thermokannen zum Warmhalten von Tee und Kaffee nutzen",
		"Anmeldung beim Campusrad",
		"Fahrrad für die Fahrt zur Uni einsatzbereit machen",
		"Persönlichen CO2-Abdruck berechnen",
		"Röhrenmonitore mit Flachbildschirmen ersetzen",
		"Umweltprämie zum Ersetzen veralteter elektronischer Geräte in Anspruch nehmen"
	]
	
	static def ACTIVITY_POINTS = [2,2,3,5,2,3,4,1,1,2,3,5,3,4,5,2,2,2,2,3,5,3,4,1,2,3,5,2,2,3,2,3,4,5]
	
	static def long MIN_DURATION_IN_MS = 1
	static def long ONE_DAY_IN_MS = 24*60*60*1000
	
	static ACTIVITY_DURATIONS = [
		MIN_DURATION_IN_MS, // 0.
		ONE_DAY_IN_MS, // 1.
		ONE_DAY_IN_MS, // 2.
		ONE_DAY_IN_MS, // 3.
		ONE_DAY_IN_MS, // 4.
		ONE_DAY_IN_MS, // 5.
		ONE_DAY_IN_MS, // 6.
		MIN_DURATION_IN_MS, // 7. TODO give correct duration
		MIN_DURATION_IN_MS, // 8. TODO give correct duration
		MIN_DURATION_IN_MS, // 9.
		ONE_DAY_IN_MS,      // 10.
		MIN_DURATION_IN_MS, // 11. TODO give correct duration
		MIN_DURATION_IN_MS, // 12. TODO give correct duration
		MIN_DURATION_IN_MS, // 13.
		MIN_DURATION_IN_MS, // 14.
		MIN_DURATION_IN_MS, // 15.
		MIN_DURATION_IN_MS, // 16.
		ONE_DAY_IN_MS, // 17.
		ONE_DAY_IN_MS, // 18.
		MIN_DURATION_IN_MS, // 19. TODO give correct duration
		MIN_DURATION_IN_MS, // 20. TODO once in a lifetime?
		MIN_DURATION_IN_MS, // 21.
		MIN_DURATION_IN_MS, // 22. TODO once in a lifetime?
		MIN_DURATION_IN_MS, // 23. TODO give correct duration
		ONE_DAY_IN_MS,      // 24.
		MIN_DURATION_IN_MS, // 25. TODO once in a lifetime?
		MIN_DURATION_IN_MS, // 26. TODO once in a lifetime?
		MIN_DURATION_IN_MS, // 27. TODO once in a lifetime?
		MIN_DURATION_IN_MS, // 28. TODO once in a lifetime?
		MIN_DURATION_IN_MS, // 29. TODO once in a lifetime?
		MIN_DURATION_IN_MS, // 30. TODO once in a lifetime?
		MIN_DURATION_IN_MS, // 31. TODO once in a lifetime?
		MIN_DURATION_IN_MS, // 32. TODO once in a lifetime?
		MIN_DURATION_IN_MS  // 33. TODO give correct duration
	]
	
	static ACTIVITY_VISIBILITY = [
		false, //0
		true, //1
		true, //2
		true, //3
		true, //4
		true, //5
		true, //6
		true, //7
		true, //8
		true, //9
		true, //10
		true, //11
		true, //12
		true, //13
		true, //14
		true, //15
		true, //16
		true, //17
		true, //18
		true, //19
		true, //20
		true, //21
		true, //22
		true, //23
		true, //24
		true, //25
		true, //26
		true, //27
		true, //28
		true, //29
		true, //30
		true, //31
		true, //32
		true, //33
		]
	
	// CAU Institute
	static def INSTITUTES = [
		// Rechtswissenschaftliche Fakultät
		"Hermann Kantorowicz-Institut für juristische Grundlagenforschung",
		"Walther-Schücking-Institut für Internationales Recht",
		"Institut für Kriminalwissenschaften",
		"Institut für Osteurop�isches Recht",
		"Institut für Wirtschafts- und Steuerrecht",
		"Institut für Europ�isches und Internationales Privat- und Verfahrensrecht",
		"Institut für Sozialrecht und Gesundheitsrecht",
		"Institut für �ffentliches Wirtschaftsrecht",
		// Mathematisch-Naturwissenschaftliche Fakultät
		"Leibniz-Institut für die P�dagogik der Naturwissenschaften und Mathematik (IPN)",
		"Mathematisches Seminar",
		"Institut für Experimentelle und Angewandte Physik (IEAP)",
		"Institut für Theoretische Physik und Astrophysik (ITAP",
		"Institut für Anorganische Chemie",
		"Otto Diels-Institut für Organische Chemie",
		"Institut für Physikalische Chemie",
		"Pharmazeutisches Institut",
		"Botanisches Institut",
		"Institut für Allgemeine Mikrobiologie",
		"Zoologische Institut und Zoologisches Museum",
		"Institut für Polar�kologie",
		"Geographisches Institut",
		"Institut für �kosystemforschung",
		"Institut für Ur- und Fr�hgeschichte",
		"Institut für Geowissenschaften",
		"GEOMAR Helmholtz-Zentrum f�r Ozeanforschung Kiel",
		"Der Botanische Garten (Museum)",
		"Das Zoologische Museum",
		"Geologischen und Mineralogischen Museum",
		"Leibniz-Labor für Altersbestimmung und Isotopenforschung",
		"Zentrum für Biochemie und Molekularbiologie (BiMo)",
		"Zentrum für Molekulare Biowissenschaften (ZMB)",
		"Forschungs- und Technologiezentrum Westküste (FTZ)",
		// Agrar- und Ernährungswissenschaftliche Fakultät
		"Institut für Pflanzenern�hrung und Bodenkunde",
		"Institut für Pflanzenbau und Pflanzenzüchtung",
		"Institut für Phytopathologie",
		"Institut für Tierern�hrung und Stoffwechselphysiologie",
		"Institut für Tierzucht und Tierhaltung",
		"Institut für Landwirtschaftliche Verfahrenstechnik",
		"Institut für Agrar�konomie",
		"Institut für Humanernährung und Lebensmittelkunde",
		"Institut für Ern�hrungswirtschaft und Verbrauchslehre",
		"Institut für Natur- und Ressourcenschutz",
		// Philosophische Fakult�t
		"Institut f�r P�dagogik",
		"Philosophisches Seminar",
		"Institut f�r Psychologie",
		"Institut f�r Sozialwissenschaften",
		"Institut f�r Sportwissenschaft",
		"Englisches Seminar",
		"Germanistisches Seminar",
		"Institut f�r Neuere Deutsche Literatur und Medien",
		"Institut f�r Klassische Altertumskunde",
		"Institut f�r Skandinavistik, Frisistik und Allgemeine Sprachwissenschaft (ISFAS)",
		"Romanisches Seminar",
		"Institut f�r Slavistik",
		"Seminar f�r Europ�ische Ethnologie/Volkskunde",
		"Historisches Seminar",
		"Kunsthistorisches Institut",
		"Seminar f�r Orientalistik",
		"Musikwissenschaftliches Institut",
		"Zentrum f�r Schl�sselqualifikationen (ZfS)",
		"Zentrum f�r Lehrerbildung (ZfL)",
		"Zentrum f�r Asiatische und Afrikanische Studien (ZAAS)",
		"Zentrum f�r Osteuropastudien (ZOS)",
		"Zentrum f�r Nordamerikastudien (ZNAS)",
		"Chinazentrum",
		"Zentrum f�r Rechtspsychologie, Kriminalwissenschaften und forensische Psychopathologie",
		"Gustav-Radbruch-Netzwerk f�r Philosophie und Ethik der Umwelt",
		// Theologische Fakult�t
		
		// Technische Fakultät
		"Institut für Elektrotechnik und Informationstechnik",
		"Institut für Informatik",
		"Institut für Materialwissenschaft",
	
		"Anderes",
	]
	
	static void initKlikActivities() {
		//println ACTIVITY_DESCRIPTIONS.size()
		for(int i=0; i < ACTIVITY_DESCRIPTIONS.size(); i++) {
			new Activity(description: ACTIVITY_DESCRIPTIONS[i], points: ACTIVITY_POINTS[i], duration: (long) ACTIVITY_DURATIONS[i], visible: ACTIVITY_VISIBILITY[i]).save(flush: true)
		}
	}
	
	// initialize roles "user" and "admin" with corresponding permissions
	static void initKlikRoles() {
		// init user role
		if(Role.findByName("user") == null) {
			def user_role = new Role(name: "user")
			user_role.addToPermissions("*:*")
			user_role.save(flush: true)
		}
		
		// init admin role
		if(Role.findByName("admin") == null) {
			def admin_role = new Role(name: "admin")
			admin_role.addToPermissions("*:*")
			admin_role.save(flush: true)
		}
	}
	
	static void initCauInstitutions() {
		for(int i=0; i < INSTITUTES.size(); i++) {
			new Institute(name: INSTITUTES[i]).save(flush: true)
		}
	}
	
	/*
	static void initUsersWithCompletedActivities(){
		def max = Activity.count()
		
		for(int i=1; i<=max; i++){
			new User() // TODO
			Activity.get(i)
		}
	}
	*/
}
