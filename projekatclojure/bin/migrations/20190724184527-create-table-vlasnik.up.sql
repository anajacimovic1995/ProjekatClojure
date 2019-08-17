CREATE TABLE `vlasnik` (
  `vlasnikID` int(11) NOT NULL AUTO_INCREMENT,
  `imePrezime` varchar(255) NOT NULL,
  `adresa` varchar(255) NOT NULL,
  `kontakt` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`vlasnikID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `vlasnik` */

insert  into `vlasnik`(`vlasnikID`,`imePrezime`,`adresa`,`kontakt`,`username`,`password`) values 
(1,'Borivoje Boric','Pariske Komune','066665555','boki','123'),
(2,'Bogdan Nisic','Steve Vargasa','067656565','boba','000'),
(4,'Nikola Nikolic','Crveni Krst','068767676','nikola','123'),
(5,'Biljana Bojic','Vojvode Stepe','064202020','bilja','123'),
(6,'Sonja Soric','Bulevar Oslobodjenja','060090909','sonja','123'),
(7,'Valentina Vasic','Dimitrija Tucovica','065454545','vale','123'),
(8,'Ivana Kovacevic','Banovo Brdo','09988888','ivanakovacevic','123'),
(9,'Ivana Mitrovic','Dimitrija Tucovica','061123456','ivanamitrovic','123');