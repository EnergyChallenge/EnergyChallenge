package de.unikiel.klik.service

import grails.transaction.Transactional
import de.unikiel.klik.persistence.*
import java.util.Random
import org.apache.shiro.crypto.hash.Sha256Hash

@Transactional
class InitService {

	static def ACTIVITY_DESCRIPTIONS = [
		"Eine neue Klik-Aktivität beisteuern", //0
		//every hour
		"Ich habe das Licht beim Verlassen des Raumes ausgeschaltet.", //1
		//"Immer das Licht ausschalten bei Verlassen des Raumes", //1
		"Ich habe den Monitor beim Verlassen des Raumes ausgeschaltet.", //2
		//"Immer den Monitor ausschalten bei Verlassen des Raumes", //2
		"Ich habe den Rechner bei einem Termin/ einer längeren Pause heruntergefahren.", //3
		//"Rechner herunterfahren bei Terminen und längeren Pausen", //3
		"Ich habe mir mit kaltem Wasser die Hände gewaschen" , //4
		//"Händewaschen mit kaltem Wasser", //4
		"Ich habe den Wasserkocher nur mit der tatsächlich benötigten Wassermenge befüllt.", //5
		//"Wasserkocher nur mit der benötigten Menge Wasser füllen (z.B. eine Tasse)", //5
		//every day
		"Ich bin bis zu 3km mit dem Fahrrad zur Uni gefahren", //6
		//"Mit dem Fahrrad zur Uni fahren bis 3km", //6
		"Ich bin bis zu 5km mit dem Fahrrad zur Uni gefahren", //7
		//"Mit dem Fahrrad zur Uni fahren bis 5km", //7
		"Ich bin über 5km mit dem Fahrrad zur Uni gefahren.", //8
		//"Mit dem Fahrrad zur Uni fahren über 5km", //8
		"Ich habe einen Weg zwischen den Universitätssektoren mit dem Fahrrad oder zu Fuß zurück gelegt", //9
		//"Mobilität auf dem Campus zwischen den Sektoren zu Fuß oder mit Fahrrad", //9
		"Ich biete eine genutzte Fahrgemeinschaft (mind. 1 Mitfahrer) zur Universität an.", //10
		//"Fahrgemeinschaft mit dem eigenen Auto zur Uni anbieten", //10
		"Ich nehme eine Fahrgemeinschaft, zur Universität, in Anspruch.", //11
		//"Fahrgemeinschaft zur Uni in Anspruch nehmen", //11
		"Ich habe den Rechner nach Feierabend herunter gefahren.", //12
		//"Rechner herunterfahren bei Feierabend", //12
		"Ich habe, wenn möglich, das Tageslicht anstatt elektrisches Licht genutzt.", //13
		//"Wenn möglich das Tageslicht statt elektrisches Licht nutzen", //13
		"Ich habe die Schreibtischlampe, statt der Deckenlampe genutzt.", //14
		//"Schreibtischlampe statt Deckenbeleuchtung nutzen", //14
		"Ich habe einmal sparsam gedruckt (doppelseitig, s/w, mehrere Seiten auf ein Blatt).", //15
		//"Ich habe darauf verzichtet, allgemeine E-Mails auszudrucken.",
		//"Sparsam drucken (doppelseitig, s/w, mehrere Seiten auf einem Blatt)", //15
		//"Ich habe Treppen anstelle des Aufzuges genutzt für 1-2 Stockwerke.",
		//"Ich habe Treppen anstelle des Aufzuges genutzt für 3-5 Stockwerke.",
		"Ich habe Treppen anstelle des Aufzuges genutzt.", //16
		//"Treppe statt Fahrstuhl nutzen pro Tag", //16
		//"Thermokannen zum Warmhalten von Tee und Kaffee nutzen", //17 Punkte: 2, Kategorie: Mobilität
		"Ich habe die Abzugsanlagen im Labor, bei Nichtnutzung, abgeschaltet.", //18
		//"Herunterfahren von Abzugsanlagen in Laborgebäuden bei Nichtnutzung", //18
		"Ich habe energetische Verbesserungsmaß nahmen auf dem Campus gemeldet. (bei Frau Steinwender, -4990)", //19
		//"Energetische Verbesserungsmaßnahme auf dem Campus melden (Fr. Steinwender -4990)", //19
		//every week
		"Ich habe die Kühltruhe ausgemistet und bei Bedarf abgeschaltet.", //20
		//"Kühltruhen ausmisten und abschalten bei Bedarf", //20
		"Ich habe einen nicht genutzten Kühlschrank ausgeschaltet und gemeldet. (bei Frau Steinwender, -4990)", //21
		//"Nicht genutzte Kühlschränke ausschalten und melden (Fr. Steinwender -4990)", //21
		//every month
		"Ich habe die Energieberatung der CAU wahrgenommen (bei Frau Steinwender, -4990)", //22
		//"Energieberatungsangebot der CAU wahrnehmen (Fr. Steinwender -4990)", //22
		"Ich habe den Kühlschrank auf die niedrigste Stufe gestellt", //23
		//"Kühlschränke auf den niedrigsten Wert stellen", //23
		//every half a year
		"Ich habe die Lüftungsanlagen in Laborgebäuden optimal eingestellt", //24
		//"Lüftungsanlagen in Laborgebäuden optimal einstellen", //24
		"Ich habe den Warmwasserboiler auf max. 60 Grad eingestellt." , //25
		//"Warmwasserboiler nur auf max. 60 Grad einstellen", //25
		"Ich habe die Nutzung von Ventilatoren eingeschränkt.", //26
		//"Nutzung von Ventilatoren einschränken", //26
		"Ich habe mein Fahrrad verkehrstauglich gemacht.", //27
		//"Fahrrad für die Fahrt zur Uni einsatzbereit machen", //27
		"Ich habe meinen persönlichen CO2- Abdruck berechnet", //28
		//"Persönlichen CO2-Abdruck berechnen", //28
		//every year
		"Ich habe die Büroeinrichtung so angeordnet, dass Heizkörper frei stehen und dass das Tageslicht optimal genutzt wird.", //29
		//"Büroeinrichtung so anordnen, dass Heizkörper frei stehen und Tageslicht optimal genutzt werden kann", //29
		"Ich nutzte schaltbare Steckerleisten im Büro, für elektrische Geräte.", //30
		//"Steckerleisten im Büro für elektronische Geräte nutzen (erhältlich bei Fr. Steinwender unter -4990)", //30
		"Ich nehme die Umweltprämie zum Ersetzen veralteter elektronischer Geräte in Anspruch.", //31
		//"Umweltprämie zum Ersetzen veralteter elektronischer Geräte in Anspruch nehmen", //31
		"Ich habe meinen Bildschirm durch einen effizienteren ersetzt.", //32
		//"Bildschirme durch effizientere ersetzen", //32
		//every 3 years
		"Ich habe mich beim Campusrad angemeldet." //33
		//"Anmeldung beim Campusrad" //33
	]
	
	static def ACTIVITY_POINTS = [2,1,1,2,2,2,2,3,5,2,3,4,3,2,2,1,2,5,3,5,4,5,3,5,3,2,2,3,4,3,5,4,3]

	/* Andere = 0
	   Licht = 1
	   Wasser = 2
	   Mobilität = 3
	   Papier = 4
	   Information = 5
	   Energie im Büro = 6
	   Energie im Labor = 7
	   Energie in der Küche = 8
	*/
	static def ACTIVITY_CATEGORIES = [0,1,6,6,2,2,3,3,3,3,3,3,6,1,1,4,2,7,5,8,5,5,8,7,8,7,3,5,6,6,5,6,3]
	
	static def long MIN_DURATION_IN_MS = 1L
	static def long ONE_HOUR_IN_MS = 60L*60*1000
	static def long ONE_DAY_IN_MS = 24L*60*60*1000
	static def long ONE_WEEK_IN_MS = 7L*24*60*60*1000
	static def long ONE_MONTH_IN_MS = 30L*24*60*60*1000
	static def long HALF_A_YEAR_IN_MS = 183L*24*60*60*1000
	static def long ONE_YEAR_IN_MS = 365L*24*60*60*1000
	static def long THREE_YEARS_IN_MS = 3L*365*24*60*60*1000
	
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
		//ONE_DAY_IN_MS, // 17.
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
		//true, //17
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
		true //33
		]
	
	// CAU Institute
	// Stand: 15.05.
	static def INSTITUTES = [

		"Agrar- und Ernähungswissenschaftliche Fakultät",
		"Mathematisch- Naturwissenschaftliche Fakultät",
		"Medizinische Fakultät",
		"Philosophische Fakultät",
		"Rechtswissenschaftliche Fakultät",
		"Technische Fakultät",
		"Theologische Fakultät",
		"Wirtschafts- und Sozialwissenschaftliche Fakultät",

		// angeschlossene Institute
		"GEOMAR",
		"IPN",

		// administratorische Bereiche
		"andere Einrichtungen",
		"Botanischer Garten",
		"Forschungs- und Technologiezentrum Westküste",
		"Graduiertenzentrum",
		"Interdisziplinäres Zentrum Multimedia",
		"Rechenzentrum",
		"Sportzentrum",
		"Universitätsbibliothek",
		"Externe Einrichtungen"

		/* OLD
		// Rechtswissenschaftliche Fakultät
		"Hermann Kantorowicz-Institut für juristische Grundlagenforschung",
		"Walther-Schücking-Institut für Internationales Recht",
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
		"Institut für Ökosystemforschung",
		"Institut für Ur- und Frühgeschichte",
		"Institut für Geowissenschaften",
		"GEOMAR Helmholtz-Zentrum für Ozeanforschung Kiel",
		"Botanischer Garten",
		"Zoologisches Museum",
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
		*/
	]
		
	static void initKlikActivities() {
		//println ACTIVITY_DESCRIPTIONS.size()
		for(int i=0; i < ACTIVITY_DESCRIPTIONS.size(); i++) {
		  new Activity(description: ACTIVITY_DESCRIPTIONS[i], category: ACTIVITY_CATEGORIES[i] ,points: ACTIVITY_POINTS[i], duration: (long) ACTIVITY_DURATIONS[i], visible: ACTIVITY_VISIBILITY[i]).save(flush: true, failOnError: true)
		}
	}
	
	// initialize roles "user" and "admin" with corresponding permissions
	static void initKlikRoles() {
		// init user role
		if(Role.findByName("benutzer") == null) {
			def userRole = new Role(name: "benutzer")
			userRole.addToPermissions("*:*")
			userRole.save(flush: true)
		}
		
		// init admin role
		if(Role.findByName("admin") == null) {
			def adminRole = new Role(name: "admin")
			adminRole.addToPermissions("*:*")
			adminRole.save(flush: true)
		}
	}
	
	static void initCauInstitutions() {
		for(int i=0; i < INSTITUTES.size(); i++) {
			new Institute(name: INSTITUTES[i]).save(flush: true)
		}
	}

	static void initKlikAdmins() {
		String instituteName = "andere Einrichtungen"

		// create klik institute if it doesn't exist yet
		if(Institute.findByName(instituteName) == null) {
			new Institute(name: instituteName).save(flush: true)
		}
		
		// create admin role if it doesn't exist yet
		if(Role.findByName("admin") == null) {
			def adminRole = new Role(name: "admin")
			adminRole.addToPermissions("*:*")
			adminRole.save(flush: true)
		}
		
		Role adminRole = Role.findByName("admin");
		Institute institute = Institute.findByName(instituteName);
		
		def nora = new User(email:"nnording@uv.uni-kiel.de", passwordHash: new Sha256Hash("glimmlampe").toHex(), firstName: "Nora", lastName: "Nording", institute: institute)
		nora.addToRoles(adminRole)
		nora.save(flush: true)

		def sebastian = new User(email:"sstarzynski@uv.uni-kiel.de", passwordHash: new Sha256Hash("glimmlampe").toHex(), firstName: "Sebastian", lastName: "Starzynski", institute: institute)
		sebastian.addToRoles(adminRole)
		sebastian.save(flush: true)
	}
}
