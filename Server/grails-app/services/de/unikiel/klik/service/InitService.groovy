package de.unikiel.klik.service

import grails.transaction.Transactional
import de.unikiel.klik.persistence.*
import java.util.Random

@Transactional
class InitService {

	static def ACTIVITY_DESCRIPTIONS = [
		"Eine neue Klik-Aktivit�t beisteuern", //0
		//every hour
		"Immer das Licht ausschalten bei Verlassen des Raumes", //1
		"Immer den Monitor ausschalten bei Verlassen des Raumes", //2
		"Rechner herunterfahren bei Terminen und l�ngeren Pausen", //3
		"H�ndewaschen mit kaltem Wasser", //4
		"Wasserkocher nur mit der ben�tigten Menge Wasser f�llen (z.B. eine Tasse)", //5
		//every day
		"Mit dem Fahrrad zur Uni fahren bis 3km", //6
		"Mit dem Fahrrad zur Uni fahren bis 5km", //7
		"Mit dem Fahrrad zur Uni fahren �ber 5km", //8
		"Mobilit�t auf dem Campus zwischen den Sektoren zu Fu� oder mit Fahrrad", //9
		"Fahrgemeinschaft mit dem eigenen Auto zur Uni anbieten", //10
		"Fahrgemeinschaft zur Uni in Anspruch nehmen", //11
		"Rechner herunterfahren bei Feierabend", //12
		"Wenn m�glich das Tageslicht statt elektrisches Licht nutzen", //13
		"Schreibtischlampe statt Deckenbeleuchtung nutzen", //14
		"Sparsam drucken (doppelseitig, s/w, mehrere Seiten auf einem Blatt)", //15
		"Treppe statt Fahrstuhl nutzen pro Tag", //16
		"Thermokannen zum Warmhalten von Tee und Kaffee nutzen", //17
		"Herunterfahren von Abzugsanlagen in Laborgeb�uden bei Nichtnutzung", //18
		"Energetische Verbesserungsma�nahme auf dem Campus melden (Fr. Steinwender -4990)", //19
		//every week
		"K�hltruhen ausmisten und abschalten bei Bedarf", //20
		"Nicht genutzte K�hlschr�nke ausschalten und melden (Fr. Steinwender -4990)", //21
		//every month
		"Energieberatungsangebot der CAU wahrnehmen (Fr. Steinwender -4990)", //22
		"K�hlschr�nke auf den niedrigsten Wert stellen", //23
		//every half a year
		"L�ftungsanlagen in Laborgeb�uden optimal einstellen", //24
		"Warmwasserboiler nur auf max. 60 Grad einstellen", //25
		"Nutzung von Ventilatoren einschr�nken", //26
		"Fahrrad f�r die Fahrt zur Uni einsatzbereit machen", //27
		"Pers�nlichen CO2-Abdruck berechnen", //28
		//every year
		"B�roeinrichtung so anordnen, dass Heizk�rper frei stehen und Tageslicht optimal genutzt werden kann", //29
		"Steckerleisten im B�ro f�r elektronische Ger�te nutzen (erh�ltlich bei Fr. Steinwender unter -4990)", //30
		"Umweltpr�mie zum Ersetzen veralteter elektronischer Ger�te in Anspruch nehmen", //31
		"Bildschirme durch effizientere ersetzen", //32
		//every 3 years
		"Anmeldung beim Campusrad", //33
	]
	
	static def ACTIVITY_POINTS = [2,1,1,2,2,2,2,3,5,2,3,4,3,2,2,1,2,2,5,3,5,4,5,3,5,3,2,2,3,4,3,5,4,3]
	
	static def long MAX_DURATION_IN_MS = long.MAX_VALUE
	static def long MIN_DURATION_IN_MS = 1
	static def long ONE_HOUR_IN_MS = 60*60*1000
	static def long ONE_DAY_IN_MS = 24*60*60*1000
	static def long ONE_WEEK_IN_MS = 7*24*60*60*1000
	static def long ONE_MONTH_IN_MS = 30*24*60*60*1000
	static def long HALF_A_YEAR_IN_MS = 183*24*60*60*1000
	static def long ONE_YEAR_IN_MS = 365*24*60*60*1000
	static def long THREE_YEARS_IN_MS = 3*365*24*60*60*1000
	
	static ACTIVITY_DURATIONS = [
		MIN_DURATION_IN_MS, // 0.
		ONE_HOUR_IN_MS, // 1.
		ONE_HOUR_IN_MS, // 2.
		ONE_HOUR_IN_MS, // 3.
		ONE_HOUR_IN_MS, // 4.
		ONE_HOUR_IN_MS, // 5.
		ONE_DAY_IN_MS, // 6.
		ONE_DAY_IN_MS, // 7.
		ONE_DAY_IN_MS, // 8.
		ONE_DAY_IN_MS, // 9.
		ONE_DAY_IN_MS, // 10.
		ONE_DAY_IN_MS, // 11.
		ONE_DAY_IN_MS, // 12.
		ONE_DAY_IN_MS, // 13.
		ONE_DAY_IN_MS, // 14.
		ONE_DAY_IN_MS, // 15.
		ONE_DAY_IN_MS, // 16.
		ONE_DAY_IN_MS, // 17.
		ONE_DAY_IN_MS, // 18.
		ONE_DAY_IN_MS, // 19.
		ONE_WEEK_IN_MS, // 20.
		ONE_WEEK_IN_MS, // 21.
		ONE_MONTH_IN_MS, // 22.
		ONE_MONTH_IN_MS, // 23.
		HALF_A_YEAR_IN_MS, // 24.
		HALF_A_YEAR_IN_MS, // 25.
		HALF_A_YEAR_IN_MS, // 26.
		HALF_A_YEAR_IN_MS, // 27.
		HALF_A_YEAR_IN_MS, // 28.
		ONE_YEAR_IN_MS, // 29.
		ONE_YEAR_IN_MS, // 30.
		ONE_YEAR_IN_MS, // 31.
		ONE_YEAR_IN_MS, // 32.
		THREE_YEARS_IN_MS // 33.
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
		"Walther-Schuuml;cking-Institut für Internationales Recht",
		"Institut für Kriminalwissenschaften",
		"Institut für Osteuropäisches Recht",
		"Institut für Wirtschafts- und Steuerrecht",
		"Institut für Europäisches und Internationales Privat- und Verfahrensrecht",
		"Institut für Sozialrecht und Gesundheitsrecht",
		"Institut für öffentliches Wirtschaftsrecht",
		// Mathematisch-Naturwissenschaftliche Fakultät
		"Leibniz-Institut für die Pädagogik der Naturwissenschaften und Mathematik (IPN)",
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
		"Institut für Polarökologie",
		"Geographisches Institut",
		"Institut für ökosystemforschung",
		"Institut für Ur- und Frühgeschichte",
		"Institut für Geowissenschaften",
		"GEOMAR Helmholtz-Zentrum für Ozeanforschung Kiel",
		"Der Botanische Garten (Museum)",
		"Das Zoologische Museum",
		"Geologischen und Mineralogischen Museum",
		"Leibniz-Labor für Altersbestimmung und Isotopenforschung",
		"Zentrum für Biochemie und Molekularbiologie (BiMo)",
		"Zentrum für Molekulare Biowissenschaften (ZMB)",
		"Forschungs- und Technologiezentrum Westküste (FTZ)",
		// Agrar- und Ernährungswissenschaftliche Fakultät
		"Institut für Pflanzenernährung und Bodenkunde",
		"Institut für Pflanzenbau und Pflanzenzüchtung",
		"Institut für Phytopathologie",
		"Institut für Tierernährung und Stoffwechselphysiologie",
		"Institut für Tierzucht und Tierhaltung",
		"Institut für Landwirtschaftliche Verfahrenstechnik",
		"Institut für Agrarökonomie",
		"Institut für Humanernährung und Lebensmittelkunde",
		"Institut für Ernährungswirtschaft und Verbrauchslehre",
		"Institut für Natur- und Ressourcenschutz",
		// Philosophische Fakultät
		"Institut für Pädagogik",
		"Philosophisches Seminar",
		"Institut für Psychologie",
		"Institut für Sozialwissenschaften",
		"Institut für Sportwissenschaft",
		"Englisches Seminar",
		"Germanistisches Seminar",
		"Institut für Neuere Deutsche Literatur und Medien",
		"Institut für Klassische Altertumskunde",
		"Institut für Skandinavistik, Frisistik und Allgemeine Sprachwissenschaft (ISFAS)",
		"Romanisches Seminar",
		"Institut für Slavistik",
		"Seminar für Europäische Ethnologie/Volkskunde",
		"Historisches Seminar",
		"Kunsthistorisches Institut",
		"Seminar für Orientalistik",
		"Musikwissenschaftliches Institut",
		"Zentrum für Schlüsselqualifikationen (ZfS)",
		"Zentrum für Lehrerbildung (ZfL)",
		"Zentrum für Asiatische und Afrikanische Studien (ZAAS)",
		"Zentrum für Osteuropastudien (ZOS)",
		"Zentrum für Nordamerikastudien (ZNAS)",
		"Chinazentrum",
		"Zentrum für Rechtspsychologie, Kriminalwissenschaften und forensische Psychopathologie",
		"Gustav-Radbruch-Netzwerk für Philosophie und Ethik der Umwelt",
		// Theologische Fakultät
		"Institut für Alttestamentliche Wissenschaft und Biblische Archäologie",
		"Institut für Neutestamentliche Wissenschaft und Judaistik",
		"Institut für Kirchengeschichte",
		"Institut für Systematische Theologie",
		"Institut für Praktische Theologie",
		"Gesenius Arbeitsstelle",
		"Schleiermacher-Forschungsstelle",
		"KiBiDaNO",
		"Universitätskirche",
		// Medizinische Fakultät
		"Anatomisches Institut",
		"Biochemisches Institut",
		"Physiologisches Institut",
		"Institut für Medizinische Klimatologie",
		"Institut für Allgemeinmedizin",
		"Institut für Epidemiologie",
		"Institut für Experimentelle Medizin",
		"Institut für Experimentelle und Klinische Pharmakologie",
		"Institut für Experimentelle Tumorforschung",
		"Institut für Humangenetik",
		"Institut für Immunologie",
		"Institut für Infektionsmedizin",
		"Institut für Klinische Chemie",
		"Institut für Klinische Molekularbiologie",
		"Institut für Medizinische Informatik und Statistik",
		"Institut für Medizinische Psychologie und Medizinische Soziologie",
		"Institut für Pathologie",
		"Institut für Rechtsmedizin",
		"Institut für Sexualmedizin und forensische Psychiatrie und Psychotherapie",
		"Institut für Toxikologie und Pharmakologie für Naturwissenschaftler",
		// Wirtschats- und Sozialwissenschaftliche Fakultät
		"Institut für Betriebswirtschaftslehre",
		"Institut für Volkswirtschaftslehre",
		"Institut für Statistik und Ökonometrie",
		"Institut für Quantitative Betriebs- und Volkswirtschaftliche Forschung (QBER)",
		"Institut für Regionalforschung",
		"Institut für Innovationsforschung",
		"Institut für Sozialwissenschaften",
		"Institut für Weltwirtschaft",
		"Deutsche Zentralbibliothek für Wirtschaftswissenschaften",
		// Technische Fakultät
		"Institut für Elektrotechnik und Informationstechnik",
		"Institut für Informatik",
		"Institut für Materialwissenschaft",
	
		"Andere"
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
