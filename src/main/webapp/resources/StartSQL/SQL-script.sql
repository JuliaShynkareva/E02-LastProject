DROP DATABASE IF EXISTS applicationservice;

CREATE DATABASE applicationservice;

USE applicationservice;

CREATE TABLE applicationservice.department
(
  id_department  INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name   VARCHAR(40) NOT NULL
);

CREATE TABLE applicationservice.status
(
  id_status  INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name   VARCHAR(30) NOT NULL
);


create table users(
	username varchar(50) not null primary key,
	password varchar(150) not null,
	enabled boolean not null
);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

INSERT INTO `applicationservice`.`users` (`username`, `password`, `enabled`) VALUES ('user', '$2a$11$fbn9vMwYaMRftwv6kDIcCOUnOzF7iENqOiPJckR9cyv5zmh/MDB4C', '1');
INSERT INTO `applicationservice`.`users` (`username`, `password`, `enabled`) VALUES ('system', '$2a$11$3og9/dhp/I.aQyYbdKglrOrQ99dLiOZ6g8qX7c2ETfiROYimEbCMy', '1');
INSERT INTO `applicationservice`.`users` (`username`, `password`, `enabled`) VALUES ('web', '$2a$11$DynZ38VJ6efpfq3fQaFi6u3WImt3hFnPJZmeJC3118kQHFLTnpOA6', '1');
INSERT INTO `applicationservice`.`users` (`username`, `password`, `enabled`) VALUES ('sap', '$2a$11$1pf0W27b0.rMJfT5jEJJ6.wigVuTlWZkKQGb5K6fq9g6oHAvvXE/a', '1');
INSERT INTO `applicationservice`.`users` (`username`, `password`, `enabled`) VALUES ('qad', '$2a$11$fwueal3CCvy7zXgYjoBgi.QK7.7Lz/d3gPZDB7IYE2qr5DMLnu56y', '1');
INSERT INTO `applicationservice`.`users` (`username`, `password`, `enabled`) VALUES ('admin', '$2a$11$mr3vmw4r5hBz80AI766j6.2TEkzAbcxUko6kZVs0VhnFjybTp59je', '1');

INSERT INTO `applicationservice`.`authorities` (`username`, `authority`) VALUES ('user', 'ROLE_USER');
INSERT INTO `applicationservice`.`authorities` (`username`, `authority`) VALUES ('qad', 'ROLE_QAD');
INSERT INTO `applicationservice`.`authorities` (`username`, `authority`) VALUES ('sap', 'ROLE_SAP');
INSERT INTO `applicationservice`.`authorities` (`username`, `authority`) VALUES ('system', 'ROLE_SYSTEM');
INSERT INTO `applicationservice`.`authorities` (`username`, `authority`) VALUES ('web', 'ROLE_WEB');
INSERT INTO `applicationservice`.`authorities` (`username`, `authority`) VALUES ('admin', 'ROLE_ADMIN');

CREATE TABLE applicationservice.client
(
  id_client INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  lastname VARCHAR(30) NOT NULL,
  surname VARCHAR(30) NOT NULL,
  login VARCHAR(30) NOT NULL,
  FOREIGN KEY (login) REFERENCES users (username) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE applicationservice.employee
(
  id_employee INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  lastname VARCHAR(30) NOT NULL,
  surname VARCHAR(30) NOT NULL,
  department_id INT NOT NULL,
  login VARCHAR(30) NOT NULL,
  FOREIGN KEY (department_id) REFERENCES department (id_department) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (login) REFERENCES users (username) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE applicationservice.application
(
  id_application INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  description VARCHAR(1024),
  comment VARCHAR(1024),
  application_date DATETIME NOT NULL,
  change_date DATETIME,
  department_id INT,
  status_id INT NOT NULL,
  employee_id INT,
  client_id INT,
  FOREIGN KEY (department_id) REFERENCES department (id_department) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (status_id) REFERENCES status (id_status) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (employee_id) REFERENCES employee (id_employee) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (client_id) REFERENCES client (id_client) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO `applicationservice`.`status` (`name`) VALUES ('Открыта');
INSERT INTO `applicationservice`.`status` (`name`) VALUES ('В работе');
INSERT INTO `applicationservice`.`status` (`name`) VALUES ('Выполнена');

INSERT INTO `applicationservice`.`department` (`name`) VALUES ('Отдел разработки программных систем');
INSERT INTO `applicationservice`.`department` (`name`) VALUES ('Отдел веб-разработок');
INSERT INTO `applicationservice`.`department` (`name`) VALUES ('Отдел SAP-консалтинга');
INSERT INTO `applicationservice`.`department` (`name`) VALUES ('Отдел контроля качества');

INSERT INTO `applicationservice`.`client` (`name`, `lastname`, `surname`, `login`) VALUES ('Екатерина', 'Олеговна', 'Селизнёва', 'user');

INSERT INTO `applicationservice`.`employee` (`name`, `lastname`, `surname`, `department_id`, `login`) VALUES ('Александр', 'Владимирович', 'Корсаков', '1', 'system');
INSERT INTO `applicationservice`.`employee` (`name`, `lastname`, `surname`, `department_id`, `login`) VALUES ('Петр', 'Петрович', 'Петров', '2', 'web');
INSERT INTO `applicationservice`.`employee` (`name`, `lastname`, `surname`, `department_id`, `login`) VALUES ('Евгений', 'Адамович', 'Скруев', '3', 'sap');
INSERT INTO `applicationservice`.`employee` (`name`, `lastname`, `surname`, `department_id`, `login`) VALUES ('Алекандр', 'Андреевич', 'Иванов', '4', 'qad');

CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);
