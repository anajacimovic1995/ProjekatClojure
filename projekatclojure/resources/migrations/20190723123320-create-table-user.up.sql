CREATE TABLE IF NOT EXISTS user (
   id INT NOT NULL AUTO_INCREMENT,
   imePrezime VARCHAR(255) NOT NULL,
   username VARCHAR(255) NOT NULL UNIQUE,
   password VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   PRIMARY KEY (id)
);
--;;
INSERT INTO user
(imePrezime, username, password, email) VALUES
('Ana Jacimovic', 'ana', '12345', 'ana@gmail.com');