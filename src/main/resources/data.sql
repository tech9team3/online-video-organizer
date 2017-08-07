INSERT INTO USER( email, first_name, last_name, password, registration_date, username) values ( "pera@para.com","Pera","Peric","para",NOW(),"pera");
INSERT INTO USER( email, first_name, last_name, password, registration_date, username) values ( "zika@zika.com","Zika","Zikic","zika",NOW(),"zile");
INSERT INTO USER( email, first_name, last_name, password, registration_date, username) values ( "mika@mika.com","Mika","Mikic","mika",NOW(),"mika");
INSERT INTO USER( email, first_name, last_name, password, registration_date, username) values ( "jeca@jeca.com","Jeca","Jeca","jeca",NOW(),"pereca");
INSERT INTO USER( email, first_name, last_name, password, registration_date, username) values ( "user@user.com","user","user","user",NOW(),"user");
INSERT INTO USER( email, first_name, last_name, password, registration_date, username) values ( "admin@admin.com","admin","admin","admin",NOW(),"admin");

INSERT INTO `role` (`type`) values("ROLE_USER");
INSERT INTO `role` (`type`) values("ROLE_ADMIN");

insert into user_roles(users_id,roles_id) values (1,1);
insert into user_roles(users_id,roles_id) values (2,1);
insert into user_roles(users_id,roles_id) values (3,1);
insert into user_roles(users_id,roles_id) values (4,1);
insert into user_roles(users_id,roles_id) values (5,1);
insert into user_roles(users_id,roles_id) values (6,1);
insert into user_roles(users_id,roles_id) values (6,2);