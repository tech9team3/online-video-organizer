INSERT INTO USER( status, email, first_name, last_name, password, registration_date, username) VALUES ( true, "pera@para.com","Pera","Peric","para@1234",NOW(),"perazdera");
INSERT INTO USER( status, email, first_name, last_name, password, registration_date, username) VALUES ( false, "zika@zika.com","Zika","Zikic","zika@1234",NOW(),"zilekile");
INSERT INTO USER( status, email, first_name, last_name, password, registration_date, username) VALUES ( true, "mika@mika.com","Mika","Mikic","mika@1234",NOW(),"mikaslika");
INSERT INTO USER( status, email, first_name, last_name, password, registration_date, username) VALUES ( true, "jeca@jeca.com","Jeca","Jeca","jeca@1234",NOW(),"pereca");
INSERT INTO USER( status, email, first_name, last_name, password, registration_date, username) VALUES ( true, "user@user.com","user","user","user@1234",NOW(),"userica");
INSERT INTO USER( status, email, first_name, last_name, password, registration_date, username) VALUES ( true, "admin@admin.com","admin","admin","admin@1234",NOW(),"admin");
INSERT INTO USER( status, email, first_name, last_name, password, registration_date, username) VALUES ( true, "organizetech9@gmail.com","Vladimir","Jevremov","jewra@1234",NOW(),"jewraaa");

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
INSERT INTO video_list (title, user_id, visible) VALUES ("Shakira play lista", 2,true);
INSERT INTO video_list (title, user_id, visible) VALUES ("Neka lista", 7,true);

INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url ) VALUES (true, "November Rain", "Guns N' Roses - November Rain","https://www.youtube.com/watch?v=8SbUC-UaAxE", 7,5, "https://www.youtube.com/embed/8SbUC-UaAxE", "https://i.ytimg.com/vi/8SbUC-UaAxE/mqdefault.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url ) VALUES (true, "Don't Cry", "Guns N' Roses - Don't Cry","https://www.youtube.com/watch?v=zRIbf6JqkNc",1,1, "https://www.youtube.com/embed/zRIbf6JqkNc", "https://i.ytimg.com/vi/zRIbf6JqkNc/mqdefault.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url ) VALUES (true, "Crazy", "Aerosmith - Crazy","https://www.youtube.com/watch?v=NMNgbISmF4I",3,3, "https://www.youtube.com/embed/NMNgbISmF4I", "https://i.ytimg.com/vi/NMNgbISmF4I/mqdefault.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url ) VALUES (true, "Always", "Bon Jovi - Always","https://www.youtube.com/watch?v=9BMwcO6_hyA",4,4, "https://www.youtube.com/embed/9BMwcO6_hyA", "https://i.ytimg.com/vi/9BMwcO6_hyA/mqdefault.jpg");

INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES (false, "We Don’t Talk", "We Don't Talk Anymore","https://www.youtube.com/watch?v=3AtDnEC4zak",1,1, "https://www.youtube.com/embed/3AtDnEC4zak", "https://i.ytimg.com/vi/3AtDnEC4zak/mqdefault.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES (false, "Treat", "Treat You Better","https://www.youtube.com/watch?v=lY2yjAdbvdQ",1,1, "https://www.youtube.com/embed/lY2yjAdbvdQ", "https://i.ytimg.com/vi/lY2yjAdbvdQ/mqdefault.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES (true, "Description of Stitches", "Stitches","https://www.youtube.com/watch?v=VbfpW0pbvaU",1,1, "https://www.youtube.com/embed/VbfpW0pbvaU", "https://i.ytimg.com/vi/VbfpW0pbvaU/mqdefault.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES (true, "A Short Film Created by Justin Bieber, Parris Goebel and Scott Scooter Braun", "Love Yourself ","https://www.youtube.com/watch?v=oyEuk8j8imI",1,1, "https://www.youtube.com/embed/oyEuk8j8imI", "https://i.ytimg.com/vi/nfs8NYg7yQM/mqdefault.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES (true, "Music video by Jonas Blue performing Mama.", "Mama ft. William Singe","https://www.youtube.com/watch?v=qPTfXwPf_HM",1,2, "https://www.youtube.com/embed/qPTfXwPf_HM", "https://i.ytimg.com/vi/qPTfXwPf_HM/mqdefault.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES (true, "David Guetta feat Justin Bieber - 2U", "David Guetta ft Justin Bieber - 2U (The Victoria’s Secret Angels Lip Sync","https://www.youtube.com/watch?v=RqcjBLMaWCg",1,2, "https://www.youtube.com/embed/RqcjBLMaWCg", "https://i.ytimg.com/vi/RqcjBLMaWCg/mqdefault.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES (true, "DJ Khaled - I'm the One ft. Justin Bieber, Quavo, Chance the Rapper, Lil Wayne", " I'm the One","https://www.youtube.com/watch?v=weeI1G46q0o",1,2, "https://www.youtube.com/embed/weeI1G46q0o", "https://i.ytimg.com/vi/weeI1G46q0o/mqdefault.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES (true, "Jason Derulo - Swalla (feat. Nicki Minaj & Ty Dolla $ign)", "Swalla","https://www.youtube.com/watch?v=NGLxoKOvzu4",1,2, "https://www.youtube.com/embed/NGLxoKOvzu4", "https://i.ytimg.com/vi/NGLxoKOvzu4/mqdefault.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES (true, "Shakira - Chantaje (Official video) ft. Maluma", "Chantaje","https://www.youtube.com/watch?v=6Mgqbai3fKo",2,4, "https://www.youtube.com/embed/6Mgqbai3fKo", "https://i.ytimg.com/vi/6Mgqbai3fKo/mqdefault.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES (true, "Shakira - Me Enamoré (Official Video)", "Me Enamoré","https://www.youtube.com/watch?v=sPTn0QEhxds",2,4, "https://www.youtube.com/embed/sPTn0QEhxds", "https://i.ytimg.com/vi/sPTn0QEhxds/mqdefault.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES (true, "Luis Fonsi - Despacito ft. Daddy Yankee", "Despacito","https://www.youtube.com/watch?v=kJQP7kiw5Fk",2,3, "https://www.youtube.com/embed/kJQP7kiw5Fk", "https://i.ytimg.com/vi/kJQP7kiw5Fk/mqdefault.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES (true, "Enrique Iglesias - SUBEME LA RADIO (Official Video) ft. Descemer Bueno, Zion & Lennox", "SUBEME LA RADIO","https://www.youtube.com/watch?v=9sg-A-eS6Ig",2,3, "https://www.youtube.com/embed/9sg-A-eS6Ig", "https://i.ytimg.com/vi/9sg-A-eS6Ig/mqdefault.jpg");

INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES ( false, "Jack Ma's Life Advice Will Change Your Life", "Jack Ma's Life Advice","https://www.youtube.com/watch?v=lYGGpc2mMno",3,3, "https://www.youtube.com/embed/lYGGpc2mMno", "https://i.ytimg.com/vi/lYGGpc2mMno/mqdefault.jpg");

INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES ( false, "Crasdasazy", "asdas","https://vimeo.com/228724581",1,1, "https://player.vimeo.com/video/228724581", "https://i.vimeocdn.com/video/648849028_640.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES ( false, "Alwasdasdays", "asdasd","http://www.dailymotion.com/video/x5wzr9a",1,1, "http://www.dailymotion.com/embed/video/x5wzr9a",  "http://s1.dmcdn.net/lrP1N/x240-pbT.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES ( false, "Craasdasdzy", "asdasdas","http://www.dailymotion.com/video/x5wypz4",1,1, "http://www.dailymotion.com/embed/video/x5wypz4", "http://s2.dmcdn.net/lq5Lu/x240-QQd.jpg");
INSERT INTO video (visible, description, title, video_url, user_id, video_list_id, video_player_url, video_image_url) VALUES ( false, "Alwasdasays", "Basdasd","http://www.dailymotion.com/video/x5psnvz",1, 1,"http://www.dailymotion.com/embed/video/x5psnvz", "http://s1.dmcdn.net/kKEpQ/x240-9uy.jpg");

INSERT INTO COMMENT (content,creation_date,user_id,video_id) VALUES("Komentar 01, za neki video", NOW(), 2,1);
INSERT INTO COMMENT (content,creation_date,user_id,video_id) VALUES("Komentar 02, za neki video", NOW(), 3,1);
INSERT INTO COMMENT (content,creation_date,user_id,video_id) VALUES("Komentar 03, za neki video", NOW(), 4,1);
INSERT INTO COMMENT (content,creation_date,user_id,video_id) VALUES("Komentar 03, za neki video", NOW(), 4,2);
INSERT INTO COMMENT (content,creation_date,user_id,video_id) VALUES("Komentar 03, za neki video", NOW(), 4,2);

INSERT INTO video_tag (name) VALUES ("test_tag");
INSERT INTO video_tag (name) VALUES ("test_tag_2");

INSERT INTO video_tag (name) VALUES ("cool");
INSERT INTO video_tag (name) VALUES ("super");

INSERT INTO video_tag (name) VALUES ("nice");
INSERT INTO video_tag (name) VALUES ("OP");

INSERT INTO video_tag (name) VALUES ("House");
INSERT INTO video_tag (name) VALUES ("Rock");

INSERT INTO video_tag (name) VALUES ("RNB");
INSERT INTO video_tag (name) VALUES ("Narodnjak");

INSERT INTO video_tag (name) VALUES ("Zabavno");
INSERT INTO video_tag (name) VALUES ("Opako");

INSERT INTO video_tag (name) VALUES ("lepoCvece");
INSERT INTO video_tag (name) VALUES ("MP3");

INSERT INTO video_video_tag (video_id, video_tag_id) VALUES (1,1);
INSERT INTO video_video_tag (video_id, video_tag_id) VALUES (1,2);

INSERT INTO video_video_tag (video_id, video_tag_id) VALUES (2,3);
INSERT INTO video_video_tag (video_id, video_tag_id) VALUES (2,4);

INSERT INTO video_video_tag (video_id, video_tag_id) VALUES (5,4);
INSERT INTO video_video_tag (video_id, video_tag_id) VALUES (5,5);










