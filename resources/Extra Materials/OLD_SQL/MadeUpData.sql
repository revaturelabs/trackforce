-----------------------------------------------------
----- This section is for adding Dummy Values--------
-----------------------------------------------------


--INSERT DUMMY VALUES INTO TF_END_CLIENT TABLE
insert into admin.tf_end_client values (1, 'Accenture');
insert into admin.tf_end_client values (2, 'Infosys');
insert into admin.tf_end_client values (3, 'Federal Reserve');
insert into admin.tf_end_client values (4, 'Fannie Mae');
insert into admin.tf_end_client values (5, 'Revature');
insert into admin.tf_end_client values (6, 'Sallie Mae');

--INSERT DUMMY VALUES INTO TF_CLIENT TABLE
insert into admin.tf_client values (1, 'Accenture');
insert into admin.tf_client values (2, 'Infosys');
insert into admin.tf_client values (3, 'AFS');
insert into admin.tf_client values (4, 'Hexaware');
insert into admin.tf_client values (5, 'Revature');

--INSERT DUMMY VALUES INTO TF_INTERVIEW_TYPE TABLE
insert into admin.tf_interview_type values (1, 'Phone');
insert into admin.tf_interview_type values (2, 'Online');
insert into admin.tf_interview_type values (3, 'On-Site');
insert into admin.tf_interview_type values (4, 'Skype');
--INSERT DUMMY VALUES INTO TF_CURRICULUM TABLE
insert into admin.tf_curriculum values (1, 'JTA');
insert into admin.tf_curriculum values (2, 'Java');
insert into admin.tf_curriculum values (3, '.Net');
insert into admin.tf_curriculum values (4, 'PEGA');
insert into admin.tf_curriculum values (5, 'DynamicCRM');
insert into admin.tf_curriculum values (6, 'Salesforce');
insert into admin.tf_curriculum values (7, 'Microservices');

--INSERT DUMMY VALUES INTO TF_MARKETING_STATUS
INSERT INTO admin.tf_marketing_status VALUES(1, 'MAPPED: TRAINING');
INSERT INTO admin.tf_marketing_status VALUES(2, 'MAPPED: RESERVED');
INSERT INTO admin.tf_marketing_status VALUES(3, 'MAPPED: SELECTED');
INSERT INTO admin.tf_marketing_status VALUES(4, 'MAPPED: CONFIRMED');
INSERT INTO admin.tf_marketing_status VALUES(5, 'MAPPED: DEPLOYED');

INSERT INTO admin.tf_marketing_status VALUES(6, 'UNMAPPED: TRAINING');
INSERT INTO admin.tf_marketing_status VALUES(7, 'UNMAPPED: OPEN');
INSERT INTO admin.tf_marketing_status VALUES(8, 'UNMAPPED: SELECTED');
INSERT INTO admin.tf_marketing_status VALUES(9, 'UNMAPPED: CONFIRMED');
INSERT INTO admin.tf_marketing_status VALUES(10, 'UNMAPPED: DEPLOYED');
INSERT INTO admin.tf_marketing_status VALUES(11, 'DIRECTLY PLACED');
INSERT INTO admin.tf_marketing_status VALUES(12, 'TERMINATED');


--INSERT DUMMY VALUES INTO TF_BATCH_LOCATION
insert into admin.tf_batch_location values(1, 'Revature LLC, 11730 Plaza America Drive, 2nd Floor | Reston, VA 20190');
insert into admin.tf_batch_location values(2, 'UMUC');
insert into admin.tf_batch_location values(3, 'USF');
insert into admin.tf_batch_location values(4, 'SkySong Innovation Center, 1475 N. Scottsdale Road, Scottsdale, AZ 85257');
insert into admin.tf_batch_location values(5, 'Tech Incubator at Queens College, 65-30 Kissena Blvd, CEP Hall 2, Queens, NY 11367');
insert into admin.tf_batch_location values(6, 'CUNY-SPS 119 West 31st Street, New York, NY 10001');

--INSERT DUMMY VALUES INTO BATCH
insert into admin.tf_batch values (0, '1712 Dec04 AP-USF', TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-02-16', 'YYYY-MM-DD'),2,3);
insert into admin.tf_batch values (1, '1710 Oct09 PEGA', TO_TIMESTAMP('2017-10-9', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-12-15', 'YYYY-MM-DD'),4,1);
insert into admin.tf_batch values (2, '1709 Sept11 JTA', TO_TIMESTAMP('2017-09-11', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-11-17', 'YYYY-MM-DD'),1,1);
insert into admin.tf_batch values (3, '1707 Jul24 Java', TO_TIMESTAMP('2017-07-24', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-09-29', 'YYYY-MM-DD'),2,1);
insert into admin.tf_batch values (4, '1707 Jul10 PEGA', TO_TIMESTAMP('2017-07-10', 'YYYY-MM-DD'), TO_TIMESTAMP('2017-09-15', 'YYYY-MM-DD'),4,1);

insert into admin.tf_batch values(5, '1701 Jan09 Java', TO_TIMESTAMP('1/9/2017', 'MM/DD/YYYY'), TO_TIMESTAMP('3/17/2017', 'MM/DD/YYYY'),2,1);
insert into admin.tf_batch values(6, '1701 Jan30 NET', TO_TIMESTAMP('1/30/2017', 'MM/DD/YYYY'), TO_TIMESTAMP('4/17/2017', 'MM/DD/YYYY'),3,1);
insert into admin.tf_batch values(7, '1709 Sep18 Salesforce', TO_TIMESTAMP('9/18/2017', 'MM/DD/YYYY'), TO_TIMESTAMP('12/8/2017', 'MM/DD/YYYY'),6,1);
insert into admin.tf_batch values(8, '1709 Sep25 Java AP-CUNY', TO_TIMESTAMP('9/25/2017', 'MM/DD/YYYY'), TO_TIMESTAMP('12/1/2017', 'MM/DD/YYYY'),2,6);
insert into admin.tf_batch values(9, '1712 Dec04-2', TO_TIMESTAMP('12/4/2017', 'MM/DD/YYYY'), TO_TIMESTAMP('2/9/2018', 'MM/DD/YYYY'),2,1);
--insert into admin.tf_batch values (admin.tf_batch_seq.nextval, '1712 Dec04 AP-USF', TO_TIMESTAMP('2017-12-04', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-02-16', 'YYYY-MM-DD'), 2, 1, 3);

--INSERT DUMMY VALUES INTO ASSOCIATES
--insert into admin.tf_associate values (admin.tf_associate_seq.nextval, 'name', 'name', status, batch);

--1712 Dec04 AP-USF
insert into admin.tf_associate values (1, 'Frank', 'Hind', 1, 2,1, 0);
insert into admin.tf_associate values (2, 'Thomas', 'Page', 1,2,1, 0);
insert into admin.tf_associate values (3, 'Lucas', 'Normand', 1,2,1, 0);
insert into admin.tf_associate values (4, 'Jhonnie', 'Cole', 1,2,1, 0);
insert into admin.tf_associate values (5, 'Ramona', 'Reyes', 1,2,1, 0);
insert into admin.tf_associate values (6, 'Grace', 'Noland', 1,2,1, 0);
insert into admin.tf_associate values (7, 'Casey', 'Morton', 1,2,1, 0);
insert into admin.tf_associate values (8, 'Gustavo', 'Brady', 1,2,1, 0);
insert into admin.tf_associate values (9, 'Glen', 'Holloway', 1,2,1, 0);
insert into admin.tf_associate values (10, 'Leeroy', 'Jenkins', 1,2,1, 0);
insert into admin.tf_associate values (11, 'Jeanne', 'Watts', 1,2,1, 0);
insert into admin.tf_associate values (12, 'Carol', 'Ruiz', 1,2,1, 0);

--1710 Oct09 PEGA
insert into admin.tf_associate values (13, 'Trevor', 'Hampton', 1,2,4, 1);
insert into admin.tf_associate values (14, 'Jennie', 'Hudson', 1,2,4, 1);
insert into admin.tf_associate values (15, 'David', 'Haynes', 1,2,4, 1);
insert into admin.tf_associate values (16, 'Ira', 'Mullins', 1,2,4, 1);
insert into admin.tf_associate values (17, 'Alexandra', 'Mitchell', 1,2,4, 1);
insert into admin.tf_associate values (18, 'Bradley', 'Harris', 1,2,4, 1);
insert into admin.tf_associate values (19, 'Gerardo', 'Roy', 1,2,4, 1);
insert into admin.tf_associate values (20, 'Jacob', 'Cortez', 1,2,4, 1);
insert into admin.tf_associate values (21, 'Kathryn', 'Young', 1,2,4, 1);
insert into admin.tf_associate values (22, 'Allen', 'Walker', 1,2,4, 1);
insert into admin.tf_associate values (23, 'Gustavo', 'Reed', 1,2,4, 1);
insert into admin.tf_associate values (24, 'Robin', 'Norton', 1,2,4, 1);
insert into admin.tf_associate values (25, 'Julia', 'Drake', 1, 2,4,1);
insert into admin.tf_associate values (26, 'Joan', 'Evans', 1, 2,4,1);
insert into admin.tf_associate values (27, 'Larry', 'Holl', 1,2,4, 1);

--1709 Sept11 JTA
insert into admin.tf_associate values (28, 'Vito', 'Plante', 1,1,1, 2);
insert into admin.tf_associate values (29, 'Crystal', 'Couch', 1,1,1, 2);
insert into admin.tf_associate values (30, 'Adam', 'Collins', 1,1,1, 2);
insert into admin.tf_associate values (31, 'Bert', 'Bryant', 1,1,1, 2);
insert into admin.tf_associate values (32, 'Nicholas', 'Griffin', 1,1,1, 2);
insert into admin.tf_associate values (33, 'Joe', 'Cook', 1,1,1, 2);
insert into admin.tf_associate values (34, 'Andrew', 'Bennet', 1, 1,1,2);
insert into admin.tf_associate values (35, 'Phillip', 'Henderson', 1,1,1, 2);
insert into admin.tf_associate values (36, 'Gary', 'Ward', 1,1,1, 2);
insert into admin.tf_associate values (37, 'Bruce', 'Long', 1,1,1, 2);
insert into admin.tf_associate values (38, 'Russel', 'Peters', 1,1,1, 2);
insert into admin.tf_associate values (39, 'Emily', 'Baker', 1,1,1, 2);
insert into admin.tf_associate values (40, 'Jake', 'King', 1,1,1, 2);
insert into admin.tf_associate values (41, 'Jamie', 'Campbell', 1,1,1, 2);
insert into admin.tf_associate values (42, 'Larry', 'Hughes', 1,1,1, 2);

--1707 Jul24 Java
insert into admin.tf_associate values (43, 'Carlos', 'Adams', 5,2,2, 3);
insert into admin.tf_associate values (44, 'Victor', 'Bailey', 5,2,2, 3);
insert into admin.tf_associate values (45, 'Harold', 'Cartor', 5,2,2, 3);
insert into admin.tf_associate values (46, 'Judith', 'Rivera', 5, 2,2,3);
insert into admin.tf_associate values (47, 'Maria', 'Smith', 5,2,2, 3);
insert into admin.tf_associate values (48, 'Steven', 'Simmons', 5,2,2, 3);
insert into admin.tf_associate values (49, 'Donna', 'Hall', 5,2,2, 3);
insert into admin.tf_associate values (50, 'Samuel', 'Price', 7, 2,2,3);
insert into admin.tf_associate values (51, 'Jean', 'Jackson', 7,2,2, 3);
insert into admin.tf_associate values (52, 'Adam', 'Stewart', 4,2,2, 3);
insert into admin.tf_associate values (53, 'Gary', 'Nelson', 4,2,2, 3);
insert into admin.tf_associate values (54, 'Peter', 'Morgan', 4, 2,2,3);

--1707 Jul10 Pega
insert into admin.tf_associate values (55, 'Jack', 'Morris', 5,3,4, 4);
insert into admin.tf_associate values (56, 'Randy', 'Parker', 5,3,4, 4);
insert into admin.tf_associate values (57, 'Justin', 'Flores', 5, 3,4,4);
insert into admin.tf_associate values (58, 'Richard', 'Gray', 5,3,4, 4);
insert into admin.tf_associate values (59, 'Jesse', 'Turner', 4,3,4, 4);
insert into admin.tf_associate values (60, 'John', 'Baker', 5,3,4, 4);
insert into admin.tf_associate values (61, 'Benjamin', 'Jones', 5,3,4, 4);
insert into admin.tf_associate values (62, 'Todd', 'Torres', 7,3,4, 4);
insert into admin.tf_associate values (63, 'Kathleen', 'Kelly', 7,3,4, 4);
insert into admin.tf_associate values (64, 'Sara', 'Long', 7,3,4, 4);
insert into admin.tf_associate values (65, 'Linda', 'Russell', 7, 3,4,4);
insert into admin.tf_associate values (66, 'Brenda', 'Wilson', 10,3,4, 4);
insert into admin.tf_associate values (67, 'Betty', 'Green', 10,3,4, 4);
insert into admin.tf_associate values (68, 'Bobby', 'Edwards', 10,3,4, 4);
insert into admin.tf_associate values (69, 'Marilyn', 'Allens', 10,3,4, 4);

--batch 7
insert into admin.tf_associate values (70, 'Willoughby', 'Sherwood', 1,3,6, 7);
insert into admin.tf_associate values (71, 'Tomi', 'Nikkole', 1,3,6, 7);
insert into admin.tf_associate values (72, 'Newt', 'Jaki', 1,3,6, 7);
insert into admin.tf_associate values (73, 'Darnell', 'Mervyn', 1, 3,6,7);
insert into admin.tf_associate values (74, 'Claire', 'Connor', 1,3,6, 7);
insert into admin.tf_associate values (75, 'Edmonde', 'Sora', 1, 3,6,7);
insert into admin.tf_associate values (76, 'Kaitlyn', 'Abbie', 1, 3,6,7);
insert into admin.tf_associate values (77, 'Natsuko', 'Lily', 1,3,6, 7);
insert into admin.tf_associate values (78, 'Ben', 'Gabrielle', 1,3,6, 7);
insert into admin.tf_associate values (79, 'Alberta', 'Arienne', 1, 3,6,7);
insert into admin.tf_associate values (80, 'Merline', 'Thom', 1,3,6, 7);
insert into admin.tf_associate values (81, 'Hachirou', 'Kasumi', 1,3,6, 7);

--batch 6
insert into admin.tf_associate values (82, 'Leigh', 'Jordon', 10, 1,3, 6);
insert into admin.tf_associate values (83, 'Amity', 'Brandi', 10,1,3, 6);
insert into admin.tf_associate values (84, 'Merlyn', 'Ros', 10,1,3, 6);
insert into admin.tf_associate values (85, 'Primula', 'Gyles', 10, 1,3,6);
insert into admin.tf_associate values (86, 'Ethel', 'Jemima', 10,1,3, 6);
insert into admin.tf_associate values (87, 'Jonelle', 'Eugenie', 10, 1,3,6);
insert into admin.tf_associate values (88, 'Evangelina', 'Harlan', 10, 1,3,6);
insert into admin.tf_associate values (89, 'Anjelica', 'Babs', 10,1,3, 6);
insert into admin.tf_associate values (90, 'Jerred', 'Yuko', 10,1,3, 6);
insert into admin.tf_associate values (91, 'Cecile', 'Colton', 10,1,3, 6);
insert into admin.tf_associate values (92, 'Ulla', 'Gilbert', 10,1,3, 6);
insert into admin.tf_associate values (93, 'Teija', 'Mariko', 10, 1,3,6);

--batch 5
insert into admin.tf_associate values (94, 'Maryann', 'Zechariah', 10, 2,2, 5);
insert into admin.tf_associate values (95, 'Nichola', 'Dennis', 10, 2,2, 5);
insert into admin.tf_associate values (96, 'Githa', 'Nyree', 10, 2,2, 5);
insert into admin.tf_associate values (97, 'Chelsey', 'Gwyneth', 10, 2,2, 5);
insert into admin.tf_associate values (98, 'Jepson', 'Orson', 10, 2,2, 5);
insert into admin.tf_associate values (99, 'Careen', 'Jeffery', 10, 2,2, 5);
insert into admin.tf_associate values (100, 'Malachi', 'Nic', 10, 2,2, 5);
insert into admin.tf_associate values (101, 'Farran', 'Sawyer', 10, 2,2, 5);
insert into admin.tf_associate values (102, 'Desiree', 'Gayelord', 10,  2,2,5);
insert into admin.tf_associate values (103, 'Mae', 'Lorrie', 10,  2,2,5);
insert into admin.tf_associate values (104, 'Jon', 'Hamilton', 10, 2,2, 5);
insert into admin.tf_associate values (105, 'Marshal', 'Parnel', 10, 2,2, 5);

--batch 8
insert into admin.tf_associate values (106, 'Ayame', 'Shun', 1, 4,2,8);
insert into admin.tf_associate values (107, 'Katashi', 'He', 1,4,2, 8);
insert into admin.tf_associate values (108, 'Jiahao', 'Shiro', 1,4,2, 8);
insert into admin.tf_associate values (109, 'Naoko', 'Hikaru', 1,4,2, 8);
insert into admin.tf_associate values (110, 'Chihiro', 'Moriko', 1,4,2, 8);
insert into admin.tf_associate values (111, 'Bai', 'Kazuo', 1,4,2, 8);
insert into admin.tf_associate values (112, 'Etsuko', 'Fang', 1,4,2, 8);
insert into admin.tf_associate values (113, 'Hideki', 'Qing', 1,4,2, 8);
insert into admin.tf_associate values (114, 'Masaru', 'Ayako', 1, 4,2,8);
insert into admin.tf_associate values (115, 'Megumi', 'Mari', 1,4,2, 8);
insert into admin.tf_associate values (116, 'Hiroko', 'Hiroshi', 1,4,2, 8);
insert into admin.tf_associate values (117, 'Sumiko', 'Mai', 1,4,2, 8);

--batch9
insert into admin.tf_associate values (118, 'Kanon', 'Bai', 1, 5,null,9);
insert into admin.tf_associate values (119, 'Hikaru', 'Yuu', 1,5,null, 9);
insert into admin.tf_associate values (120, 'Shiori', 'Takeshi', 1, 5,null,9);
insert into admin.tf_associate values (121, 'Jianhong', 'Youta', 1,5,null, 9);
insert into admin.tf_associate values (122, 'Goro', 'Bai', 1,5,null, 9);
insert into admin.tf_associate values (123, 'Shufen', 'Miyu', 1,5,null, 9);
insert into admin.tf_associate values (124, 'Kenji', 'He', 1, 5,null,9);
insert into admin.tf_associate values (125, 'Ren', 'Hayate', 1,5,null, 9);
insert into admin.tf_associate values (126, 'Momoko', 'Miki', 1,5,null, 9);
insert into admin.tf_associate values (127, 'Takashi', 'Ling', 1,5,null, 9);
insert into admin.tf_associate values (128, 'Setsuko', 'Yuuki', 1,5,null, 9);
insert into admin.tf_associate values (129, 'Megumi', 'Kato', 1,5,null, 9);


--INSERT DUMMY VALUES INTO INTERVIEW
--insert into admin.tf_interview values(admin.tf_interview_seq.nextval, date, feedback, client, endclient, inttype, associd);

--batch 6
insert into admin.tf_interview values(0, TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), 'Good', 2, 3, 3, 82);
insert into admin.tf_interview values(1, TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), 'Good', 2, 3, 3, 83);
insert into admin.tf_interview values(2, TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), 'Good', 2, 3, 3, 84);
insert into admin.tf_interview values(3, TO_TIMESTAMP('2017-4-24', 'YYYY-MM-DD'), 'Good', 2, 3, 3, 85);
insert into admin.tf_interview values(4, TO_TIMESTAMP('2017-4-24', 'YYYY-MM-DD'), 'Good', 2, 3, 3, 86);
insert into admin.tf_interview values(5, TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), 'Good', 2, 3, 1, 87);
insert into admin.tf_interview values(6, TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), 'Good', 2, 3, 1, 88);
insert into admin.tf_interview values(7, TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), 'Good', 2, 3, 4, 89);
insert into admin.tf_interview values(8, TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), 'Good', 2, 3, 4, 90);
insert into admin.tf_interview values(9, TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), 'Good', 2, 3, 1, 91);
insert into admin.tf_interview values(10, TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), 'Good', 2, 3, 1, 92);
insert into admin.tf_interview values(11, TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), 'Good', 2, 3, 1, 93);

--batch5
insert into admin.tf_interview values(12, TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), 'Good', 1, 6, 1, 94);
insert into admin.tf_interview values(13, TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), 'Good', 1, 6, 4, 95);
insert into admin.tf_interview values(14, TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), 'Good', 1, 6, 4, 96);
insert into admin.tf_interview values(15, TO_TIMESTAMP('2017-3-18', 'YYYY-MM-DD'), 'Good', 1, 6, 1, 97);
insert into admin.tf_interview values(16, TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'Good', 1, 6, 4, 98);
insert into admin.tf_interview values(17, TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'Good', 1, 6, 1, 99);
insert into admin.tf_interview values(18, TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'Good', 1, 6, 1, 100);
insert into admin.tf_interview values(19, TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'Good', 1, 6, 4, 101);
insert into admin.tf_interview values(20, TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'Good', 2, 3, 1, 102);
insert into admin.tf_interview values(21, TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'Good', 2, 3, 1, 103);
insert into admin.tf_interview values(22, TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'Good', 2, 3, 1, 104);
insert into admin.tf_interview values(23, TO_TIMESTAMP('2017-3-21', 'YYYY-MM-DD'), 'Good', 2, 3, 1, 105);

--batch-4
insert into admin.tf_interview values(24, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'Good', 3, 4, 3, 55);
insert into admin.tf_interview values(25, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'Good', 3, 4, 3, 56);
insert into admin.tf_interview values(26, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'Good', 3, 4, 3, 57);
insert into admin.tf_interview values(27, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'Good', 3, 4, 3, 58);
insert into admin.tf_interview values(28, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'Good', 3, 4, 3, 59);
insert into admin.tf_interview values(29, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'Good', 3, 4, 3, 60);
insert into admin.tf_interview values(30, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'Good', 3, 4, 3, 61);

insert into admin.tf_interview values(31, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'No Good', 3, 4, 3, 62);
insert into admin.tf_interview values(32, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'No Good', 3, 4, 3, 63);
insert into admin.tf_interview values(33, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'No Good', 3, 4, 3, 64);
insert into admin.tf_interview values(34, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'No Good', 3, 4, 3, 65);

insert into admin.tf_interview values(35, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'Good', 1, 6, 3, 66);
insert into admin.tf_interview values(36, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'Good', 1, 6, 3, 67);
insert into admin.tf_interview values(37, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'Good', 1, 6, 3, 68);
insert into admin.tf_interview values(38, TO_TIMESTAMP('2017-9-27', 'YYYY-MM-DD'), 'Good', 1, 6, 3, 69);

--batch3
insert into admin.tf_interview values(39, TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'Good', 2, 2, 3, 43);
insert into admin.tf_interview values(40, TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'Good', 2, 2, 3, 44);
insert into admin.tf_interview values(41, TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'Good', 2, 2, 3, 45);
insert into admin.tf_interview values(42, TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'Good', 2, 2, 3, 46);
insert into admin.tf_interview values(43, TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'Good', 2, 2, 3, 47);
insert into admin.tf_interview values(44, TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'Good', 2, 2, 3, 48);
insert into admin.tf_interview values(45, TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'Good', 2, 2, 3, 49);
insert into admin.tf_interview values(46, TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'No Good', 2, 2, 3, 50);
insert into admin.tf_interview values(47, TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'No Good', 2, 2, 3, 51);
insert into admin.tf_interview values(48, TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'Good', 2, 2, 3, 52);
insert into admin.tf_interview values(49, TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'Good', 2, 2, 3, 53);
insert into admin.tf_interview values(50, TO_TIMESTAMP('2017-11-1', 'YYYY-MM-DD'), 'Good', 2, 2, 3, 54);


--INSERT DUMMY VALUES INTO PLACEMENT
--insert into admin.tf_placement values(admin.tf_placement_seq.nextval, start, end, client, endclient, assoc);

--batch 6
insert into admin.tf_placement values(0, TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 2, 3, 82);
insert into admin.tf_placement values(1, TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 2, 3, 83);
insert into admin.tf_placement values(2, TO_TIMESTAMP('2017-4-23', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 2, 3, 84);
insert into admin.tf_placement values(3, TO_TIMESTAMP('2017-4-24', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 2, 3, 85);
insert into admin.tf_placement values(4, TO_TIMESTAMP('2017-4-24', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 2, 3, 86);
insert into admin.tf_placement values(5, TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 2, 3, 87);
insert into admin.tf_placement values(6, TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 2, 3, 88);
insert into admin.tf_placement values(7, TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 2, 3, 89);
insert into admin.tf_placement values(8, TO_TIMESTAMP('2017-7-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 2, 3, 90);
insert into admin.tf_placement values(9, TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 2, 3, 91);
insert into admin.tf_placement values(10, TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 2, 3, 92);
insert into admin.tf_placement values(11, TO_TIMESTAMP('2017-7-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-23', 'YYYY-MM-DD'), 2, 3, 93);

--batch 5
insert into admin.tf_placement values(12, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 1, 6, 94);
insert into admin.tf_placement values(13, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 1, 6, 95);
insert into admin.tf_placement values(14, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 1, 6, 96);
insert into admin.tf_placement values(15, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 1, 6, 97);
insert into admin.tf_placement values(16, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 1, 6, 98);
insert into admin.tf_placement values(17, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 1, 6, 99);
insert into admin.tf_placement values(18, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 1, 6, 100);
insert into admin.tf_placement values(19, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 1, 6, 101);
insert into admin.tf_placement values(20, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 2, 3, 102);
insert into admin.tf_placement values(21, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 2, 3, 103);
insert into admin.tf_placement values(22, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 2, 3, 104);
insert into admin.tf_placement values(23, TO_TIMESTAMP('2017-4-18', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-18', 'YYYY-MM-DD'), 2, 3, 105);

--batch 4
insert into admin.tf_placement values(24, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-15', 'YYYY-MM-DD'), 3, 4,  55);
insert into admin.tf_placement values(25, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-15', 'YYYY-MM-DD'), 3, 4,  56);
insert into admin.tf_placement values(26, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-15', 'YYYY-MM-DD'), 3, 4,  57);
insert into admin.tf_placement values(27, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-15', 'YYYY-MM-DD'), 3, 4,  58);
insert into admin.tf_placement values(28, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-15', 'YYYY-MM-DD'), 3, 4,  59);
insert into admin.tf_placement values(29, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-15', 'YYYY-MM-DD'), 3, 4,  60);
insert into admin.tf_placement values(30, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-4-15', 'YYYY-MM-DD'), 3, 4,  61);
insert into admin.tf_placement values(31, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-10-15', 'YYYY-MM-DD'), 1, 6,  62);
insert into admin.tf_placement values(32, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-10-15', 'YYYY-MM-DD'), 1, 6,  63);
insert into admin.tf_placement values(33, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-10-15', 'YYYY-MM-DD'), 1, 6,  64);
insert into admin.tf_placement values(34, TO_TIMESTAMP('2017-10-15', 'YYYY-MM-DD'), TO_TIMESTAMP('2018-10-15', 'YYYY-MM-DD'), 1, 6,  65);

--batch 3
insert into admin.tf_placement values(35, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 2, 2, 43);
insert into admin.tf_placement values(36, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 2, 2, 44);
insert into admin.tf_placement values(37, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 2, 2, 45);
insert into admin.tf_placement values(38, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 2, 2, 46);
insert into admin.tf_placement values(39, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 2, 2, 47);
insert into admin.tf_placement values(40, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 2, 2, 47);
insert into admin.tf_placement values(41, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 2, 2, 49);
insert into admin.tf_placement values(42, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 2, 2, 52);
insert into admin.tf_placement values(43, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 2, 2, 53);
insert into admin.tf_placement values(44, TO_TIMESTAMP('2017-11-6', 'YYYY-MM-DD') , TO_TIMESTAMP('2018-11-6', 'YYYY-MM-DD'), 2, 2, 54);

commit;