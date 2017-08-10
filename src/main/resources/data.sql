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

 insert into video_list ( title, user_id) values ( "Naslov", 1);
 insert into video ( description, title,video_list_id,video_url) values ( "Ovo je neki opis", "ovo je neki naslov", 1, "https://github.com");
 insert into comment ( content, creation_date, user_id, video_id) values (" ovo je neki komentar", NOW(),1,1);
