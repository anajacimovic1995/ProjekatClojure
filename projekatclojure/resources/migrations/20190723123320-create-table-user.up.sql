CREATE TABLE IF NOT EXISTS user (
   id INT NOT NULL AUTO_INCREMENT,
   imePrezime VARCHAR(255) NOT NULL,
   username VARCHAR(255) NOT NULL UNIQUE,
   password VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   rola VARCHAR(255) NOT NULL,
   PRIMARY KEY (id)
);

-- name: add-user
INSERT INTO USER
(imePrezime, username, PASSWORD, email, rola) VALUES 
('Ana Jacimovic', 'ana', '12345', 'ana@gmail.com', '`obican`'),
('Ana Jacimovic', 'ana1', '12345', 'ana@gmail.com', '`obican`');
