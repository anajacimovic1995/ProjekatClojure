CREATE TABLE IF NOT EXISTS `user` (
   userID INT NOT NULL AUTO_INCREMENT,
   imePrezime VARCHAR(255) NOT NULL,
   username VARCHAR(255) NOT NULL UNIQUE,
   password VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   rola VARCHAR(255) NOT NULL,
   PRIMARY KEY (userID)
);

--;;
INSERT INTO `user`
(imePrezime, username, password, email, rola) VALUES 
('Ana Jacimovic', 'ana', '12345', 'ana@gmail.com', '`kupac`'),
('Ana Jacimovic', 'ana1', '12345', 'ana@gmail.com', '`kupac`'),
('bogdan nisic', 'boba', '123', 'boba@gmail.com', '`kupac`'),
('olivera kordic', 'olja', '123', 'olja@gmail.com', '`kupac`');
