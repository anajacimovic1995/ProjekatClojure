CREATE TABLE `favorit` (
  `favoritID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) DEFAULT NULL,
  `stanID` int(11) DEFAULT NULL,
  PRIMARY KEY (`favoritID`),
  KEY `userID` (`userID`),
  KEY `stanID` (`stanID`),
  CONSTRAINT `favorit_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `favorit_ibfk_2` FOREIGN KEY (`stanID`) REFERENCES `stan` (`stanID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

/*Data for the table `favorit` */

insert  into `favorit`(`favoritID`,`userID`,`stanID`) values 
(9,1,3),
(13,10,1),
(30,7,1),
(31,7,1),
(32,7,16),
(33,7,16),
(34,7,1),
(35,18,23),
(36,18,19);
