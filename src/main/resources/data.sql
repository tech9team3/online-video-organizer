INSERT INTO USER( status, email, first_name, last_name, password, registration_date, username) VALUES ( true, "pera@para.com","Pera","Peric","para@1234",NOW(),"pera");
INSERT INTO USER( status, email, first_name, last_name, password, registration_date, username) VALUES ( false, "zika@zika.com","Zika","Zikic","zika@1234",NOW(),"zile");
INSERT INTO USER( status, email, first_name, last_name, password, registration_date, username) VALUES ( true, "mika@mika.com","Mika","Mikic","mika@1234",NOW(),"mika");
INSERT INTO USER( status, email, first_name, last_name, password, registration_date, username) VALUES ( true, "jeca@jeca.com","Jeca","Jeca","jeca@1234",NOW(),"pereca");
INSERT INTO USER( status, email, first_name, last_name, password, registration_date, username) VALUES ( true, "user@user.com","user","user","user@1234",NOW(),"user");
INSERT INTO USER( status, email, first_name, last_name, password, registration_date, username) VALUES ( true, "admin@admin.com","admin","admin","admin@1234",NOW(),"admin");
INSERT INTO USER( status, email, first_name, last_name, password, registration_date, username) VALUES ( true, "jewraaaa@gmail.com","Vladimir","Jevremov","jewra@1234",NOW(),"jewra");

INSERT INTO `role` (`type`) values("ROLE_USER");
INSERT INTO `role` (`type`) values("ROLE_ADMIN");

INSERT INTO user_roles(user_id,role_id) VALUES (1,1);
INSERT INTO user_roles(user_id,role_id) VALUES (2,1);
INSERT INTO user_roles(user_id,role_id) VALUES (3,1);
INSERT INTO user_roles(user_id,role_id) VALUES (4,1);
INSERT INTO user_roles(user_id,role_id) VALUES (5,1);
INSERT INTO user_roles(user_id,role_id) VALUES (6,1);
INSERT INTO user_roles(user_id,role_id) VALUES (6,2);
INSERT INTO user_roles(user_id,role_id) VALUES (7,1);
INSERT INTO user_roles(user_id,role_id) VALUES (7,2);

INSERT INTO video_list (title, user_id, visible) VALUES ("Moja play lista", 1,true);
INSERT INTO video_list (title, user_id, visible) VALUES ("Moja nova play lista", 1,false);
INSERT INTO video_list (title, user_id, visible) VALUES ("Spanish play lista", 2,false);
INSERT INTO video_list (title, user_id, visible) VALUES (" Shakira play lista", 2,true);
INSERT INTO video_list (title, user_id, visible) VALUES ("Moja rock play lista", 3,true);
INSERT INTO video_list (title, user_id, visible) VALUES ("Moja nova omiljena play lista", 4,true);
INSERT INTO video_list (title, user_id, visible) VALUES ("Moja nova ili stara play lista", 5,true);
INSERT INTO video_list (title, user_id, visible) VALUES ("Moja nova rock play lista", 6,true);



INSERT INTO video (visible, description, title, video_url, user_id) VALUES (true, "November Rain", "Guns N' Roses - November Rain","www.youtube.com/watch?v=8SbUC-UaAxE",1);
INSERT INTO video (visible, description, title, video_url, user_id) VALUES (true, "Don't Cry", "Guns N' Roses - Don't Cry","www.youtube.com/watch?v=zRIbf6JqkNc",1);
INSERT INTO video (visible, description, title, video_url, user_id) VALUES (true, "Crazy", "Aerosmith - Crazy","www.youtube.com/watch?v=NMNgbISmF4I",3);
INSERT INTO video (visible, description, title, video_url, user_id) VALUES (true, "Always", "Bon Jovi - Always","www.youtube.com/watch?v=9BMwcO6_hyA",4);

INSERT INTO video (visible, description, title, video_url, user_id, video_list_id) VALUES (false, "We Don’t Talk", "We Don't Talk Anymore","www.youtube.com/watch?v=3AtDnEC4zak",1,1);
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id) VALUES (false, "Treat", "Treat You Better","www.youtube.com/watch?v=lY2yjAdbvdQ",1,1);
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id) VALUES (true, "Description of Stitches", "Stitches","www.youtube.com/watch?v=VbfpW0pbvaU",1,1);
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id) VALUES (true, "A Short Film Created by Justin Bieber, Parris Goebel and Scott Scooter Braun", "Love Yourself ","www.youtube.com/watch?v=oyEuk8j8imI",1,1);
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id) VALUES (true, "Music video by Jonas Blue performing Mama.", "Mama ft. William Singe","www.youtube.com/watch?v=qPTfXwPf_HM",1,2);
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id) VALUES (true, "David Guetta feat Justin Bieber - 2U", "David Guetta ft Justin Bieber - 2U (The Victoria’s Secret Angels Lip Sync","www.youtube.com/watch?v=RqcjBLMaWCg",1,2);
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id) VALUES (true, "DJ Khaled - I'm the One ft. Justin Bieber, Quavo, Chance the Rapper, Lil Wayne", " I'm the One","www.youtube.com/watch?v=weeI1G46q0o",1,2);
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id) VALUES (true, "Jason Derulo - Swalla (feat. Nicki Minaj & Ty Dolla $ign)", "Swalla","www.youtube.com/watch?v=NGLxoKOvzu4",1,2);
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id) VALUES (true, "Shakira - Chantaje (Official video) ft. Maluma", "Chantaje","www.youtube.com/watch?v=6Mgqbai3fKo",2,4);
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id) VALUES (true, "Shakira - Me Enamoré (Official Video)", "Me Enamoré","www.youtube.com/watch?v=sPTn0QEhxds",2,4);
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id) VALUES (true, "Luis Fonsi - Despacito ft. Daddy Yankee", "Despacito","www.youtube.com/watch?v=kJQP7kiw5Fk",2,3);
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id) VALUES (true, "Enrique Iglesias - SUBEME LA RADIO (Official Video) ft. Descemer Bueno, Zion & Lennox", "SUBEME LA RADIO","www.youtube.com/watch?v=9sg-A-eS6Ig",2,3);

INSERT INTO video (visible, description, title, video_url, user_id) VALUES ( false, "Jack Ma's Life Advice Will Change Your Life", "Jack Ma's Life Advice","https://www.youtube.com/watch?v=lYGGpc2mMno",3);
INSERT INTO video (visible, description, title, video_url, user_id) VALUES ( false, "Crasdasazy", "asdas","https://vimeo.com/228724581",3);
INSERT INTO video (visible, description, title, video_url, user_id) VALUES ( false, "Alwasdasdays", "asdasd","http://www.dailymotion.com/video/x5wzr9a",4);
INSERT INTO video (visible, description, title, video_url, user_id) VALUES ( false, "Craasdasdzy", "asdasdas","http://www.dailymotion.com/video/x5wypz4",3);
INSERT INTO video (visible, description, title, video_url, user_id) VALUES ( false, "Alwasdasays", "Basdasd","http://www.dailymotion.com/video/x5psnvz",4);
INSERT INTO video (visible, description, title, video_url, user_id) VALUES ( true, "November Rain", "Guns N' Roses - November Rain","www.youtube.com/watch?v=8SbUC-UaAxE",7);


INSERT INTO video_tag (name,video_id) values ("cool",1);
INSERT INTO video_tag (name,video_id) values ("good",1);
INSERT INTO video_tag (name,video_id) values ("Super",1);
INSERT INTO video_tag (name,video_id) values ("cool 2",2);
INSERT INTO video_tag (name,video_id) values ("good 2",2);
INSERT INTO video_tag (name,video_id) values ("Super 2",2);
INSERT INTO video_tag (name,video_id) values ("cool 3",3);
INSERT INTO video_tag (name,video_id) values ("good 3",3);
INSERT INTO video_tag (name,video_id) values ("Super 3",3);
INSERT INTO video_tag (name,video_id) values ("cool 4",4);
INSERT INTO video_tag (name,video_id) values ("good 4",4);
INSERT INTO video_tag (name,video_id) values ("Super 4",4);

INSERT INTO COMMENT (content,creation_date,user_id,video_id) VALUES("Komentar 01, za neki video", NOW(), 2,1);
INSERT INTO COMMENT (content,creation_date,user_id,video_id) VALUES("Komentar 02, za neki video", NOW(), 3,1);
INSERT INTO COMMENT (content,creation_date,user_id,video_id) VALUES("Komentar 03, za neki video", NOW(), 4,1);
INSERT INTO COMMENT (content,creation_date,user_id,video_id) VALUES("Komentar 033, za neki video", NOW(), 1,1);
INSERT INTO COMMENT (content,creation_date,user_id,video_id) VALUES("Komentar 033, za neki video", NOW(), 1,1);









