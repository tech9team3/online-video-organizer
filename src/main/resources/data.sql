INSERT INTO USER( email, first_name, last_name, password, registration_date, username) VALUES ( "pera@para.com","Pera","Peric","para@1234",NOW(),"pera");
INSERT INTO USER( email, status, first_name, last_name, password, registration_date, username) VALUES ( "zika@zika.com",false,"Zika","Zikic","zika@1234",NOW(),"zile");
INSERT INTO USER( email, first_name, last_name, password, registration_date, username) VALUES ( "mika@mika.com","Mika","Mikic","mika@1234",NOW(),"mika");
INSERT INTO USER( email, first_name, last_name, password, registration_date, username) VALUES ( "jeca@jeca.com","Jeca","Jeca","jeca@1234",NOW(),"pereca");
INSERT INTO USER( email, first_name, last_name, password, registration_date, username) VALUES ( "user@user.com","user","user","user@1234",NOW(),"user");
INSERT INTO USER( email, first_name, last_name, password, registration_date, username) VALUES ( "admin@admin.com","admin","admin@1234","admin@1234",NOW(),"admin");

INSERT INTO `role` (`type`) values("ROLE_USER");
INSERT INTO `role` (`type`) values("ROLE_ADMIN");

INSERT INTO user_roles(user_id,role_id) values (1,1);
INSERT INTO user_roles(user_id,role_id) values (2,1);
INSERT INTO user_roles(user_id,role_id) values (3,1);
INSERT INTO user_roles(user_id,role_id) values (4,1);
INSERT INTO user_roles(user_id,role_id) values (5,1);
INSERT INTO user_roles(user_id,role_id) values (6,1);
INSERT INTO user_roles(user_id,role_id) values (6,2);

INSERT INTO video (description, title, video_url, user_id) VALUES ("November Rain", "Guns N' Roses - November Rain","www.youtube.com/watch?v=8SbUC-UaAxE",1);
INSERT INTO video (description, title, video_url, user_id) VALUES ("Don't Cry", "Guns N' Roses - Don't Cry","www.youtube.com/watch?v=zRIbf6JqkNc",1);
INSERT INTO video (description, title, video_url, user_id) VALUES ("Crazy", "Aerosmith - Crazy","www.youtube.com/watch?v=NMNgbISmF4I",3);
INSERT INTO video (description, title, video_url, user_id) VALUES ("Always", "Bon Jovi - Always","www.youtube.com/watch?v=9BMwcO6_hyA",4);

INSERT INTO video_list (title, user_id) VALUES ("Moja play lista", 1);
INSERT INTO video (description, title, video_url, user_id, video_list_id) VALUES ("We Don’t Talk", "We Don't Talk Anymore","www.youtube.com/watch?v=3AtDnEC4zak",1,1);
INSERT INTO video_tag (name,video_id) values ("cool",1);
INSERT INTO video_tag (name,video_id) values ("good",1);
INSERT INTO video_tag (name,video_id) values ("Super",1);
INSERT INTO COMMENT (content,creation_date,user_id,video_id) VALUES("Komentar 01, za neki video", NOW(), 2,1);
INSERT INTO COMMENT (content,creation_date,user_id,video_id) VALUES("Komentar 02, za neki video", NOW(), 3,1);
INSERT INTO COMMENT (content,creation_date,user_id,video_id) VALUES("Komentar 03, za neki video", NOW(), 4,1);
INSERT INTO COMMENT (content,creation_date,user_id,video_id) VALUES("Komentar 033, za neki video", NOW(), 1,1);

INSERT INTO video (description, title, video_url, user_id, video_list_id) VALUES ("Treat", "Treat You Better","www.youtube.com/watch?v=lY2yjAdbvdQ",1,1);
INSERT INTO video_tag (name,video_id) values ("cool 2",2);
INSERT INTO video_tag (name,video_id) values ("good 2",2);
INSERT INTO video_tag (name,video_id) values ("Super 2",2);

INSERT INTO video (description, title, video_url, user_id, video_list_id) VALUES ("Description of Stitches", "Stitches","www.youtube.com/watch?v=VbfpW0pbvaU",1,1);
INSERT INTO video_tag (name,video_id) values ("cool 3",3);
INSERT INTO video_tag (name,video_id) values ("good 3",3);
INSERT INTO video_tag (name,video_id) values ("Super 3",3);

INSERT INTO video (description, title, video_url, user_id, video_list_id) VALUES ("A Short Film Created by Justin Bieber, Parris Goebel and Scott Scooter Braun", "Love Yourself ","www.youtube.com/watch?v=oyEuk8j8imI",1,1);
INSERT INTO video_tag (name,video_id) values ("cool 4",4);
INSERT INTO video_tag (name,video_id) values ("good 4",4);
INSERT INTO video_tag (name,video_id) values ("Super 4",4);

INSERT INTO video_list (title, user_id) VALUES ("Moja nova play lista", 1);
INSERT INTO video (description, title, video_url, user_id, video_list_id) VALUES ("Music video by Jonas Blue performing Mama.", "Mama ft. William Singe","www.youtube.com/watch?v=qPTfXwPf_HM",1,2);
INSERT INTO video (description, title, video_url, user_id, video_list_id) VALUES ("David Guetta feat Justin Bieber - 2U", "David Guetta ft Justin Bieber - 2U (The Victoria’s Secret Angels Lip Sync","www.youtube.com/watch?v=RqcjBLMaWCg",1,2);
INSERT INTO video (description, title, video_url, user_id, video_list_id) VALUES ("DJ Khaled - I'm the One ft. Justin Bieber, Quavo, Chance the Rapper, Lil Wayne", " I'm the One","www.youtube.com/watch?v=weeI1G46q0o",1,2);
INSERT INTO video (description, title, video_url, user_id, video_list_id) VALUES ("Jason Derulo - Swalla (feat. Nicki Minaj & Ty Dolla $ign)", "Swalla","www.youtube.com/watch?v=NGLxoKOvzu4",1,2);

INSERT INTO video_list (title, user_id) VALUES ("Spanish play lista", 2);
INSERT INTO video (description, title, video_url, user_id, video_list_id) VALUES ("Luis Fonsi - Despacito ft. Daddy Yankee", "Despacito","www.youtube.com/watch?v=kJQP7kiw5Fk",2,3);
INSERT INTO video (description, title, video_url, user_id, video_list_id) VALUES ("Enrique Iglesias - SUBEME LA RADIO (Official Video) ft. Descemer Bueno, Zion & Lennox", "SUBEME LA RADIO","www.youtube.com/watch?v=9sg-A-eS6Ig",2,3);

INSERT INTO video_list (title, user_id) VALUES (" Shakira play lista", 2);
INSERT INTO video (description, title, video_url, user_id, video_list_id) VALUES ("Shakira - Chantaje (Official video) ft. Maluma", "Chantaje","www.youtube.com/watch?v=6Mgqbai3fKo",2,4);
INSERT INTO video (description, title, video_url, user_id, video_list_id) VALUES ("Shakira - Me Enamoré (Official Video)", "Me Enamoré","www.youtube.com/watch?v=sPTn0QEhxds",2,4);


INSERT INTO video_list (title, user_id) VALUES ("Moja rock play lista", 3);

INSERT INTO video_list (title, user_id) VALUES ("Moja nova omiljena play lista", 4);

INSERT INTO video_list (title, user_id) VALUES ("Moja nova ili stara play lista", 5);

INSERT INTO video_list (title, user_id) VALUES ("Moja nova rock play lista", 6);



INSERT INTO RATE(mark,user_id,video_id) VALUES (2,2,1);
INSERT INTO RATE(mark,user_id,video_id) VALUES (3,3,2);
INSERT INTO RATE(mark,user_id,video_id) VALUES (4,4,2);
INSERT INTO RATE(mark,user_id,video_id) VALUES (5,5,1);

INSERT INTO RATE(mark,user_id,video_id) VALUES (2,1,3);
INSERT INTO RATE(mark,user_id,video_id) VALUES (3,2,4);
INSERT INTO RATE(mark,user_id,video_id) VALUES (4,3,4);
INSERT INTO RATE(mark,user_id,video_id) VALUES (5,4,3);

