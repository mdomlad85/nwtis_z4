# Vježba 13. / Zadaća 4. - Opis
Naziv: enterprise aplikacija za rad s bazom podataka putem ORM uz korištenje JPA uz korisničku stranu s JSF na bazi predloška i korištenje Ajax-a.

Enterprise aplikacija služi za rad s bazom podataka putem ORM uz korištenje JPA (bez SQL, koristi se Criteria API), uz korisničku stranu s JSF na bazi predloška, dodavanja i ažuriranje adresa, preuzimanje prognoza, pregled i filtriranje dnevnika, pregled i filtriranje promjena, sve opreracije provode se Ajax-om. Enterprise aplikacija pod nazivom {LDAP_korisničko_ime}_zadaca_4 sastoji se od tri modula:

{LDAP_korisničko_ime}_zadaca_4_1: EJB modul za podatkovni sloj s JPA
{LDAP_korisničko_ime}_zadaca_4_2: EJB modul za poslovnu logiku i korištenje EJB podatkovnog sloja
{LDAP_korisničko_ime}_zadaca_4_3: Web modul za korisničko sučelje
Svaka akcija korisnika treba biti zapisana u tablicu dnevnik. Svi prikazi trebaju imati abecedni popis elemenata. 

Postupak počinje unosom novog IoT uređaja (id, naziv, adresa) i pregledom postojećih IoT uređaja (u obliku padajućeg izbornika) iz ORM (tablica uredjaji). Dodavanje novog IoT uređaja (putem gumba Dodaj IoT uređaj) Ajaxom pokreće preuzimanje lokacijskih podataka za upisanu adresu, spremanje objekta u ORM (upis u tablicu uredjaji), spremanje objekta u ORM (tablica promjene) i ažuriranjem pregleda raspoloživih IoT uređaja. Iz raspoloživih IoT uređaja može se odabrati jedan ili više IoT uređaja te (putem gumba Preuzmi) Ajaxom dodati  u popis odabranih IoT uređaja. IoT uređaj(i) koji se odabire(u) više se ne pojavljuje u pregledu raspoloživih IoT uređaja. Moguće je u popisu raspoloživih IoT uređaja odabrati jedan i pokrenuti njegovo ažuriranje (putem gumba Ažuriraj) u istom pogledu. Dio za ažuriranje je to sada bio sakriven. Nakon ažuriranja podataka (putem gumba Upiši) ažurira se objekt u ORM (tablica uredjaji), sprema se objekt u ORM (tablica promjene), sakriva se dio za ažuriranje i vraća se na odabir IoT uređaja. Kod popisa odabranih IoT uređaja može se maknuti (putem gumba Vrati) jedan ili više IoT uređaja putem Ajaxa (i tada se on(i) ponovno pojavljuje(u) u pregledu raspoloživih IoT uređaja). Nakon pokretanja prognoza (putem gumba Prognoze) putem Ajaxa, preuzimaju se prognoze vremena za sve odabrane IoT uređaje i 5 dana prognoze. Gumb Prognoze mijenja naziv u Zatvori prognoze. Zatim se prikazuju podaci prognoza (IoT uređaj, adresa iz prognoze, datum, temp,...) u bloku s tablicom (taj dio je prethodno bio sakriven) i nakon završetka pregleda prognoza (putem gumba Zatvori prognoze) cijeli blok se sakriva. Pregled dnevnika obavlja se u posebnom pogledu. Mogu se unijeti podaci za filtiranje na bazi; vremena od-do, ipadrese, trajanja i statusa. Može se unijeti od ni jednog pa do svih podataka. Nakon pokretanja pregleda (putem gumba Prikaži) prikazuju se filtirani podaci iz dnevnika. Pregled promjena obavlja se u posebnom pogledu. Mogu se unijeti podaci za filtiranje na bazi; id, naziv. Može se unijeti od ni jednog pa do svih podataka. Nakon pokretanja pregleda (putem gumba Prikaži) prikazuju se filtirani podaci iz promjena.

Klase i metode trebaju biti komentirane u javadoc formatu. Projekt se isključivo treba predati u formatu NetBeans projekta. Prije predavanja projekta potrebno je napraviti Clean na projektu. Zatim cijeli projekt sažeti u .zip (NE .rar) format s nazivom Prije predavanja projekta potrebno je napraviti Clean na projektu. Zatim cijeli projekt sažeti u .zip (NE .rar) format s nazivom {LDAP_korisničko_ime}_zadaca_4.zip i predati u Moodle. Uključiti izvorni kod, primjere datoteka konfiguracijskih podataka i popunjeni obrazac za zadaću pod nazivom {LDAP_korisničko_ime}_zadaca_4.doc (u korijenskom direktoriju projekta).

Naziv projekta: {LDAP_korisničko_ime}_zadaca_4

Kreiranje direktorija {LDAP_korisničko_ime}_zadaca_4. U nastavku se direktorij za vježbu simbolički označava kao {vježba}.
startati Java DB
kreirati Java DB bazu podataka (Services/Java DB/Create Database...) nwtis_zadaca_4, korisničko ime nwtis, lozinka foi2017 (Slika 1)
otvoriti vezu prema novoj bazi podataka
preuzeti sql za kreiranje tablice uredaji iz zadaće 2, kopirati sadržaj i izvršiti (u otvorenoj vezi na bazu podataka odabrati Tables/Execute Command...)
preuzeti sql za punjenje tablice uredaji iz zadaće 2, kopirati sadržaj i izvršiti (u otvorenoj vezi na bazu podataka odabrati Tables/Execute Command...)
preuzeti datoteku promjene_JavaDB.sql, kopirati sadržaj i izvršiti (u otvorenoj vezi na bazu podataka odabrati Tables/Execute Command...)
preuzeti datoteku dnevnik_JavaDB.sql, kopirati sadržaj i izvršiti (u otvorenoj vezi na bazu podataka odabrati Tables/Execute Command...)
provjeriti sadržaj sheme  (kao na Slici 2.)
{LDAP_korisničko_ime}_zadaca_4_1: Kreiranje modula za podatkovni sloj s EJB: EntityBean - JPA

Kreiranje projekta {LDAP_korisničko_ime}_zadaca_4_1 kao Java EE/EJB Module , server Glassfish, Java EE verzija: Java EE 7
kreirati pakete org.foi.nwtis.{LDAP_korisnik}.ejb.eb, org.foi.nwtis.{LDAP_korisnik}.ejb.sb
kreirati perzistantnu jedinicu (New/Other/Persistence/Persistence Unit) . Slike 3, 4 i 5 prikazuju aktivnosti koje se provode: određivanje naziva: zadaca_4_1PU, kreiranje izvora podataka (Data source) pod nazivom jdbc/NWTiS_2017 za ranije kreranu vezu prema bazi podataka nwtis_zadaca_4. Slika 6 prikazuje podatke kreirane perzistente jedinice. Slike 7 i 8 pokazuju kreirane datoteke opisnika (persistence.xml i glassfish-resources.xml)
kreirati shemu baze podataka (New/Other/Persistence/Database Schema). Naziv: NWTiS_2017 (Slika 7) na src/conf i odabrati ranije kreranu vezu prema bazi podataka nwtis_zadaca_4 (Slika 8). Slijedi odabir svih tablica (Slika 9). Struktura sheme baze podataka prikazana je na slici 10. Služi nam kao pomoć i nije nužno to obavljati. 
kreirati klase na entitete (New/Other/Persistence/Entity Classes from Database) na org.foi.nwtis.{LDAP_korisnik}.ejb.eb. Slijedi odabir slih tablica s time da se može odabrati kao osnova izvor podataka jdbc/NWTiS_2017 (Slika 11) ili shema baze podataka NWTiS_2017 ako smo ju kreirali u projektu (Slika 12). Preslikavanja tablica baze podataka u klase prikazana je na slici 13. I na kraju se određuju elementi preslikavanja (Slika 14)
kreirati session bean-nove (Facade) za klase entiteta (New/Other/Persistence/Session Beans for Entity Classes) na org.foi.nwtis.{LDAP_korisnik}.ejb.sb. Slijedi odabir klasa (Slika 15) za koje će se generirati fasade (Slika 16)
{LDAP_korisničko_ime}_zadaca_4_2: Kreiranje modula za poslovnu logiku i korištenje EJB: SessionBean (Stateful, Stateless i Singleton)

Kreiranje projekta {LDAP_korisničko_ime}_zadaca_4_2 kao Java EE/EJB Module , server Glassfish, Java EE verzija: Java EE 7
kreirati paket org.foi.nwtis.{LDAP_korisnik}.ejb.sb
kopirati pakete org.foi.nwtis.{LDAP_korisnik}.web.podaci, org.foi.nwtis.{LDAP_korisnik}.rest.klijenti iz {LDAP_korisnik}_zadaca_3_1
kreirati Stateless SessionBean MeteoIoTKlijent (New/Other/Enterprise JavaBeans/Session Bean/Session Type: Stateless/Create Interface (označiti ni jedan)) u org.foi.nwtis.{LDAP_korisnik}.ejb.sb
dodati privatnu varijablu sa svojim podacima (apiKey), koja preuzima vrijednost iz datoteke postavki.
kreirati poslovnu operaciju void postaviKorisnickePodatke(String apiKey) (Insert Code/Add Business Method...) koja pridružuje korisničke podatke privatnim varijablama
preuzeti klasu MeteoPrognoza.java i prilagoditi paket
u OWMKlijent dodati metodu public MeteoPrognoza[] getWeatherForecast(int id, String latitude, String longitude). Može se kreirati posebna klasa za podatke meteo prognoze. Na temelju metode getRealTimeWeather(...) napraviti poziv REST servisa openweathermap.org za prognozu vremena za 5 dana po 3 sata (http://openweathermap.org/forecast5) i prilagoditi podatke za traženi tip podatka u odgovoru.
u MeteoIoTKlijent kreirati poslovnu operaciju public Lokacija dajLokaciju(String adresa) (Insert Code/Add Business Method...)
operacija kreira objekt klase GMKlijent, poziva metodu getGeoLocation(adresa) i vraća lokaciju
kreirati poslovnu operaciju public MeteoPrognoza[] dajMeteoPrognoze(int id, String adresa) (Insert Code/Add Business Method...) . 
operacija kreira objekt klase klase OWMKlijent , poziva metodu dajLokaciju(String adresa) i s dobivenom lokacijom poziva metodu getWeatherForecast(...) 
{LDAP_korisničko_ime}_zadaca_4: Kreiranje Enterprise aplikacije

Kreiranje projekta {LDAP_korisničko_ime}_zadaca_4 kao Enterprise Application, server Glassfish, Java EE verzija: Java EE 7
otvoriti projekt
na Java EE Modules, Add Java EE Module... Označiti: {LDAP_korisničko_ime}_zadaca_4_1, {LDAP_korisničko_ime}_zadaca_4_2 i OK
kopirati datoteku glassfish-resources.xml iz projekta {LDAP_korisničko_ime}_zadaca_4_1 (Files/{LDAP_korisničko_ime}_zadaca_4_1/src/conf/META-INF) u projekt {LDAP_korisničko_ime}_zadaca_4 (Files/{LDAP_korisničko_ime}_zadaca_4/src/conf/)
{LDAP_korisničko_ime}_zadaca_4_3: Kreiranje web modula za korisničko sučelje

Kreiranje {LDAP_korisničko_ime}_zadaca_4_3 kao Java Web aplikaciju, za Enterprise aplikaciju: {LDAP_korisničko_ime}_zadaca_4, server Glassfish, Java EE verzija: Java EE 7 (Frameworks: JSF), Libraries/Server Library: JSF 2.0, Configuration/JSF Servlet URL pattern: /faces/* , Prefered Page Language: Facelets
kreirati paket org.foi.nwtis.{LDAP_korisnik}.web.zrna, org.foi.nwtis.{LDAP_korisnik}.rest.klijenti
kopirati paket org.foi.nwtis.{LDAP_korisnik}.web.podaci iz {LDAP_korisnik}_zadaca_4_2
u paket org.foi.nwtis.{LDAP_korisnik}.rest.klijenti kopirati klase MeteoPodaci i Lokacija iz {LDAP_korisnik}_zadaca_4_2
kreirati JSF konfiguracijsku datoteke (New/Other/JavaServer Faces/ JSF Faces Configuration)
kreirati predložak pod nazivom predlozak.xhtml (New/Others/JavaServer Faces/Facelets Template) Layout Style: CSS, izbor izgleda sa zaglavljem, podnožjem i lijevim izbornikom.  U <title>...</title> umetnuti <ui:insert name="naslov">Naslov</ui:insert> tako da se naslov može postaviti u pojedinom pogledu.  Za bottom ime prezime - NWTiS &copy; 2017. U <h:outputStylesheet name="./css..."> obrišite u name ./ tako da bude name="css/..."
kreirati JSF stranicu za korištenje predloška pod nazivom pregledIoT.xhtml (New/Others/JavaServer Faces/Facelets Template Client), odabrati predložak predlozak.xhtml, Generated Root Tag: <html>. Za naslov NWTiS - Pregled IoT uređaja i prognoza vremena. Sadržaj za top staviti isto kao naslov, za left poveznice <h: ...> na index.xhtml, na pregledIoT.xhtml, na pregledDnevnika.xhtml i na pregledPromjena.xhtml. Pogled služi za pregled i pretraživanje podataka o IoT uređajima i prognozama vremena
selektirati sadržaj pregledIoT.xhtml i kopirati u index.xhtml . Promijeniti potrebne elemente (naslov, poveznicu,...) 
kreirati JSF stranicu za korištenje predloška pod nazivom pregledDnevnika.xhtml (New/Others/JavaServer Faces/Facelets Template Client), odabrati predložak predlozak.xhtml, Generated Root Tag: <html> .... Pogled služi za pregled dnevnika. 
kreirati JSF stranicu za korištenje predloška pod nazivom pregledPromjena.xhtml (New/Others/JavaServer Faces/Facelets Template Client), odabrati predložak predlozak.xhtml, Generated Root Tag: <html> .... Pogled služi za pregled promjena podataka IoT uređaja. 
dodati navigaciju u faces-config.xml
izgraditi i izvršiti aplikaciju {LDAP_korisničko_ime}_zadaca_4. Provjeriti izgled za sve poglede.
kopirati paket org.foi.nwtis.{LDAP_korisnik}.web.kontrole iz {LDAP_korisnik}_zadaca_2. Obrisati klasu Poruka
kreirati JSF ManageBean OdabirIoTPrognoza (Session scope) koji implementira sučelje Serializable
dodati varijable za novi IoT, popis raspoloživih IoT, lista raspoloživih IoT za odabir, popis odabranih IoT, lista odabranih IoT za vraćanje, ažurirani IoT, lista prognoza, prikaz ažuriranja IoT, prikaz pregleda prognoza. Popisi imaju tip podatka List<Izbornik>, a liste imaju tip podatka List<String>
dodati settere i gettere za varijable
u pregledIoT.xhtml dodati obrazac (slika 19) s elementima za unos novog IoT, izbor IoT, pregled raspoloživih IoT, preged odabranih IoT, ažuriranje IoT i pregled prognoza. Predvidjeti element za poruke o pogreškama. Elementi koji trebaju biti sakriveni koriste atribut rendered koji putem UEL ispituje stanje svoje pridružene varijable (true, false). Taj oblik ima svoju manu. Druga oblik je da se u nadeđenom elementu (<h:panelGroup...>) koristi atribut "style" uz vrijednost "display:" nakon koje se pridružuju "none" kada se sakriva odnosno "block" kada se prikazuje. Vrijednost displaPotrebne su kontrole (gumb) za pojedine akcije. Za svaki gumb potrebno je dodati podršku za ajax (<f:ajax ..> pri čemu su važna dva atributa:
execute - kojim se određuje koje elemente (na bazi id) je potrebno osvježiti, učitati prije nego što se pozove akcija
render - kojim se određuju koje elemente je potrebno osvježiti nakon akcije
dodati projekt {LDAP_korisnicko_ime}_zadaca_4_3 u Enterprise aplikaciju ukoliko to nije napravljeno do sad
u OdabirIoTPrognoza dodati poziv EJBUredajiFacade (Insert Code .../Call Enterprise Bean i odabrati {LDAP_korisničko_ime}_zadaca_4_1/UredajiFacade)
u OdabirIoTPrognoza dodati poziv EJB UredajiFacade (Insert Code .../Call Enterprise Bean i odabrati {LDAP_korisničko_ime}_zadaca_4_2/MeteoIoTKlijent)
dodati metode koje će izvoditi akcije dodavanja novog IoT uređaja, ažuriranja odabranog IoT uređaja, dodavanje odabranog(ih) IoT u odabrane IoT, izbacivanje odabranog(ih) IoT iz popisa odabranih IoT, pregled prognoza za odabrane IoT uređaje itd
dodati kod kojim se preuzimaju podaci o IoT putem UredajFacade
dodati kod kojim se dodaje IoT putem UredjajiFacade
dodati kod kojim se ažurira IoT putem UredajiFacade.
dodati akcije u pregledIoT.xhtml
da bi se vidjele SQL komande koje izvršava EclipseLink može se dodati u persistence.xml (http://eclipse.org/eclipselink/ http://wiki.eclipse.org/EclipseLink/Examples/JPA/Logging)
         <properties>
            <property name="eclipselink.logging.level" value="FINE"/>        
            <property name="eclipselink.logging.level.sql" value="FINE"/>
            <property name="eclipselink.logging.parameters" value="true"/>            
            <property name="eclipselink.logging.logger" value="DefaultLogger"/>      
        </properties> 
u pregledDnevnika.xhtml dodati obrazac s elementima za filtriranje podataka: korisnik, ipadresa, trajanje, status. Potrebna je kontrola (gumb) za pokretanje pregleda uz filtriranje. Za gumb potrebno je dodati podršku za ajax (<f:ajax ..>
kreirati JSF ManageBean PregledDnevnika (Session scope) i koji implementira sučelje Serializable
dodati potrebne varijable za podatke iz dnevnika
dodati gettere i settere
dodati poziv EJB UredjajFacade (Insert Code .../Call Enterprise Bean i odabrati {LDAP_korisničko_ime}_zadaca_4_2/DnevnikFacade)
dodati metodu koja će izvoditi akciju preuzimanja podataka iz dnevnika
dodati kod kojim se preuzimaju podaci iz dnevnika putem DnevnikFacade
u pregledPromjena.xhtml dodati obrazac s elementima za filtriranje podataka: id, naziv. Potrebna je kontrola (gumb) za pokretanje pregleda uz filtriranje. Za gumb potrebno je dodati podršku za ajax (<f:ajax ..>
kreirati JSF ManageBean PregledPromjena (Session scope) i koji implementira sučelje Serializable
dodati potrebne varijable za podatke iz promjena
dodati gettere i settere
dodati poziv EJB PromjeneFacade (Insert Code .../Call Enterprise Bean i odabrati {LDAP_korisničko_ime}_zadaca_4_2/PromjeneFacade)
dodati metodu koja će izvoditi akciju preuzimanja podataka iz promjena
dodati kod kojim se preuzimaju podaci iz dnevnika putem PromjenaFacade
Preporučuju se:
za JSF http://elf.foi.hr/pluginfile.php/8314/mod_resource/content/0/DZone/rc058-010d-jsf2.pdf
za Ajax http://www.coreservlets.com/JSF-Tutorial/jsf2/#Ajax
za Criteria API http://docs.oracle.com/cd/E19798-01/821-1841/gjitv/index.html, http://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Querying/Criteria

![Slika 1](http://nwtis.foi.hr/NWTiS/slike/vjezba_13_zadaca_4/vjezba_13N_1.jpg)
![Slika 4](http://nwtis.foi.hr/NWTiS/slike/vjezba_13_zadaca_4/vjezba_13N_4.jpg)
