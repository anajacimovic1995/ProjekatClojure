CREATE TABLE `stan` (
  `stanID` int(10) NOT NULL AUTO_INCREMENT,
  `link` varchar(255) NOT NULL,
  `sajt` varchar(255) NOT NULL,
  `kvadratura` double NOT NULL,
  `cenaEvri` double NOT NULL,
  `lokacija` varchar(255) NOT NULL,
  `slobodan` tinyint(4) NOT NULL,
  `terasa` tinyint(4) NOT NULL,
  `garazaParking` varchar(255) NOT NULL,
  `slika` varchar(255) NOT NULL,
  `opis` varchar(255) NOT NULL,
  `prodavacID` int(11) DEFAULT NULL,
  PRIMARY KEY (`stanID`),
  KEY `prodavacID` (`prodavacID`),
  CONSTRAINT `stan_ibfk_2` FOREIGN KEY (`prodavacID`) REFERENCES `vlasnik` (`vlasnikID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

/*Data for the table `stan` */

insert  into `stan`(`stanID`,`link`,`sajt`,`kvadratura`,`cenaEvri`,`lokacija`,`slobodan`,`terasa`,`garazaParking`,`slika`,`opis`,`prodavacID`) values 
(1,'https://www.nekretnine24.rs/agencije/grad-beograd/stari-grad/683/prodaja-stana-u-centru-beograda-110m2','4zida',44,44000,'Mirijevo',1,1,'garaza','https://cityexpert.rs/blog/sites/default/files/slika/prodaja-stanova-beograd-centar.jpg','odlican stan',1),
(3,'https://www.nekretnine24.rs/agencije/grad-beograd/stari-grad/683/prodaja-stana-u-centru-beograda-110m2','nekretnie.rs',25,50000,'Djeram',0,0,'garaza','https://api.4zida.rs/resize/5baa8d76ad85026bb3375e63/crop/ef67f7c2d86352c2c42e19d20f881f53/jove-ilica-beograd-autox270.jpeg','super stan',1),
(16,'https://www.nekretnine24.rs/agencije/grad-beograd/stari-grad/683/prodaja-stana-u-centru-beograda-110m2','4zida',90,100000,'vozdovac',0,0,'oba','https://www.nekretnine24.rs/slike/683/1-11.JPG','vrhunskog kvaliteta',1),
(18,'https://www.4zida.rs/prodaja/stanovi/beograd/oglas/bulevar-zorana-djindjica/5ce93d4c9a3091634d146554','4zida',55,83000,'Novi Beograd',0,1,'Nema','https://api.4zida.rs/resize/5ce93d4c9a3091634d146554/crop/f40ee694989b3e2161be989e7b9907fc/bulevar-zorana-djindjica-800x480.jpeg','Ova prestižna nepokretnost nalazi se u samom srcu Novog Beograda. Svojim položajem i kvalitetom gradnje opravdava epitet porodicnog doma. ',7),
(19,'https://cityexpert.rs/prodaja/stan/36191/cetvorosoban-davida-pajica-vozdovac','cityexpert',120,198000,'Dusanovac',0,1,'Nema','https://img.cityexpert.rs/sites/default/files/styles/1920x/public/image/_-_0864_-_20190813.jpg',' U cenu nije ukljuceno parking mesto. Postoji mogucnost zakupa parking mesta.',6),
(20,'https://cityexpert.rs/prodaja/stan/36203/dvosoban-darinke-radovic-cukarica','cityexpert',56,53000,'Cukarica',0,1,'Garaza','https://img.cityexpert.rs/sites/default/files/styles/1920x/public/image/cityexpert.rs_-_nekretnina_id_-_36203_-_0730_-_20190809.jpg','Zgrada poseuje lift i interfon. Stan je na 11 spratu.',6),
(21,'https://cityexpert.rs/prodaja/stan/35854/dvosoban-radoja-dakica-novi-beograd','cityexpert',43,75000,'Blok 7a, Novi Beograd',0,0,'Slobodan parking zgrade','https://img.cityexpert.rs/sites/default/files/styles/1920x/public/image/cityexpert.rs_-_nekretnina_id_-_35854_-_5604_-_20190711_0.jpg','Grejanje po potrosnji',4),
(22,'https://cityexpert.rs/prodaja/stan/31220/cetvorosoban-gornjogradska-zemun','cityexpert',103,195000,'Zemun',0,0,'Bez garaze, slobodan parking','https://img.cityexpert.rs/sites/default/files/styles/1920x/public/image/cityexpert.rs_-_nekretnina_id_-_31220_-_7187_-_20190808.jpg','Visok infostan',4),
(23,'https://cityexpert.rs/prodaja/stan/18140/dvosoban-jove-ilica-vozdovac','Cityexert',83,140000,'Vozdovac',0,0,'Garaza','https://img.cityexpert.rs/sites/default/files/styles/1920x/public/image/cityexpert.rs_-_nekretnina_id_-_18140_-_6150_-_20161007_0.jpg','Zgrada novije gradnje na Vozdovcu u ulici Jove Ilica .Ima površinu od 71m2 + terasu povrsine 12m2 koja je zastakljena. Stan se prodaje sa namestajem. ',2);

