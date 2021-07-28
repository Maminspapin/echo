# echo

БД MySQL запущена отдельно с помощью Docker.

- docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:latest
- docker exec -it mysql bash
- mysql -u root -p
- CREATE DATABASE test;
- USE test;
- CREATE TABLE properties(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(35),
    token varchar(100),
    PRIMARY KEY (id)
    );
- INSERT INTO test(name, token) VALUES('echo','токен');
- CREATE TABLE keyboard (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(15),
    PRIMARY KEY (id)
);
-CREATE TABLE button (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(15),
    callback_text varchar(100),
    PRIMARY KEY (id)
);
- CREATE TABLE keyboard_button (
    keyboard_id int NOT NULL,
    button_id int NOT NULL,
    PRIMARY KEY (keyboard_id, button_id),
    FOREIGN KEY (keyboard_id) REFERENCES keyboard(id),
    FOREIGN KEY (button_id) REFERENCES button(id)
);
