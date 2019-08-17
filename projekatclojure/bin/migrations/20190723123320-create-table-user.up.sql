CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `imePrezime` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `rola` varchar(255) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`userID`,`imePrezime`,`username`,`password`,`email`,`rola`) values 
(1,'Ana Jacimovic','ana','123','ana@gmail.com','kupac'),
(7,'Ivan Ivanovic','ivanivanovic','123','ivana@gmail.com','kupac'),
(10,'Verica Vekovic','verica','123','verica@gmail.com','kupac'),
(11,'Admin','admin','admin','admin@gmail.com','admin'),
(13,'Srdjan Srdic','srki','123','srki@gmail.com','kupac'),
(14,'Bojan Milovanovic','bojanmilovanovic','123','bojan@gmail.com','kupac'),
(15,'Stefan Stefanovic','stef','123','stef@gmail.com','kupac'),
(16,'Stefan Novakovic','stefan','123','stefan@gmail.com','kupac'),
(17,'Boris Milic','boris','123','boris@gmail.com','kupac'),
(18,'Milica Milic','mici','123','mici@gmail.com','kupac');
