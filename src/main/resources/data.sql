INSERT INTO role (role) VALUES ('USER');
INSERT INTO role (role) VALUES ('MODERATOR');

INSERT INTO user (rolefk, username, password, wallet) VALUES ('2', 'gipfeli', '$2a$10$FyhAaMvvC7IpeMjP9UZUZeoSYOhnmFhe6NdIhS87v8KokLG4sSo5i', '1000');
INSERT INTO user (rolefk, username, password, wallet) VALUES ('1', 'croissant', '$2a$10$FyhAaMvvC7IpeMjP9UZUZeoSYOhnmFhe6NdIhS87v8KokLG4sSo5i', '1000');
INSERT INTO user (rolefk, username, password, wallet) VALUES ('1', 'silsergipfeli', '$2a$10$FyhAaMvvC7IpeMjP9UZUZeoSYOhnmFhe6NdIhS87v8KokLG4sSo5i', '1000');

INSERT INTO category (category) VALUES ('funny');
INSERT INTO category (category) VALUES ('not-funny');
INSERT INTO category (category) VALUES ('motivation');
INSERT INTO category (category) VALUES ('random');
INSERT INTO category (category) VALUES ('melancholic');

INSERT INTO motto (categoryfk, ownerfk, motto, price) VALUES ('3', '1', 'Geld ist nicht alles, aber alles kostet Geld', '20');
INSERT INTO motto (categoryfk, ownerfk, motto, price) VALUES ('2', '2', 'Was geht was geht', '30');
INSERT INTO motto (categoryfk, ownerfk, motto, price) VALUES ('3', '1', 'Ein AMG macht dich glücklicher als eine Frau.', '100');
INSERT INTO motto (categoryfk, ownerfk, motto, price) VALUES ('1', '3', '3 Backpfeifen', '1100');