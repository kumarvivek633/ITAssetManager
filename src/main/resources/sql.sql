--CREATE USER IT_MAN 
--    IDENTIFIED BY IT_MAN ;

CREATE SCHEMA `it_man` ;
CREATE USER 'IT_MAN' IDENTIFIED BY 'IT_MAN';




--Grant all PRIVILEGES   to IT_MAN;

GRANT ALL PRIVILEGES ON it_man.* TO 'IT_MAN';

/*CREATE TABLE IT_MAN.Users
( emp_id number(10) NOT NULL,
  first_name varchar2(50) NOT NULL,
  last_name varchar2(50) NOT NULL,
  email varchar2(50) NOT NULL,
  creation_date date,
  CONSTRAINT Users_pk PRIMARY KEY (emp_id)
);*/


CREATE TABLE it_man.USERS
( EMP_ID INT NOT NULL,
  FIRST_NAME VARCHAR(50) NOT NULL,
  LAST_NAME VARCHAR(50) NOT NULL,
  EMAIL VARCHAR(50) NOT NULL,
  CREATION_DATE date,
  CONSTRAINT USERS_PK PRIMARY KEY (EMP_ID)
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

CREATE TABLE it_man.AUTHORISED_USERS
(
  EMAIL varchar(50) NOT NULL,
  PASSWORD varchar(50) NOT NULL,
  EMP_ID INT NOT NULL,
  OTP INT,
  ACTIVATED CHAR(2) default 'N',
   CONSTRAINT AUTHORISED_USERS_PK PRIMARY KEY (EMAIL),
    CONSTRAINT AUTHORISED_USERS_FK
    FOREIGN KEY (EMP_ID)
    REFERENCES it_man.USERS (EMP_ID)
);


insert into USERS values(1,'Vivek','Kumar','a@a.com',systimestamp);
insert into AUTHORISED_USERS values('a@a.com','a',1);

mvn install:install-file -Dfile=G:\\ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar


insert into it_man.USERS values(1286,'vivek','kumar','kumarvivek633@gmail.com',Now());
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

CREATE TABLE it_man.ASSET_ALLOCATION
( ID INT NOT NULL AUTO_INCREMENT,
ASSET_ID varchar(50) NOT NULL,
  EMP_ID INT NOT NULL,
  ASSET_TYPE varchar(50) NOT NULL,
  ALLOCATED_ON date,
    RETURNED_ON date,
  CONSTRAINT ASSET_ALLOCATION_PK PRIMARY KEY (ID)
);




CREATE SEQUENCE it_man.seq_ASSET_aLLOCATION
  MINVALUE 1
  MAXVALUE 999999999999999999999999999
  START WITH 1
  INCREMENT BY 1
  CACHE 20;