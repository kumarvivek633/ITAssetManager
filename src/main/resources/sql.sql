CREATE USER IT_MAN 
    IDENTIFIED BY IT_MAN ;

Grant all PRIVILEGES   to IT_MAN;


CREATE TABLE IT_MAN.Users
( emp_id number(10) NOT NULL,
  first_name varchar2(50) NOT NULL,
  last_name varchar2(50) NOT NULL,
  email varchar2(50) NOT NULL,
  creation_date date,
  CONSTRAINT Users_pk PRIMARY KEY (emp_id)
);

CREATE TABLE IT_MAN.Authorised_Users
(
  email varchar2(50) NOT NULL,
  password varchar2(50) NOT NULL,
  emp_id number(10) NOT NULL,
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


CREATE TABLE IT_MAN.ASSET_aLLOCATION
( ID number(10) NOT NULL,
ASSET_ID varchar2(50) NOT NULL,
  emp_id number(10) NOT NULL,
  ASSET_TYPE varchar2(50) NOT NULL,
  ALLOCATED_ON date,
    RETURNED_ON date,
  CONSTRAINT ASSET_aLLOCATION_PK PRIMARY KEY (ID)
);