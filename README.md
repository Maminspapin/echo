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
    name varchar(200),
    PRIMARY KEY (id)
); 
-  CREATE TABLE button (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(200),
    callback_text varchar(200),
    keyboard_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (keyboard_id) REFERENCES keyboard(id)
);
