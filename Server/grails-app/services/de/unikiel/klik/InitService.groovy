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
		// Rechtswissenschaftliche Fakult�t
		"Hermann Kantorowicz-Institut f�r juristische Grundlagenforschung",
		"Walther-Schuuml;cking-Institut f�r Internationales Recht",
		"Institut f�r Kriminalwissenschaften",
		"Institut f�r Osteurop�isches Recht",
		"Institut f�r Wirtschafts- und Steuerrecht",
		"Institut f�r Europ�isches und Internationales Privat- und Verfahrensrecht",
		"Institut f�r Sozialrecht und Gesundheitsrecht",
		"Institut f�r �ffentliches Wirtschaftsrecht",
		// Mathematisch-Naturwissenschaftliche Fakult�t
		"Leibniz-Institut f�r die P�dagogik der Naturwissenschaften und Mathematik (IPN)",
		"Mathematisches Seminar",
		"Institut f�r Experimentelle und Angewandte Physik (IEAP)",
		"Institut f�r Theoretische Physik und Astrophysik (ITAP",
		"Institut f�r Anorganische Chemie",
		"Otto Diels-Institut f�r Organische Chemie",
		"Institut f�r Physikalische Chemie",
		"Pharmazeutisches Institut",
		"Botanisches Institut",
		"Institut f�r Allgemeine Mikrobiologie",
		"Zoologische Institut und Zoologisches Museum",
		"Institut f�r Polar�kologie",
		"Geographisches Institut",
		"Institut f�r �kosystemforschung",
		"Institut f�r Ur- und Fr�hgeschichte",
		"Institut f�r Geowissenschaften",
		"GEOMAR Helmholtz-Zentrum f�r Ozeanforschung Kiel",
		"Der Botanische Garten (Museum)",
		"Das Zoologische Museum",
		"Geologischen und Mineralogischen Museum",
		"Leibniz-Labor f�r Altersbestimmung und Isotopenforschung",
		"Zentrum f�r Biochemie und Molekularbiologie (BiMo)",
		"Zentrum f�r Molekulare Biowissenschaften (ZMB)",
		"Forschungs- und Technologiezentrum Westk�ste (FTZ)",
		// Agrar- und Ern�hrungswissenschaftliche Fakult�t
		"Institut f�r Pflanzenern�hrung und Bodenkunde",
		"Institut f�r Pflanzenbau und Pflanzenz�chtung",
		"Institut f�r Phytopathologie",
		"Institut f�r Tierern�hrung und Stoffwechselphysiologie",
		"Institut f�r Tierzucht und Tierhaltung",
		"Institut f�r Landwirtschaftliche Verfahrenstechnik",
		"Institut f�r Agrar�konomie",
		"Institut f�r Humanern�hrung und Lebensmittelkunde",
		"Institut f�r Ern�hrungswirtschaft und Verbrauchslehre",
		"Institut f�r Natur- und Ressourcenschutz",
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
		"Institut f�r Alttestamentliche Wissenschaft und Biblische Arch�ologie",
		"Institut f�r Neutestamentliche Wissenschaft und Judaistik",
		"Institut f�r Kirchengeschichte",
		"Institut f�r Systematische Theologie",
		"Institut f�r Praktische Theologie",
		"Gesenius Arbeitsstelle",
		"Schleiermacher-Forschungsstelle",
		"KiBiDaNO",
		"Universit�tskirche",
		// Medizinische Fakult�t
		"Anatomisches Institut",
		// Technische Fakult�t
		"Institut f�r Elektrotechnik und Informationstechnik",
		"Institut f�r Informatik",
		"Institut f�r Materialwissenschaft",
	
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
