DROP TABLE IF EXISTS category CASCADE;
CREATE TABLE category (
                      id INT AUTO_INCREMENT  PRIMARY KEY,
                      name VARCHAR(250) NOT NULL,
                      category_type VARCHAR(250) NOT NULL
);

INSERT INTO category(id,name,category_type) VALUES (1000,'cloth','EXPENSE');
insert into category(id,name,category_type) values (1001,'fuel','EXPENSE');
insert into category(id,name,category_type) values (1002,'shopping','EXPENSE');
insert into category(id,name,category_type) values (1003,'food','EXPENSE');
insert into category(id,name,category_type) values (1004,'entertainment','EXPENSE');
insert into category(id,name,category_type) values (1005,'salary','INCOME');
insert into category(id,name,category_type) values (1006,'loan','INCOME');


DROP TABLE IF EXISTS TRANSACTION;

CREATE TABLE TRANSACTION (
                          id INT AUTO_INCREMENT  PRIMARY KEY,
                          amount DOUBLE,
                          description VARCHAR(250) NOT NULL,
                          start_date DATE,
                          dtype VARCHAR(250) NOT NULL,
                          transaction_type VARCHAR(250),
                          category_id INT,
                          account_type_id INT NOT NULL
);

INSERT INTO TRANSACTION(id,amount,description,start_date,dtype,transaction_type,category_id,account_type_id)
VALUES (10000, 200.00,'eeee',TO_DATE('2022/01/21','yyyy/MM/dd'),'EXPENSE','EXPENSE',1000,10);

INSERT INTO TRANSACTION(id,amount,description,start_date,dtype,transaction_type,category_id,account_type_id)
VALUES (10001, 200.00,'eeee',TO_DATE('2022/01/21','yyyy/MM/dd'),'EXPENSE','EXPENSE',1001,10);

INSERT INTO TRANSACTION(id,amount,description,start_date,dtype,transaction_type,category_id,account_type_id)
VALUES (10002, 200.00,'eeee',TO_DATE('2022/01/21','yyyy/MM/dd'),'EXPENSE','EXPENSE',1002,11);
INSERT INTO TRANSACTION(id,amount,description,start_date,dtype,transaction_type,category_id,account_type_id)
VALUES (10003, 200.00,'eeee',TO_DATE('2022/01/21','yyyy/MM/dd'),'EXPENSE','EXPENSE',1003,11);
INSERT INTO TRANSACTION(id,amount,description,start_date,dtype,transaction_type,category_id,account_type_id)
VALUES (10004, 200.00,'eeee',TO_DATE('2022/01/21','yyyy/MM/dd'),'EXPENSE','EXPENSE',1004,12);

INSERT INTO TRANSACTION(id,amount,description,start_date,dtype,transaction_type,category_id,account_type_id)
VALUES (10005,2000.00,'eeee',TO_DATE('2022/01/21','yyyy/MM/dd'),'INCOME','INCOME',1005,12);

INSERT INTO TRANSACTION(id,amount,description,start_date,dtype,transaction_type,category_id,account_type_id)
VALUES (10006,2000.00,'eeee',TO_DATE('2022/02/21','yyyy/MM/dd'),'INCOME','INCOME',1006,10);


DROP TABLE IF EXISTS USER;
CREATE TABLE USER (
                          id INT AUTO_INCREMENT  PRIMARY KEY,
                          username VARCHAR(250) NOT NULL,
                          password VARCHAR(25) NOT NULL,
                          full_name VARCHAR(250)
);

insert into user(username,password,full_name) values ('sawsan','sawsan123','SS');
insert into user(username,password,full_name) values ('keshan','keshan123','KK');
insert into user(username,password,full_name) values ('rubisha','rubisha123','RR');

DROP TABLE IF EXISTS BUDGET;
CREATE TABLE BUDGET (
                             id INT AUTO_INCREMENT  PRIMARY KEY,
                             amount DOUBLE,
                             budget_type VARCHAR(250) NOT NULL,
                             start_date DATE,
                             category_id INT DEFAULT NULL
);

INSERT INTO BUDGET(id,amount,budget_type,start_date,category_id)
VALUES (100,2000.00,'ALL',TO_DATE('2022/01/21','yyyy/MM/dd'),null);

INSERT INTO BUDGET(id,amount,budget_type,start_date,category_id)
VALUES (101,2000.00,'CATEGORY',TO_DATE('2022/01/21','yyyy/MM/dd'),1000);

INSERT INTO BUDGET(id,amount,budget_type,start_date,category_id)
VALUES (102,200.00,'CATEGORY',TO_DATE('2022/01/21','yyyy/MM/dd'),1001);

INSERT INTO BUDGET(id,amount,budget_type,start_date,category_id)
VALUES (103,500.00,'CATEGORY',TO_DATE('2022/01/21','yyyy/MM/dd'),1002);

INSERT INTO BUDGET(id,amount,budget_type,start_date,category_id)
VALUES (104,1300.00,'CATEGORY',TO_DATE('2022/01/21','yyyy/MM/dd'),1003);

DROP TABLE IF EXISTS ACCOUNT_TYPE;
CREATE TABLE ACCOUNT_TYPE (
                      id INT AUTO_INCREMENT  PRIMARY KEY,
                      name VARCHAR(250) NOT NULL
);

insert into ACCOUNT_TYPE(id,name) values ('10','bank');
insert into ACCOUNT_TYPE(id,name) values ('11','cash');
insert into ACCOUNT_TYPE(id,name) values ('12','loan');