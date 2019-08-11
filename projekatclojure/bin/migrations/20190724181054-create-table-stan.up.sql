CREATE TABLE IF NOT EXISTS stan (
   stanID INT NOT NULL AUTO_INCREMENT,
   link VARCHAR(255) NOT NULL,
   sajt VARCHAR(255) NOT NULL,
   kvadratura DOUBLE NOT NULL,
   cenaEvri DOUBLE NOT NULL,
   lokacija VARCHAR(255) NOT NULL,
   slobodan BOOLEAN NOT NULL,
   terasa BOOLEAN NOT NULL,
   garazaParking VARCHAR(255) NOT NULL,
   slika VARCHAR(255) NOT NULL,
   opis VARCHAR(255) NOT NULL,   
   prodavacID INT NOT NULL,
   PRIMARY KEY (stanID),
   FOREIGN KEY (prodavacID) REFERENCES vlasnik(vlasnikID) 
);

--;;
INSERT INTO `stan` (link, sajt, kvadratura, cenaEvri, lokacija, slobodan, terasa, garazaParking, slika, opis, prodavacID) VALUES
('url','nekretnie.rs','25','50000','Djeram','0','0','garaza','url','super stan','1');
