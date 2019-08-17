CREATE TABLE `projekat` (
  `projekatID` int(11) NOT NULL AUTO_INCREMENT,
  `tip` varchar(255) NOT NULL,
  `naziv` varchar(255) NOT NULL,
  `lokacija` varchar(255) NOT NULL,
  `opis` varchar(255) NOT NULL,
  `slika` varchar(255) NOT NULL,
  `investitorID` int(11) DEFAULT NULL,
  `godinaZavrsetka` int(11) NOT NULL,
  PRIMARY KEY (`projekatID`),
  KEY `investitorID` (`investitorID`),
  CONSTRAINT `projekat_ibfk_1` FOREIGN KEY (`investitorID`) REFERENCES `vlasnik` (`vlasnikID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `projekat` */

insert  into `projekat`(`projekatID`,`tip`,`naziv`,`lokacija`,`opis`,`slika`,`investitorID`,`godinaZavrsetka`) values 
(1,'novogradnja','East Side','Ustanicka','Nekoliko faza gradnje. Od jednosobnih do visesobnih stanova.','https://www.east-side.rs/wp-content/uploads/2018/11/slide1-1.jpg',1,2018),
(5,'novogradnja','Koste Novakovica 7','Zvezdara','Vrhunska stolarija','https://novazgrada.rs/files/artbud/styles/boom-property/public/images/property/koste-novakovica-5-1.jpg?itok=b-aXqWGo',1,2021),
(6,'novogradnja','Vozdove kapije','Vozdovac','Kondominijum vrhunskog kvalliteta','https://novazgrada.rs/files/artbud/styles/boom-property/public/images/property/cerska-107-3.jpg?itok=ChzgliAW',1,2022),
(7,'novogradnja','Nodilova 19','Ada Ciganlija','Objekat je spratnosti 2Po + Pr + 5 + Ps. U zgradi se nalazi 24 stana, kao i 27 garaznih mesta. Dostupni su dvosobni, trosobni i cetvorosobni stanovi.','https://i0.wp.com/bgnovogradnja.rs/wp-content/uploads/2018/12/nodilova-19_glavna.jpg?w=1500&ssl=1',7,2019),
(9,'novogradnja','Gospodara Vucica 29','Vracar','Moderna šestospratnica sa obezbedjenim garaznim parking mestom za svaki stan u podzemnoj garazi. U suterenu se nalazi atelje koji se može koristiti i kao stambeni prostor. ','https://novazgrada.rs/files/artbud/styles/boom-property/public/images/property/gospodara-vucica-29-2.jpg?itok=bjCrm0xf',5,2019),
(10,'novogradnja','Kapija Vracara','Vracar','Stambeno- poslovni objekat „Kapija Vracara“ nalazi se u Beogradu, u ulici Juzni Bulevar 112 na Vracaru.  Predstavlja zonu visoko estetskog kvaliteta zahvaljujuci urbanistickoj postavci i samoj konfiguraciji terena ovog dela grada.','https://novazgrada.rs/files/artbud/styles/boom-property/public/images/property/kapija-vracara-1.jpg?itok=C7svyT44',5,2018),
(11,'novogradnja','Park Šumice','Vozdovac','dealno mesto za život, okruženo zelenilom, zašticeno od gradske vreve, pravu oazu mira i lepote u urbanoj sredini, predstavlja objekat – Park Šumice.','https://novazgrada.rs/files/artbud/styles/boom-property/public/images/property/park-sumice-5.jpg?itok=Obc_XX_s',5,2018),
(12,'novogradnja','Strumicka 11','Lekino brdo','Stambeno-poslovni objekat na Lekinom Brdu, Voždovac. ','https://novazgrada.rs/files/artbud/styles/boom-property/public/images/property/strumicka-11-1.jpg?itok=curb1Zmm',6,2019),
(13,'novogradnja','Vojvode Blazete','Zvezdara Olimp','Stambeni objekat, spratnosti Po+Su+P+4+Ps, u ul. Vojvode Blazete na Zvezdari sa 18 stanova parkingom u garazi i na otvorenom. ','https://novazgrada.rs/files/artbud/styles/image-not-styles/public/images/property/vojvode-blazete-40-4.jpg?itok=1GH6McT7',4,2018),
(14,'novogradnja','AlphaCity','Zvezdara,Zeleno Brdo','Novoizgradjeni kompleks na Zvezdari, u Živka Davidovica 81, jedan je od retkih u ovom delu Beograda koji funkcioniše kao mali grad sa svim pogodnostima koje su potrebne porodici i savremenim uslovima života.','https://novazgrada.rs/files/artbud/styles/boom-property/public/images/property/alphacity3.jpg?itok=u2NUEiqj',2,2019),
(15,'u izgradnji','Sakura Park','Novi Beograd','Pruza najsavremeniji nacin zivota.','https://sakurapark.rs/wp-content/uploads/2018/09/sakura-1.jpg',10,2020);
