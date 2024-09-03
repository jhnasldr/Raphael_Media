INSERT INTO media (title, url, release_date, media_type)
VALUES ('Introduction to Java', 'https://rapharlstudio/video/java-intro', '2023-01-10','music');
insert into music(id) values (1);
/*
 INSERT INTO video (title, url, release_date)
VALUES ('Introduction to Java', 'https://rapharlstudio/video/java-intro', '2023-01-10'),
       ('Advanced Spring Boot', 'https://rapharlstudio/video/spring-boot-advanced', '2023-08-15'),
       ('Understanding SQL', 'https://rapharlstudio/video/sql-basics', '2023-07-20');
INSERT INTO music (title, url, release_date)
VALUES ('Java Music', 'https://rapharlstudio/music/java-intro', '2020-05-13'),
      ('Advanced Music', 'https://rapharlstudio/music/spring-boot-advanced', '2019-10-01'),
       ('Music of SQL', 'https://rapharlstudio/music/sql-basics', '2022-01-30');
INSERT INTO podcast (title, url, release_date)
VALUES ('Java podcast', 'https://rapharlstudio/podcast/java-intro', '2024-01-01'),
       ('Super Advanced podcast', 'https://rapharlstudio/podcast/spring-boot-advanced', '2012-03-10'),
       ('podcast of SQL', 'https://rapharlstudio/podcast/sql-basics', '1992-01-20');


INSERT INTO Artist (artist_name)
VALUES ('Justin Hall'),
       ('Trevor Page'),
       ('John Lennon');


--Todo lägga till genre till media



-- lägger till media till artister
INSERT INTO video_artist(artist_id, video_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);


INSERT INTO music_artists(artist_id, music_id)
VALUES (3, 1),
       (3, 2),
       (3, 3);

INSERT INTO podcast_artist(artist_id, podcast_id)
VALUES (2, 1),
       (2, 2),
       (2, 3);

INSERT INTO album (album_name, artist_id)
VALUES ('First java album', 1),
       ('Testing something new', 3),
       ('Programming podcast', 2);


--lägg till media till album
INSERT INTO video_albums(album_id, video_id)
VALUES (1,1),
       (1,2),
       (1,3);

INSERT INTO music_albums(album_id, music_id)
VALUES (2,1),
       (2,2),
       (2,3);

INSERT INTO podcast_albums(album_id, podcast_id)
VALUES (3,1),
       (3,2),
       (3,3);


-- todo
-- artist/artists
   
 */