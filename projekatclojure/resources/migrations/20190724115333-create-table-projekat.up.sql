CREATE TABLE IF NOT EXISTS projekat (
   projekatID INT NOT NULL AUTO_INCREMENT,
   tip VARCHAR(255) NOT NULL,
   naziv VARCHAR(255) NOT NULL,
   lokacija VARCHAR(255) NOT NULL,
   opis VARCHAR(255) NOT NULL,
   slika VARCHAR(255) NOT NULL,
   investitorID INT NOT NULL,
   godinaZavrsetka INT NOT NULL,
   PRIMARY KEY (projekatID),
   FOREIGN KEY (investitorID) REFERENCES vlasnik(vlasnikID) 
);

--;;
