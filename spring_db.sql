DROP DATABASE IF EXISTS spring_db;
CREATE DATABASE spring_db;
USE spring_db;
CREATE TABLE account(
	id INT(11) NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(32) DEFAULT NULL,
	money DOUBLE DEFAULT NULL,
	PRIMARY KEY(id) 
);

INSERT INTO account(id, NAME, money) vlues(1, 'tom', 1000), (2,'jerry', 1000);