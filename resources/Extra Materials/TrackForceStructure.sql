
create table tf_batch(
    tf_batch_id NUMBER PRIMARY KEY,
    tf_batch_name VARCHAR2(50),
    tf_batch_start_date TIMESTAMP,
    tf_batch_end_date TIMESTAMP,
    tf_curriculum_id NUMBER,
    tf_batch_location_id NUMBER

);


create table tf_batch_location(
    tf_batch_location_id NUMBER PRIMARY KEY,
    tf_batch_location_name VARCHAR(500)
);
create table tf_associate(
    tf_associate_id NUMBER PRIMARY KEY,
    tf_associate_first_name VARCHAR2(30),
    tf_associate_last_name VARCHAR2(30),
    tf_marketing_status_id NUMBER,
    tf_client_id NUMBER,
    tf_end_client_id NUMBER,
    tf_batch_id NUMBER
);

create table tf_placement(
    tf_placement_id NUMBER PRIMARY KEY,
    tf_placement_start_date TIMESTAMP,
    tf_placement_end_date TIMESTAMP,
    tf_client_id NUMBER,
    tf_end_client_id NUMBER,
    tf_associate_id NUMBER    
);

create table tf_interview(
    tf_interview_id NUMBER PRIMARY KEY,
    tf_interview_date TIMESTAMP,
    tf_interview_feedback VARCHAR2(2000),
    tf_client_id NUMBER,
    tf_end_client_id NUMBER,
    tf_interview_type_id NUMBER,
    tf_associate_id NUMBER    
);

create table tf_marketing_status(
    tf_marketing_status_id NUMBER PRIMARY KEY,
    tf_marketing_status_name VARCHAR(30)    
);
create table tf_client(
    tf_client_id NUMBER PRIMARY KEY,
    tf_client_name VARCHAR(100)    
);

create table tf_end_client(
    tf_end_client_id NUMBER PRIMARY KEY,
    tf_end_client_name VARCHAR(100)    
);

create table tf_interview_type(
    tf_interview_type_id NUMBER PRIMARY KEY,
    tf_interview_type_name VARCHAR(30)    
);

create table tf_curriculum(
    tf_curriculum_id NUMBER PRIMARY KEY,
    tf_curriculum_name VARCHAR(30)    
);

create table tf_user(
tf_user_id number PRIMARY KEY,
tf_username VARCHAR(20),
tf_hashpassword varchar(200),
tf_role_id number
);

create table tf_role(
tf_role_id number PRIMARY KEY,
tf_role_name varchar(20)
);

 
----------CONSTRAINTS-----------


alter table tf_batch
    ADD CONSTRAINT tf_batch__tf_location_FK
    FOREIGN KEY (tf_batch_location_id) REFERENCES tf_batch_location(tf_batch_location_id);
 
alter table tf_batch
    ADD CONSTRAINT tf_batch__tf_curriculum_FK
    FOREIGN KEY (tf_curriculum_id) REFERENCES tf_curriculum(tf_curriculum_id);
alter table tf_associate
    ADD CONSTRAINT tf_associate__tf_client_FK
    FOREIGN KEY (tf_client_id) REFERENCES tf_client(tf_client_id);
alter table tf_associate
    ADD CONSTRAINT tf_associate__tf_end_client_FK
    FOREIGN KEY (tf_end_client_id) REFERENCES tf_end_client(tf_end_client_id);
alter table tf_associate
    ADD CONSTRAINT tf_assoc__tf_market_stat_FK
    FOREIGN KEY (tf_marketing_status_id) REFERENCES tf_marketing_status(tf_marketing_status_id);
    
alter table tf_associate
    ADD CONSTRAINT tf_associate__tf_batch_FK
    FOREIGN KEY (tf_batch_id) REFERENCES tf_batch(tf_batch_id);
    
alter table tf_placement
    ADD CONSTRAINT tf_placement__tf_assoc_FK
    FOREIGN KEY (tf_associate_id) REFERENCES tf_associate(tf_associate_id);

alter table tf_placement
    ADD CONSTRAINT tf_placement__tf_client_FK
    FOREIGN KEY (tf_client_id) REFERENCES tf_client(tf_client_id);    

alter table tf_placement
    ADD CONSTRAINT tf_place__tf_end_client_FK
    FOREIGN KEY (tf_end_client_id) REFERENCES tf_end_client(tf_end_client_id);    

alter table tf_interview
    ADD CONSTRAINT tf_interview__tf_assoc_FK
    FOREIGN KEY (tf_associate_id) REFERENCES tf_associate(tf_associate_id);    

alter table tf_interview
    ADD CONSTRAINT tf_interview__tf_client_FK
    FOREIGN KEY (tf_client_id) REFERENCES tf_client(tf_client_id);

alter table tf_interview
    ADD CONSTRAINT tf_interview__tf_end_client_FK
    FOREIGN KEY (tf_end_client_id) REFERENCES tf_end_client(tf_end_client_id);
    
alter table tf_interview
    ADD CONSTRAINT tf_interv__tf_interv_type_FK
    FOREIGN KEY (tf_interview_type_id) REFERENCES tf_interview_type(tf_interview_type_id);
    

alter table tf_user
    ADD CONSTRAINT tf_user__tf_role_FK
    FOREIGN KEY (tf_role_id) REFERENCES tf_role(tf_role_id);


commit;