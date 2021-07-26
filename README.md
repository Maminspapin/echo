# echo

БД MySQL запущена отдельно с помощью Docker.

- docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:latest
- docker exec -it mysql bash
- mysql -u root -p
- create database test;
- use test;
- CREATE TABLE properties(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(35),
    token varchar(100),
    PRIMARY KEY (id)
    );
