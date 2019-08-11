CREATE TABLE IF NOT EXISTS vlasnik (
   vlasnikID INT NOT NULL AUTO_INCREMENT,
   imePrezime VARCHAR(255) NOT NULL,
   adresa VARCHAR(255) NOT NULL UNIQUE,
   kontakt VARCHAR(255) NOT NULL,
   username VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   PRIMARY KEY (vlasnikID)
);

INSERT INTO `vlasnik`
(imePrezime, adresa, kontakt, username, password) VALUES 
('Neko', 'negde', '09876', 'neko', '1234'),
('mm', 'mm', '123', 'mm', 'mm');