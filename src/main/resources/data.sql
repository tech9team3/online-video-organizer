INSERT INTO USER( email,status, first_name, last_name, password, registration_date, username) VALUES ( "pera@para.com",true,"Pera","Peric","para@1234",NOW(),"pera");
INSERT INTO USER( email,status, first_name, last_name, password, registration_date, username) VALUES ( "zika@zika.com",false,"Zika","Zikic","zika@1234",NOW(),"zile");
INSERT INTO USER( email,status, first_name, last_name, password, registration_date, username) VALUES ( "mika@mika.com",true,"Mika","Mikic","mika@1234",NOW(),"mika");
INSERT INTO USER( email,status, first_name, last_name, password, registration_date, username) VALUES ( "jeca@jeca.com",true,"Jeca","Jeca","jeca@1234",NOW(),"pereca");
INSERT INTO USER( email,status, first_name, last_name, password, registration_date, username) VALUES ( "user@user.com",true,"user","user","user@1234",NOW(),"user");
INSERT INTO USER( email,status, first_name, last_name, password, registration_date, username) VALUES ( "admin@admin.com",true,"admin","admin@1234","admin@1234",NOW(),"admin");

INSERT INTO `role` (`type`) values("ROLE_USER");
INSERT INTO `role` (`type`) values("ROLE_ADMIN");

INSERT INTO user_roles(user_id,role_id) values (1,1);
INSERT INTO user_roles(user_id,role_id) values (2,1);
INSERT INTO user_roles(user_id,role_id) values (3,1);
INSERT INTO user_roles(user_id,role_id) values (4,1);
INSERT INTO user_roles(user_id,role_id) values (5,1);
INSERT INTO user_roles(user_id,role_id) values (6,1);
INSERT INTO user_roles(user_id,role_id) values (6,2);

INSERT INTO COMMENT (content,creation_date,user_id) VALUES ("OVO JE Perin KOMENTAR",NOW(), 1);
INSERT INTO COMMENT (content,creation_date,user_id) VALUES ("OVO JOS JEDAN KOMENTAR OD Pere",NOW(), 1);

INSERT INTO COMMENT (content,creation_date,user_id) VALUES ("OVO JE Zikin KOMENTAR",NOW(), 2);
INSERT INTO COMMENT (content,creation_date,user_id) VALUES ("OVO JOS JEDAN KOMENTAR OD Zike",NOW(), 2);

INSERT INTO COMMENT (content,creation_date,user_id) VALUES ("OVO JE Mikin KOMENTAR",NOW(), 3);
INSERT INTO COMMENT (content,creation_date,user_id) VALUES ("OVO JOS JEDAN KOMENTAR OD Mike",NOW(), 3);

INSERT INTO COMMENT (content,creation_date,user_id) VALUES ("OVO JE Jecin KOMENTAR",NOW(), 4);
INSERT INTO COMMENT (content,creation_date,user_id) VALUES ("OVO JOS JEDAN KOMENTAR OD Jece",NOW(), 4);

INSERT INTO COMMENT (content,creation_date,user_id) VALUES ("OVO JE User KOMENTAR",NOW(), 5);
INSERT INTO COMMENT (content,creation_date,user_id) VALUES ("OVO JOS JEDAN KOMENTAR OD Usera",NOW(), 5);

INSERT INTO COMMENT (content,creation_date,user_id) VALUES ("OVO JE Admin KOMENTAR",NOW(), 6);
INSERT INTO COMMENT (content,creation_date,user_id) VALUES ("OVO JOS JEDAN KOMENTAR OD Admina",NOW(), 6);

INSERT INTO RATE(mark, user_id) VALUES(1,1);
INSERT INTO RATE(mark, user_id) VALUES(2,1);

INSERT INTO RATE(mark, user_id) VALUES(1,2);
INSERT INTO RATE(mark, user_id) VALUES(2,2);

INSERT INTO RATE(mark, user_id) VALUES(1,3);
INSERT INTO RATE(mark, user_id) VALUES(2,3);

INSERT INTO RATE(mark, user_id) VALUES(1,4);
INSERT INTO RATE(mark, user_id) VALUES(2,4);

INSERT INTO RATE(mark, user_id) VALUES(1,5);
INSERT INTO RATE(mark, user_id) VALUES(2,5);

INSERT INTO RATE(mark, user_id) VALUES(1,6);
INSERT INTO RATE(mark, user_id) VALUES(2,6);
