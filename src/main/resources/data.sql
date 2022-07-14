INSERT INTO role (role) VALUES ('USER');
INSERT INTO role (role) VALUES ('MODERATOR');

INSERT INTO user (rolefk, username, password, wallet) VALUES ('2', 'gipfeli', '$2a$10$FyhAaMvvC7IpeMjP9UZUZeoSYOhnmFhe6NdIhS87v8KokLG4sSo5i', '1000');
INSERT INTO user (rolefk, username, password, wallet) VALUES ('1', 'croissant', '$2a$10$FyhAaMvvC7IpeMjP9UZUZeoSYOhnmFhe6NdIhS87v8KokLG4sSo5i', '1000');
INSERT INTO user (rolefk, username, password, wallet) VALUES ('1', 'silsergipfeli', '$2a$10$FyhAaMvvC7IpeMjP9UZUZeoSYOhnmFhe6NdIhS87v8KokLG4sSo5i', '1000');

INSERT INTO category (category) VALUES ('funny');
INSERT INTO category (category) VALUES ('not-funny');
INSERT INTO category (category) VALUES ('motivation');

INSERT INTO motto (categoryfk, ownerfk, motto, price) VALUES ('1', '1', 'Erstes Motto', '20');