
--------------------------------------------------
--   Create user
--------------------------------------------------

CREATE USER ADMIN

-- ***************  YOUR PASSWORD HERE ********************
IDENTIFIED BY Password

DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 1000M ON users;

GRANT connect to ADMIN;
GRANT resource to ADMIN;
GRANT create session TO ADMIN;
GRANT create table TO ADMIN;
GRANT create view TO ADMIN;

-- ***************  YOUR PASSWORD HERE ********************
conn ADMIN/Password



--------------------------------------------------
--   Drop tables if needed
--------------------------------------------------

drop table tf_interview;
drop table tf_interview_type;
drop table tf_placement;
drop table cotrainer_batch;
drop table tf_associate;
drop table tf_client;
drop table tf_end_client;
drop table tf_marketing_status;
drop table tf_batch;
drop table tf_curriculum;
drop table tf_batch_location;
drop table tf_trainer;
drop table tf_user;
drop table tf_role;

drop sequence interviewid_seq2; 
drop sequence userid_seq; 

--------------------------------------------------
--   Create sequences
--------------------------------------------------

create sequence interviewid_seq2
    start with     1
    increment by   50
    nocache;

create sequence userid_seq
    start with     1
    increment by   50
    nocache;

--------------------------------------------------
--   Create tables and constraints
--------------------------------------------------

create table tf_role (
    tf_role_id number primary key,
    tf_role_name varchar2(20)
);

create table tf_user (
    tf_user_id number primary key,
    tf_isapproved number,
    tf_hashpassword varchar2(200),
    tf_username varchar2(20) unique,
    tf_role_id number,

    constraint fk_tf_role_id
        foreign key (tf_role_id)
        references tf_role(tf_role_id)
);

create table tf_trainer (
    trainer_id number primary key,
    trainer_firstname varchar2(255),
    trainer_lastname varchar2(255),
    tf_user_id number,

    constraint fk_tf_user_id
        foreign key (tf_user_id)
        references tf_user(tf_user_id)
);

create table tf_batch_location (
    tf_batch_location_id number primary key,
    tf_batch_location_name varchar2(500)
);

create table tf_curriculum(
    tf_curriculum_id number primary key,
    tf_curriculum_name varchar2(30)    
);

create table tf_batch (
    tf_batch_id number primary key,
    tf_batch_name varchar2(50),
    tf_batch_start_date timestamp,
    tf_batch_end_date timestamp,
    tf_curriculum_id number,
    tf_batch_location_id number,
    primary_trainer number,

    constraint fk_tf_curriculum_id
        foreign key (tf_curriculum_id)
        references tf_curriculum(tf_curriculum_id),

    constraint fk_tf_batch_location_id
        foreign key (tf_batch_location_id)
        references tf_batch_location(tf_batch_location_id),

    constraint fk_primary_trainer
        foreign key (primary_trainer)
        references tf_trainer(trainer_id)
);

create table cotrainer_batch (
    trainer_id number,
    batch_id number,

    constraint pk_cotrainer_batch primary key (trainer_id, batch_id),

    constraint fk_trainer_id
        foreign key (trainer_id)
        references tf_trainer(trainer_id),

    constraint fk_batch_id
        foreign key (batch_id)
        references tf_batch(tf_batch_id)
);

create table tf_client(
    tf_client_id number primary key,
    tf_client_name varchar2(100)    
);

create table tf_interview_type(
    tf_interview_type_id number primary key,
    tf_interview_type_name varchar2(30)    
);

create table tf_end_client(
    tf_end_client_id number primary key,
    tf_end_client_name varchar2(100)    
);

create table tf_marketing_status(
    tf_marketing_status_id number primary key,
    tf_marketing_status_name varchar2(30)    
);


create table tf_associate(
    tf_associate_id number primary key,
    tf_client_start_date timestamp,
    tf_associate_first_name varchar2(30),
    tf_associate_last_name varchar2(30),
    tf_staging_feedback varchar2(255),
    tf_batch_id number,
    tf_client_id number,
    tf_end_client_id number,
    tf_marketing_status_id number,
    tf_user_id number,

    constraint fk_tf_asso_batch_id
        foreign key (tf_batch_id)
        references tf_batch(tf_batch_id),

    constraint fk_tf_asso_client_id
        foreign key (tf_client_id)
        references tf_client(tf_client_id),

    constraint fk_tf_asso_end_client_id
        foreign key (tf_end_client_id)
        references tf_end_client(tf_end_client_id),

    constraint fk_tf_asso_marketing_status_id
        foreign key (tf_marketing_status_id)
        references tf_marketing_status(tf_marketing_status_id),

    constraint fk_tf_asso_user_id
        foreign key (tf_user_id)
        references tf_user(tf_user_id)
);


create table tf_placement(
    tf_placement_id number primary key,
    tf_placement_start_date timestamp,
    tf_placement_end_date timestamp,
    tf_associate_id number,    
    tf_client_id number,
    tf_end_client_id number,


    constraint fk_tf_associate_id
        foreign key (tf_associate_id)
        references tf_associate(tf_associate_id),

    constraint fk_tf_client_id
        foreign key (tf_client_id)
        references tf_client(tf_client_id),

    constraint fk_tf_end_client_id
        foreign key (tf_end_client_id)
        references tf_end_client(tf_end_client_id)
);

create table tf_interview(
    tf_interview_id number primary key,
    tf_associate_feedback varchar2(2000),
    tf_client_feedback varchar2(2500),
    tf_date_associate_issued timestamp,
    tf_date_sales_issued timestamp,
    tf_flag_reason varchar2(300),
    tf_interview_date timestamp,
    tf_is_client_feedback_visiable number,
    tf_is_interview_flagged number,
    tf_job_description varchar2(2000),
    tf_question_given varchar2(3500),
    tf_was_24hr_notice number,
    tf_associate_id number,    
    tf_client_id number,
    tf_end_client_id number,
    tf_interview_type_id number,
    
    constraint fk_tf_interview_associate_id
        foreign key (tf_associate_id)
        references tf_associate(tf_associate_id),

    constraint fk_tf_interview_client_id
        foreign key (tf_client_id)
        references tf_client(tf_client_id),

    constraint fk_tf_interview_end_client_id
        foreign key (tf_end_client_id)
        references tf_end_client(tf_end_client_id),

    constraint fk_tf_interview_type_id
        foreign key (tf_interview_type_id)
        references tf_interview_type(tf_interview_type_id)
);



--------------------------------------------------
--  insert roles
--------------------------------------------------

insert into admin.tf_role values(1, 'Admin');
insert into admin.tf_role values(2, 'Trainer');
insert into admin.tf_role values(3, 'Sales');
insert into admin.tf_role values(4, 'Staging');
insert into admin.tf_role values(5, 'Associate');



--------------------------------------------------
--  insert users
--------------------------------------------------

insert into admin.tf_user values(1, 1, 'sha1:64000:18:zBfcx3rxxYev6SuYjw/EoTzwwhDW0+5I:TE/5QDShUo2DpVtwM1wfpnmD', 'TestAdmin', 1);
insert into admin.tf_user values(2, 1, 'sha1:64000:18:L/M8XUhtmOcIJbjYLhuq+4KLQqem//Sl:Edj7gu9z9U7+y9iDd072qbpP', 'Trainer', 2);
insert into admin.tf_user values(3, 1, 'sha1:64000:18:YqUfPnMwHCQa52e+bNV2SKUprjlpwCOx:hkdeqfHll17n9UfECH7nXvjS', 'salestest', 3);
insert into admin.tf_user values(4, 1, 'sha1:64000:18:KCXMleEg1/Ry47fUSPXuFwdWfc+9fped:DmAyTCW2D4ZLv/3zoDFAprQr', 'bobstage', 4);
insert into admin.tf_user values(5, 1, 'sha1:64000:18:S6jPrDVGGV4nDx6fiA4Mwvo1uWk9Ft9s:S8BGeiX7wuw1TkkEFmegwuPh', 'cyril', 5);


-- All of these users have the password 'Trainer'
insert into admin.tf_user values(6, 1, 'sha1:64000:18:L/M8XUhtmOcIJbjYLhuq+4KLQqem//Sl:Edj7gu9z9U7+y9iDd072qbpP', 'Trainer0', 2);
insert into admin.tf_user values(7, 1, 'sha1:64000:18:L/M8XUhtmOcIJbjYLhuq+4KLQqem//Sl:Edj7gu9z9U7+y9iDd072qbpP', 'Trainer1', 2);
insert into admin.tf_user values(8, 1, 'sha1:64000:18:L/M8XUhtmOcIJbjYLhuq+4KLQqem//Sl:Edj7gu9z9U7+y9iDd072qbpP', 'Trainer2', 2);
insert into admin.tf_user values(9, 1, 'sha1:64000:18:L/M8XUhtmOcIJbjYLhuq+4KLQqem//Sl:Edj7gu9z9U7+y9iDd072qbpP', 'Trainer3', 2);
insert into admin.tf_user values(10, 1, 'sha1:64000:18:L/M8XUhtmOcIJbjYLhuq+4KLQqem//Sl:Edj7gu9z9U7+y9iDd072qbpP', 'Trainer4', 2);


--trainers
insert into admin.tf_trainer values (0, 'Trainer0', 'Trainer0', 6);
insert into admin.tf_trainer values (1, 'Trainer1', 'Trainer1', 7);
insert into admin.tf_trainer values (2, 'Trainer2', 'Trainer2', 8);
insert into admin.tf_trainer values (3, 'Trainer3', 'Trainer3', 9);
insert into admin.tf_trainer values (4, 'Trainer4', 'Trainer4', 10);



-----------------------------------------------------
----- This section is for adding Dummy Values--------
-----------------------------------------------------


--INSERT DUMMY VALUES INTO TF_END_CLIENT TABLE
insert into tf_end_client values (1, 'Accenture');
insert into tf_end_client values (2, 'Infosys');
insert into tf_end_client values (3, 'Federal Reserve');
insert into tf_end_client values (4, 'Fannie Mae');
insert into tf_end_client values (5, 'Revature');
insert into tf_end_client values (6, 'Sallie Mae');

--INSERT DUMMY VALUES INTO TF_CLIENT TABLE
insert into tf_client values (1, 'Accenture');
insert into tf_client values (2, 'Infosys');
insert into tf_client values (3, 'AFS');
insert into tf_client values (4, 'Hexaware');
insert into tf_client values (5, 'Revature');

--INSERT DUMMY VALUES INTO TF_INTERVIEW_TYPE TABLE
insert into tf_interview_type values (1, 'Phone');
insert into tf_interview_type values (2, 'Online');
insert into tf_interview_type values (3, 'On-Site');
insert into tf_interview_type values (4, 'Skype');
--INSERT DUMMY VALUES INTO TF_CURRICULUM TABLE
insert into tf_curriculum values (1, 'JTA');
insert into tf_curriculum values (2, 'Java');
insert into tf_curriculum values (3, '.Net');
insert into tf_curriculum values (4, 'PEGA');
insert into tf_curriculum values (5, 'DynamicCRM');
insert into tf_curriculum values (6, 'Salesforce');
insert into tf_curriculum values (7, 'Microservices');

--INSERT DUMMY VALUES INTO TF_MARKETING_STATUS
INSERT INTO tf_marketing_status VALUES(1, 'MAPPED: TRAINING');
INSERT INTO tf_marketing_status VALUES(2, 'MAPPED: RESERVED');
INSERT INTO tf_marketing_status VALUES(3, 'MAPPED: SELECTED');
INSERT INTO tf_marketing_status VALUES(4, 'MAPPED: CONFIRMED');
INSERT INTO tf_marketing_status VALUES(5, 'MAPPED: DEPLOYED');

INSERT INTO tf_marketing_status VALUES(6, 'UNMAPPED: TRAINING');
INSERT INTO tf_marketing_status VALUES(7, 'UNMAPPED: OPEN');
INSERT INTO tf_marketing_status VALUES(8, 'UNMAPPED: SELECTED');
INSERT INTO tf_marketing_status VALUES(9, 'UNMAPPED: CONFIRMED');
INSERT INTO tf_marketing_status VALUES(10, 'UNMAPPED: DEPLOYED');
INSERT INTO tf_marketing_status VALUES(11, 'DIRECTLY PLACED');
INSERT INTO tf_marketing_status VALUES(12, 'TERMINATED');


--INSERT DUMMY VALUES INTO TF_BATCH_LOCATION
insert into tf_batch_location values(1, 'Revature LLC, 11730 Plaza America Drive, 2nd Floor | Reston, VA 20190');
insert into tf_batch_location values(2, 'UMUC');
insert into tf_batch_location values(3, 'USF');
insert into tf_batch_location values(4, 'SkySong Innovation Center, 1475 N. Scottsdale Road, Scottsdale, AZ 85257');
insert into tf_batch_location values(5, 'Tech Incubator at Queens College, 65-30 Kissena Blvd, CEP Hall 2, Queens, NY 11367');
insert into tf_batch_location values(6, 'CUNY-SPS 119 West 31st Street, New York, NY 10001');

--INSERT DUMMY VALUES INTO BATCH
insert into tf_batch values (0, '1712 Dec04 AP-USF', TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-02-16', 'YYYY-MM-DD'),2,3,0);
insert into tf_batch values (1, '1710 Oct09 PEGA', TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-12-15', 'YYYY-MM-DD'),4,1,0);
insert into tf_batch values (2, '1709 Sept11 JTA', TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-11-17', 'YYYY-MM-DD'),1,1,0);
insert into tf_batch values (3, '1707 Jul24 Java', TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-09-29', 'YYYY-MM-DD'),2,1,0);
insert into tf_batch values (4, '1707 Jul10 PEGA', TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-09-15', 'YYYY-MM-DD'),4,1,0);
insert into tf_batch values(5, '1701 Jan09 Java', TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), TO_TIMESTAMP('3/17/2017', 'MM/DD/YYYY'),2,1,0);
insert into tf_batch values(6, '1701 Jan30 NET', TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), TO_TIMESTAMP('4/17/2017', 'MM/DD/YYYY'),3,1,0);
insert into tf_batch values(7, '1709 Sep18 Salesforce', TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), TO_TIMESTAMP('12/8/2017', 'MM/DD/YYYY'),6,1,0);
insert into tf_batch values(8, '1709 Sep25 Java AP-CUNY', TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), TO_TIMESTAMP('12/1/2017', 'MM/DD/YYYY'),2,6,0);
insert into tf_batch values(9, '1712 Dec04-2', TO_TIMESTAMP('12/4/2017', 'MM/DD/YYYY'), TO_TIMESTAMP('2/9/2018', 'MM/DD/YYYY'),2,1,0);




--1712 Dec04 AP-USF
insert into tf_associate values (1,TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), 'Frank', 'Hind', 'No Feedback', 0, 2, 1, 1, 5);
insert into tf_associate values (2,TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), 'Thomas', 'Page', 'No Feedback', 0, 2, 1, 1, null);
insert into tf_associate values (3,TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), 'Lucas', 'Normand', 'No Feedback', 0, 2, 1, 1, null);
insert into tf_associate values (4,TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), 'Jhonnie', 'Cole', 'No Feedback', 0, 2, 1, 1, null);
insert into tf_associate values (5,TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), 'Ramona', 'Reyes', 'No Feedback', 0, 2, 1, 1, null);
insert into tf_associate values (6,TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), 'Grace', 'Noland', 'No Feedback', 0, 2, 1, 1, null);
insert into tf_associate values (7,TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), 'Casey', 'Morton', 'No Feedback', 0, 2, 1, 1, null);
insert into tf_associate values (8,TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), 'Gustavo', 'Brady', 'No Feedback', 0, 2, 1, 1, null);
insert into tf_associate values (9,TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), 'Glen', 'Holloway', 'No Feedback', 0, 2, 1, 1, null);
insert into tf_associate values (10,TO_TIMESTAMP('12/4/2017', 'MM/DD/YYYY'), 'Leeroy', 'Jenkins', 'No Feedback', 0, 2, 1, 1, null);
insert into tf_associate values (11,TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), 'Jeanne', 'Watts', 'No Feedback', 0, 2, 1, 1, null);
insert into tf_associate values (12,TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), 'Carol', 'Ruiz', 'No Feedback', 0, 2, 1, 1, null);

--1710 Oct09 PEGA
insert into tf_associate values (13,TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), 'Trevor', 'Hampton', 'No Feedback', 1, 2, 4, 1, null);
insert into tf_associate values (14,TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), 'Jennie', 'Hudson', 'No Feedback', 1, 2, 4, 1, null);
insert into tf_associate values (15,TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), 'David', 'Haynes', 'No Feedback', 1, 2, 4, 1, null);
insert into tf_associate values (16,TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), 'Ira', 'Mullins', 'No Feedback', 1, 2, 4, 1, null);
insert into tf_associate values (17,TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), 'Alexandra', 'Mitchell', 'No Feedback', 1, 2, 4, 1, null);
insert into tf_associate values (18,TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), 'Bradley', 'Harris', 'No Feedback', 1, 2, 4, 1, null);
insert into tf_associate values (19,TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), 'Gerardo', 'Roy', 'No Feedback', 1, 2, 4, 1, null);
insert into tf_associate values (20,TO_TIMESTAMP('12/4/2017', 'MM/DD/YYYY'), 'Jacob', 'Cortez', 'No Feedback', 1, 2, 4, 1, null);
insert into tf_associate values (21,TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), 'Kathryn', 'Young', 'No Feedback', 1, 2, 4, 1, null);
insert into tf_associate values (22,TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), 'Allen', 'Walker', 'No Feedback', 1, 2, 4, 1, null);
insert into tf_associate values (23,TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), 'Gustavo', 'Reed', 'No Feedback', 1, 2, 4, 1, null);
insert into tf_associate values (24,TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), 'Robin', 'Norton', 'No Feedback', 1, 2, 4, 1, null);
insert into tf_associate values (25,TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), 'Julia', 'Drake', 'No Feedback', 1, 2, 4, 1, null);
insert into tf_associate values (26,TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), 'Joan', 'Evans', 'No Feedback', 1, 2, 4, 1, null);
insert into tf_associate values (27,TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), 'Larry', 'Holl', 'No Feedback', 1, 2, 4, 1, null);

--1709 Sept11 JTA
insert into tf_associate values (28,TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), 'Vito', 'Plante', 'No Feedback', 2, 1, 1, 1, null);
insert into tf_associate values (29,TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), 'Crystal', 'Couch', 'No Feedback', 2, 1, 1, 1, null);
insert into tf_associate values (30,TO_TIMESTAMP('12/4/2017', 'MM/DD/YYYY'), 'Adam', 'Collins', 'No Feedback', 2, 1, 1, 1, null);
insert into tf_associate values (31,TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), 'Bert', 'Bryant', 'No Feedback', 2, 1, 1, 1, null);
insert into tf_associate values (32,TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), 'Nicholas', 'Griffin', 'No Feedback', 2, 1, 1, 1, null);
insert into tf_associate values (33,TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), 'Joe', 'Cook', 'No Feedback', 2, 1, 1, 1, null);
insert into tf_associate values (34,TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), 'Andrew', 'Bennet', 'No Feedback', 2, 1, 1, 1, null);
insert into tf_associate values (35,TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), 'Phillip', 'Henderson', 'No Feedback', 2, 1, 1, 1, null);
insert into tf_associate values (36,TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), 'Gary', 'Ward', 'No Feedback', 2, 1, 1, 1, null);
insert into tf_associate values (37,TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), 'Bruce', 'Long', 'No Feedback', 2, 1, 1, 1, null);
insert into tf_associate values (38,TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), 'Russel', 'Peters', 'No Feedback', 2, 1, 1, 1, null);
insert into tf_associate values (39,TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), 'Emily', 'Baker', 'No Feedback', 2, 1, 1, 1, null);
insert into tf_associate values (40,TO_TIMESTAMP('12/4/2017', 'MM/DD/YYYY'), 'Jake', 'King', 'No Feedback', 2, 1, 1, 1, null);
insert into tf_associate values (41,TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), 'Jamie', 'Campbell', 'No Feedback', 2, 1, 1, 1, null);
insert into tf_associate values (42,TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), 'Larry', 'Hughes', 'No Feedback', 2, 1, 1, 1, null);

--1707 Jul24 Java
insert into tf_associate values (43,TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), 'Carlos', 'Adams', 'No Feedback', 3, 2, 2, 5, null);
insert into tf_associate values (44,TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), 'Victor', 'Bailey', 'No Feedback', 3, 2, 2, 5, null);
insert into tf_associate values (45,TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), 'Harold', 'Cartor', 'No Feedback', 3, 2, 2, 5, null);
insert into tf_associate values (46,TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), 'Judith', 'Rivera', 'No Feedback', 3, 2, 2, 5, null);
insert into tf_associate values (47,TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), 'Maria', 'Smith', 'No Feedback', 3, 2, 2, 5, null);
insert into tf_associate values (48,TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), 'Steven', 'Simmons', 'No Feedback', 3, 2, 2, 5, null);
insert into tf_associate values (49,TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), 'Donna', 'Hall', 'No Feedback', 3, 2, 2, 5, null);
insert into tf_associate values (50,TO_TIMESTAMP('12/4/2017', 'MM/DD/YYYY'), 'Samuel', 'Price', 'No Feedback', 3, 2, 2, 7, null);
insert into tf_associate values (51,TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), 'Jean', 'Jackson', 'No Feedback', 3, 2, 2, 7, null);
insert into tf_associate values (52,TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), 'Adam', 'Stewart', 'No Feedback', 3, 2, 2, 4, null);
insert into tf_associate values (53,TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), 'Gary', 'Nelson', 'No Feedback', 3, 2, 2, 4, null);
insert into tf_associate values (54,TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), 'Peter', 'Morgan', 'No Feedback', 3, 2, 2, 4, null);

--1707 Jul10 Pega
insert into tf_associate values (55,TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), 'Jack', 'Morris', 'No Feedback', 4, 3, 4, 5, null);
insert into tf_associate values (56,TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), 'Randy', 'Parker', 'No Feedback', 4, 3, 4, 5, null);
insert into tf_associate values (57,TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), 'Justin', 'Flores', 'No Feedback', 4, 3, 4, 5, null);
insert into tf_associate values (58,TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), 'Richard', 'Gray', 'No Feedback', 4, 3, 4, 5, null);
insert into tf_associate values (59,TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), 'Jesse', 'Turner', 'No Feedback', 4, 3, 4, 4, null);
insert into tf_associate values (60,TO_TIMESTAMP('12/4/2017', 'MM/DD/YYYY'), 'John', 'Baker', 'No Feedback', 4, 3, 4, 5, null);
insert into tf_associate values (61,TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), 'Benjamin', 'Jones', 'No Feedback', 4, 3, 4, 5, null);
insert into tf_associate values (62,TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), 'Todd', 'Torres', 'No Feedback', 4, 3, 4, 7, null);
insert into tf_associate values (63,TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), 'Kathleen', 'Kelly', 'No Feedback', 4, 3, 4, 7, null);
insert into tf_associate values (64,TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), 'Sara', 'Long', 'No Feedback', 4, 3, 4, 7, null);
insert into tf_associate values (65,TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), 'Linda', 'Russell', 'No Feedback', 4, 3, 4, 7, null);
insert into tf_associate values (66,TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), 'Brenda', 'Wilson', 'No Feedback', 4, 3, 4,10, null);
insert into tf_associate values (67,TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), 'Betty', 'Green', 'No Feedback', 4, 3, 4,10, null);
insert into tf_associate values (68,TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), 'Bobby', 'Edwards', 'No Feedback', 4, 3, 4,10, null);
insert into tf_associate values (69,TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), 'Marilyn', 'Allens', 'No Feedback', 4, 3, 4,10, null);

--batch 7
insert into tf_associate values (70,TO_TIMESTAMP('12/4/2017', 'MM/DD/YYYY'), 'Willoughby', 'Sherwood', 'No Feedback', 7, 3, 6, 1, null);
insert into tf_associate values (71,TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), 'Tomi', 'Nikkole', 'No Feedback', 7, 3, 6, 1, null);
insert into tf_associate values (72,TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), 'Newt', 'Jaki', 'No Feedback', 7, 3, 6, 1, null);
insert into tf_associate values (73,TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), 'Darnell', 'Mervyn', 'No Feedback', 7, 3, 6, 1, null);
insert into tf_associate values (74,TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), 'Claire', 'Connor', 'No Feedback', 7, 3, 6, 1, null);
insert into tf_associate values (75,TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), 'Edmonde', 'Sora', 'No Feedback', 7, 3, 6, 1, null);
insert into tf_associate values (76,TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), 'Kaitlyn', 'Abbie', 'No Feedback', 7, 3, 6, 1, null);
insert into tf_associate values (77,TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), 'Natsuko', 'Lily', 'No Feedback', 7, 3, 6, 1, null);
insert into tf_associate values (78,TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), 'Ben', 'Gabrielle', 'No Feedback', 7, 3, 6, 1, null);
insert into tf_associate values (79,TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), 'Alberta', 'Arienne', 'No Feedback', 7, 3, 6, 1, null);
insert into tf_associate values (80,TO_TIMESTAMP('12/4/2017', 'MM/DD/YYYY'), 'Merline', 'Thom', 'No Feedback', 7, 3, 6, 1, null);
insert into tf_associate values (81,TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), 'Hachirou', 'Kasumi', 'No Feedback', 7, 3, 6, 1, null);

--batch 6
insert into tf_associate values (82,TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), 'Leigh', 'Jordon', 'No Feedback', 6, 1, 3,10, null);
insert into tf_associate values (83,TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), 'Amity', 'Brandi', 'No Feedback', 6, 1, 3,10, null);
insert into tf_associate values (84,TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), 'Merlyn', 'Ros', 'No Feedback', 6, 1, 3,10, null);
insert into tf_associate values (85,TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), 'Primula', 'Gyles', 'No Feedback', 6, 1, 3,10, null);
insert into tf_associate values (86,TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), 'Ethel', 'Jemima', 'No Feedback', 6, 1, 3,10, null);
insert into tf_associate values (87,TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), 'Jonelle', 'Eugenie', 'No Feedback', 6, 1, 3,10, null);
insert into tf_associate values (88,TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), 'Evangelina', 'Harlan', 'No Feedback', 6, 1, 3,10, null);
insert into tf_associate values (89,TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), 'Anjelica', 'Babs', 'No Feedback', 6, 1, 3,10, null);
insert into tf_associate values (90,TO_TIMESTAMP('12/4/2017', 'MM/DD/YYYY'), 'Jerred', 'Yuko', 'No Feedback', 6, 1, 3,10, null);
insert into tf_associate values (91,TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), 'Cecile', 'Colton', 'No Feedback', 6, 1, 3,10, null);
insert into tf_associate values (92,TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), 'Ulla', 'Gilbert', 'No Feedback', 6, 1, 3,10, null);
insert into tf_associate values (93,TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), 'Teija', 'Mariko', 'No Feedback', 6, 1, 3,10, null);

--batch 5
insert into tf_associate values (94,TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), 'Maryann', 'Zechariah', 'No Feedback', 5, 2, 2,10, null);
insert into tf_associate values (95,TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), 'Nichola', 'Dennis', 'No Feedback', 5, 2, 2,10, null);
insert into tf_associate values (96,TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), 'Githa', 'Nyree', 'No Feedback', 5, 2, 2,10, null);
insert into tf_associate values (97,TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), 'Chelsey', 'Gwyneth', 'No Feedback', 5, 2, 2,10, null);
insert into tf_associate values (98,TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), 'Jepson', 'Orson', 'No Feedback', 5, 2, 2,10, null);
insert into tf_associate values (99,TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), 'Careen', 'Jeffery', 'No Feedback', 5, 2, 2,10, null);
insert into tf_associate values (100,TO_TIMESTAMP('12/4/2017', 'MM/DD/YYYY'), 'Malachi', 'Nic', 'No Feedback', 5, 2, 2,10, null);
insert into tf_associate values (101,TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), 'Farran', 'Sawyer', 'No Feedback', 5, 2, 2,10, null);
insert into tf_associate values (102,TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), 'Desiree', 'Gayelord', 'No Feedback', 5,  2, 2,10, null);
insert into tf_associate values (103,TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), 'Mae', 'Lorrie', 'No Feedback', 5,  2, 2,10, null);
insert into tf_associate values (104,TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), 'Jon', 'Hamilton', 'No Feedback', 5, 2, 2,10, null);
insert into tf_associate values (105,TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), 'Marshal', 'Parnel', 'No Feedback', 5, 2, 2,10, null);

--batch 8
insert into tf_associate values (106,TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), 'Ayame', 'Shun', 'No Feedback', 8, 4, 2, 1, null);
insert into tf_associate values (107,TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), 'Katashi', 'He', 'No Feedback', 8, 4, 2, 1, null);
insert into tf_associate values (108,TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), 'Jiahao', 'Shiro', 'No Feedback', 8, 4, 2, 1, null);
insert into tf_associate values (109,TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), 'Naoko', 'Hikaru', 'No Feedback', 8, 4, 2, 1, null);
insert into tf_associate values (110,TO_TIMESTAMP('12/4/2017', 'MM/DD/YYYY'), 'Chihiro', 'Moriko', 'No Feedback', 8, 4, 2, 1, null);
insert into tf_associate values (111,TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), 'Bai', 'Kazuo', 'No Feedback', 8, 4, 2, 1, null);
insert into tf_associate values (112,TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), 'Etsuko', 'Fang', 'No Feedback', 8, 4, 2, 1, null);
insert into tf_associate values (113,TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), 'Hideki', 'Qing', 'No Feedback', 8, 4, 2, 1, null);
insert into tf_associate values (114,TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), 'Masaru', 'Ayako', 'No Feedback', 8, 4, 2, 1, null);
insert into tf_associate values (115,TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), 'Megumi', 'Mari', 'No Feedback', 8, 4, 2, 1, null);
insert into tf_associate values (116,TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), 'Hiroko', 'Hiroshi', 'No Feedback', 8, 4, 2, 1, null);
insert into tf_associate values (117,TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), 'Sumiko', 'Mai', 'No Feedback', 8, 4, 2, 1, null);

--batch9
insert into tf_associate values (118,TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), 'Kanon', 'Bai', 'No Feedback', 9, 5, null, 1, null);
insert into tf_associate values (119,TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), 'Hikaru', 'Yuu', 'No Feedback', 9, 5, null, 1, null);
insert into tf_associate values (120,TO_TIMESTAMP('12/4/2017', 'MM/DD/YYYY'), 'Shiori', 'Takeshi', 'No Feedback', 9, 5, null, 1, null);
insert into tf_associate values (121,TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), 'Jianhong', 'Youta', 'No Feedback', 9, 5, null, 1, null);
insert into tf_associate values (122,TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), 'Goro', 'Bai', 'No Feedback', 9, 5, null, 1, null);
insert into tf_associate values (123,TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), 'Shufen', 'Miyu', 'No Feedback', 9, 5, null, 1, null);
insert into tf_associate values (124,TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), 'Kenji', 'He', 'No Feedback', 9, 5, null, 1, null);
insert into tf_associate values (125,TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), 'Ren', 'Hayate', 'No Feedback', 9, 5, null, 1, null);
insert into tf_associate values (126,TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), 'Momoko', 'Miki', 'No Feedback', 9, 5, null, 1, null);
insert into tf_associate values (127,TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), 'Takashi', 'Ling', 'No Feedback', 9, 5, null, 1, null);
insert into tf_associate values (128,TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), 'Setsuko', 'Yuuki', 'No Feedback', 9, 5, null, 1, null);
insert into tf_associate values (129,TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), 'Megumi', 'Kato', 'No Feedback', 9, 5, null, 1, null);



--INSERT DUMMY VALUES INTO INTERVIEW
--insert into tf_interview(tf_interview_id, tf_interview_date, tf_interview_feedback, tf_client_id, tf_end_client_id, tf_interview_type_id, tf_associate_id) values(tf_interview_seq.nextval, date, feedback, client, endclient, inttype, associd);

--batch 6
insert into tf_interview values(0, 'sinister', 'cold', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), 0, 1, 'Programmer', 'Question List...', 0, 82, 2, 3, 3);
insert into tf_interview values(1, 'energized', 'positive', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), 0, 1, 'Developer', 'Question List...', 0, 83, 2, 3, 3);
insert into tf_interview values(2, 'hard-hearted', 'enchanting',TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), 0, 0, 'Tester', 'Question List...', 0, 84, 2, 3, 3);
insert into tf_interview values(3, 'superb', 'callous', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-4-24', 'YYYY-MM-DD'), 0, 1, 'Advisor', 'Question List...', 1, 85, 2, 3, 3);
insert into tf_interview values(4, 'sparkling', 'effective', TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-4-24', 'YYYY-MM-DD'), 0, 0, 'Programmer', 'Question List...', 1, 86, 2, 3, 3);
insert into tf_interview values(5, 'paradise', 'adventure', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), 0, 0, 'Developer', 'Question List...', 1, 87, 2, 3, 1);
insert into tf_interview values(6, 'horrible', 'ecstatic', TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), 0, 0, 'Tester', 'Question List...', 1, 88, 2, 3, 1);
insert into tf_interview values(7, 'hostile', 'popular', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), 0, 1, 'Advisor', 'Question List...', 0, 89, 2, 3, 4);
insert into tf_interview values(8, 'efficient', 'essential', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-4-24', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), 0, 1, 'Programmer', 'Question List...', 1, 90, 2, 3, 4);
insert into tf_interview values(9, 'progress', 'efficient', TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), 0, 0, 'Developer', 'Question List...', 0, 91, 2, 3, 1);
insert into tf_interview values(10, 'collapse', 'honest', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), 0, 1, 'Tester', 'Question List...', 1, 92, 2, 3, 1);
insert into tf_interview values(11, 'agree', 'accomplishment', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), 0, 1, 'Advisor', 'Question List...', 1, 93, 2, 3, 1);

--batch5
insert into tf_interview values(12, 'shocking', 'savage', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), 0, 1, 'Programmer', 'Question List...', 1, 94, 1, 6, 1);
insert into tf_interview values(13, 'heavenly', 'electrifying', TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), 0, 1, 'Developer', 'Question List...', 1, 95, 1, 6, 4);
insert into tf_interview values(14, 'harmful', 'sickening', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), 0, 0, 'Tester', 'Question List...', 1, 96, 1, 6, 4);
insert into tf_interview values(15, 'scare', 'effervescent', TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), 0, 1, 'Advisor', 'Question List...', 1, 97, 1, 6, 1);
insert into tf_interview values(16, 'accomplish', 'skillful', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 0, 1, 'Programmer', 'Question List...', 1, 98, 1, 6, 4);
insert into tf_interview values(17, 'angelic', 'greed', TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 0, 0, 'Developer', 'Question List...', 1, 99, 1, 6, 1);
insert into tf_interview values(18, 'slimy', 'hurt', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 0, 1, 'Tester', 'Question List...', 1, 100, 1, 6, 1);
insert into tf_interview values(19, 'essential', 'accepted', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 0, 1, 'Advisor', 'Question List...', 1, 101, 1, 6, 4);
insert into tf_interview values(20, 'spirited', 'honorable', TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 0, 0, 'Programmer', 'Question List...', 0, 102, 2, 3, 1);
insert into tf_interview values(21, 'prominent', 'action', TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 0, 0, 'Developer', 'Question List...', 1, 103, 2, 3, 1);
insert into tf_interview values(22, 'effortless', 'stupendous', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 0, 0, 'Tester', 'Question List...', 1, 104, 2, 3, 1);
insert into tf_interview values(23, 'accomplishment', 'healthy', TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 0, 0, 'Advisor', 'Question List...', 1, 105, 2, 3, 1);

--batch-4
insert into tf_interview values(24, 'effective', 'scary', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-4-24', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 0, 'Programmer', 'Question List...', 0, 55, 3, 4, 3);
insert into tf_interview values(25, 'skilled', 'honored', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 1, 'Developer', 'Question List...', 0, 56, 3, 4, 3);
insert into tf_interview values(26, 'grave', 'active', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 0, 'Tester', 'Question List...', 0, 57, 3, 4, 3);
insert into tf_interview values(27, 'surprising', 'harmonious', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 0, 'Advisor', 'Question List...', 0, 58, 3, 4, 3);
insert into tf_interview values(28, 'sorry', 'surprising', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 0, 'Programmer', 'Question List...', 1, 59, 3, 4, 3);
insert into tf_interview values(29, 'principled', 'clumsy', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 1, 'Developer', 'Question List...', 1, 60, 3, 4, 3);
insert into tf_interview values(30, 'spiteful', 'smelly', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 0, 'Tester', 'Question List...', 0, 61, 3, 4, 3);

insert into tf_interview values(31, 'hate', 'achievement', TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 1, 'Advisor', 'Question List...', 0, 62, 3, 4, 3);
insert into tf_interview values(32, 'grotesque', 'angelic', TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 1, 'Programmer', 'Question List...', 1, 63, 3, 4, 3);
insert into tf_interview values(33, 'secure', 'affluent', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 1, 'Developer', 'Question List...', 1, 64, 3, 4, 3);
insert into tf_interview values(34, 'haggard', 'energetic', TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 1, 'Tester', 'Question List...', 0, 65, 3, 4, 3);

insert into tf_interview values(35, 'ethical', 'coarse', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 0, 'Advisor', 'Question List...', 0, 66, 1, 6, 3);
insert into tf_interview values(36, 'appealing', 'grotesque', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 1, 'Programmer', 'Question List...', 0, 67, 1, 6, 3);
insert into tf_interview values(37, 'hard', 'sobbing', TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 1, 'Developer', 'Question List...', 1, 68, 1, 6, 3);
insert into tf_interview values(38, 'agreeable', 'hate', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 0, 1, 'Tester', 'Question List...', 1, 69, 1, 6, 3);

--batch3
insert into tf_interview values(39, 'smile', 'affirmative', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 0, 0, 'Advisor', 'Question List...', 1, 43, 2, 2, 3);
insert into tf_interview values(40, 'enthusiastic', 'enthusiastic', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 0, 1, 'Programmer', 'Question List...', 0, 44, 2, 2, 3);
insert into tf_interview values(41, 'satisfactory', 'sparkling', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 0, 1, 'Developer', 'Question List...', 1, 45, 2, 2, 3);
insert into tf_interview values(42, 'honorable', 'super', TO_TIMESTAMP('2017-4-24', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 0, 1, 'Tester', 'Question List...', 1, 46, 2, 2, 3);
insert into tf_interview values(43, 'cold', 'appealing', TO_TIMESTAMP('2017-4-24', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 0, 1, 'Advisor', 'Question List...', 1, 47, 2, 2, 3);
insert into tf_interview values(44, 'perfect', 'stirring', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 0, 0, 'Programmer', 'Question List...', 1, 48, 2, 2, 3);
insert into tf_interview values(45, 'sick', 'hideous', TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 0, 0, 'Developer', 'Question List...', 1, 49, 2, 2, 3);
insert into tf_interview values(46, 'horrendous', 'agree', TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 0, 0, 'Tester', 'Question List...', 1, 50, 2, 2, 3);
insert into tf_interview values(47, 'amazing', 'principled', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 0, 0, 'Advisor', 'Question List...', 0, 51, 2, 2, 3);
insert into tf_interview values(48, 'harmonious', 'skilled', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 0, 1, 'Programmer', 'Question List...', 1, 52, 2, 2, 3);
insert into tf_interview values(49, 'adorable', 'engaging', TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 0, 0, 'Developer', 'Question List...', 1, 53, 2, 2, 3);
insert into tf_interview values(50, 'successful', 'pleasurable', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'none', TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 0, 1, 'Tester', 'Question List...', 1, 54, 2, 2, 3);


--INSERT DUMMY VALUES INTO PLACEMENT
--insert into tf_placement(tf_placement_id, tf_placement_start_date, tf_placement_end_date, tf_client_id, tf_end_client_id, tf_associate_id) values(tf_placement_seq.nextval, start, end, client, endclient, assoc);

--batch 6
insert into tf_placement values(0, TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 82, 2, 3);
insert into tf_placement values(1, TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 83, 2, 3);
insert into tf_placement values(2, TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 84, 2, 3);
insert into tf_placement values(3, TO_TIMESTAMP('2017-4-24', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 85, 2, 3);
insert into tf_placement values(4, TO_TIMESTAMP('2017-4-24', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 86, 2, 3);
insert into tf_placement values(5, TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 87, 2, 3);
insert into tf_placement values(6, TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 88, 2, 3);
insert into tf_placement values(7, TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 89, 2, 3);
insert into tf_placement values(8, TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 90, 2, 3);
insert into tf_placement values(9, TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 91, 2, 3);
insert into tf_placement values(10, TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 92, 2, 3);
insert into tf_placement values(11, TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 93, 2, 3);

--batch 5
insert into tf_placement values(12, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 94, 1, 6);
insert into tf_placement values(13, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 95, 1, 6);
insert into tf_placement values(14, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 96, 1, 6);
insert into tf_placement values(15, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 97, 1, 6);
insert into tf_placement values(16, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 98, 1, 6);
insert into tf_placement values(17, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 99, 1, 6);
insert into tf_placement values(18, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 100, 1, 6);
insert into tf_placement values(19, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 101, 1, 6);
insert into tf_placement values(20, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 102, 2, 3);
insert into tf_placement values(21, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 103, 2, 3);
insert into tf_placement values(22, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 104, 2, 3);
insert into tf_placement values(23, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 105, 2, 3);

--batch 4
insert into tf_placement values(24, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-15', 'YYYY-MM-DD'), 55, 3, 4);
insert into tf_placement values(25, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-15', 'YYYY-MM-DD'), 56, 3, 4);
insert into tf_placement values(26, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-15', 'YYYY-MM-DD'), 57, 3, 4);
insert into tf_placement values(27, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-15', 'YYYY-MM-DD'), 58, 3, 4);
insert into tf_placement values(28, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-15', 'YYYY-MM-DD'), 59, 3, 4);
insert into tf_placement values(29, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-15', 'YYYY-MM-DD'), 60, 3, 4);
insert into tf_placement values(30, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-15', 'YYYY-MM-DD'), 61, 3, 4);
insert into tf_placement values(31, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-10-15', 'YYYY-MM-DD'), 62, 1, 6);
insert into tf_placement values(32, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-10-15', 'YYYY-MM-DD'), 63, 1, 6);
insert into tf_placement values(33, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-10-15', 'YYYY-MM-DD'), 64, 1, 6);
insert into tf_placement values(34, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-10-15', 'YYYY-MM-DD'), 65, 1, 6);

--batch 3
insert into tf_placement values(35, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 43, 2, 2);
insert into tf_placement values(36, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 44, 2, 2);
insert into tf_placement values(37, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 45, 2, 2);
insert into tf_placement values(38, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 46, 2, 2);
insert into tf_placement values(39, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 47, 2, 2);
insert into tf_placement values(40, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 47, 2, 2);
insert into tf_placement values(41, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 49, 2, 2);
insert into tf_placement values(42, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 52, 2, 2);
insert into tf_placement values(43, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 53, 2, 2);
insert into tf_placement values(44, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 54, 2, 2);

commit;




