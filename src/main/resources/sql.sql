--CREATE USER IT_MAN 
--    IDENTIFIED BY IT_MAN ;

CREATE SCHEMA `it_man` ;
CREATE USER 'IT_MAN' IDENTIFIED BY 'IT_MAN';




--Grant all PRIVILEGES   to IT_MAN;

GRANT ALL PRIVILEGES ON IT_MAN.* TO 'IT_MAN';

/*CREATE TABLE IT_MAN.Users
( emp_id number(10) NOT NULL,
  first_name varchar2(50) NOT NULL,
  last_name varchar2(50) NOT NULL,
  email varchar2(50) NOT NULL,
  creation_date date,
  CONSTRAINT Users_pk PRIMARY KEY (emp_id)
);*/


CREATE TABLE IT_MAN.Users
( emp_id INT NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  creation_date date,
  CONSTRAINT Users_pk PRIMARY KEY (emp_id)
);

/*CREATE TABLE IT_MAN.Authorised_Users
(
  email varchar2(50) NOT NULL,
  password varchar2(50) NOT NULL,
  emp_id number(10) NOT NULL,
  otp number(10),
  activated CHAR(2) default 'N',
   CONSTRAINT Authorised_Users_pk PRIMARY KEY (email),
    CONSTRAINT Authorised_Users_fk
    FOREIGN KEY (emp_id)
    REFERENCES IT_MAN.Users (emp_id)
);*/

CREATE TABLE IT_MAN.Authorised_Users
(
  email varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  emp_id INT NOT NULL,
  otp INT,
  activated CHAR(2) default 'N',
   CONSTRAINT Authorised_Users_pk PRIMARY KEY (email),
    CONSTRAINT Authorised_Users_fk
    FOREIGN KEY (emp_id)
    REFERENCES IT_MAN.Users (emp_id)
);


insert into users values(1,'Vivek','Kumar','a@a.com',systimestamp);
insert into AUTHORISED_USERS values('a@a.com','a',1);

mvn install:install-file -Dfile=G:\\ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar


insert into IT_MAN.USERS values(1286,'vivek','kumar','a@a.com','a',systimestamp);
commmit;


/*CREATE TABLE IT_MAN.ASSET_aLLOCATION
( ID number(10) NOT NULL,
ASSET_ID varchar2(50) NOT NULL,
  emp_id number(10) NOT NULL,
  ASSET_TYPE varchar2(50) NOT NULL,
  ALLOCATED_ON date,
    RETURNED_ON date,
  CONSTRAINT ASSET_aLLOCATION_PK PRIMARY KEY (ID)
);*/

CREATE TABLE IT_MAN.ASSET_aLLOCATION
( ID INT NOT NULL auto_increment,
ASSET_ID varchar(50) NOT NULL,
  emp_id INT NOT NULL,
  ASSET_TYPE varchar(50) NOT NULL,
  ALLOCATED_ON date,
    RETURNED_ON date,
  CONSTRAINT ASSET_aLLOCATION_PK PRIMARY KEY (ID)
);




CREATE SEQUENCE IT_MAN.seq_ASSET_aLLOCATION
  MINVALUE 1
  MAXVALUE 999999999999999999999999999
  START WITH 1
  INCREMENT BY 1
  CACHE 20;