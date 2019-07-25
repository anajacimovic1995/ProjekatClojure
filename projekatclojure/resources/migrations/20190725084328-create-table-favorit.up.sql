CREATE TABLE IF NOT EXISTS favorit (
   favoritID INT NOT NULL AUTO_INCREMENT,
   userID INT NOT NULL,
   stanID INT NOT NULL,
   PRIMARY KEY (favoritID),   
   FOREIGN KEY (userID) REFERENCES `user`(userID),
   FOREIGN KEY (stanID) REFERENCES stan(stanID) 
);

INSERT INTO `favorit`
(userID, stanID) VALUES (1,1);