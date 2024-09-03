/*-- Lägg till artister
INSERT INTO artist (artist_name)
VALUES ('Queen'),
       ('Michael Jackson'),
       ('Dave Brubeck'),
       ('James Cameron'),  -- Director of Titanic
       ('Jeff Orlowski'),  -- Director of The Social Dilemma
       ('David Attenborough'),  -- Narrator of Planet Earth
       ('Freddie Mercury'),          -- Ytterligare artist för "Bohemian Rhapsody"
       ('Paul McCartney'),           -- Exempel för flera artister för The Beatles låtar
       ('John Lennon'),              -- Samma som ovan
       ('Miles Davis Quintet'),      -- För "So What" exempelvis
       ('Hans Zimmer & Lisa Gerrard'); -- För Gladiator Soundtrack

-- Lägg till album
INSERT INTO album (album_name, artist_id)
VALUES ('A Night at the Opera', 1),  -- Album for "Bohemian Rhapsody"
       ('Thriller', 2),              -- Album for "Thriller"
       ('Time Out', 3),              -- Album for "Take Five"
       ('Abbey Road', 8),            -- För The Beatles album
       ('Sketches of Spain', 9),     -- Ytterligare album för Miles Davis
       ('Gladiator Soundtrack', 10); -- För Gladiator filmens soundtrack

-- Lägg till genrer
INSERT INTO genre (genre_name)
VALUES ('Drama'),
       ('Documentary'),
       ('Nature/Science'),
       ('Rock'),
       ('Pop'),
       ('Jazz'),
       ('History'),
       ('True Crime'),
       ('Science');

-- Lägg till videor
INSERT INTO media (title, url, release_date, media_type)
VALUES ('Titanic', 'https://raphaelstudio/video/titanic', '1997-12-19','video'),
       ('The Social Dilemma', 'https://raphaelstudio/video/social-dilemma', '2020-01-26','video'),
       ('Planet Earth', 'https://raphaelstudio/video/planet-earth', '2006-03-05','video'),
       ('The Godfather', 'https://raphaelstudio/video/the-godfather', '1972-03-24','video'),
       ('Forrest Gump', 'https://raphaelstudio/video/forrest-gump', '1994-07-06','video'),
       ('Blackfish', 'https://raphaelstudio/video/blackfish', '2013-07-01','video'),
       ('13th', 'https://raphaelstudio/video/13th', '2016-10-07','video'),
       ('World War II Documentary', 'https://raphaelstudio/video/ww2-documentary', '2001-05-15','video'),
       ('The Rise and Fall of the Roman Empire', 'https://raphaelstudio/video/roman-empire', '2018-09-10','video');
insert into video(id) values (1);
insert into video(id) values (2);
insert into video(id) values (3);
insert into video(id) values (11);
insert into video(id) values (12);
insert into video(id) values (13);
insert into video(id) values (14);
insert into video(id) values (20);
insert into video(id) values (21);

-- Lägg till musik
INSERT INTO media (title, url, release_date, media_type)
VALUES ('Bohemian Rhapsody - Queen', 'https://raphaelstudio/music/bohemian-rhapsody', '1975-10-31', 'music'),
       ('Thriller - Michael Jackson', 'https://raphaelstudio/music/thriller', '1982-11-30', 'music'),
       ('Take Five - Dave Brubeck', 'https://raphaelstudio/music/take-five', '1959-09-21', 'music'),
       ('Come Together - The Beatles', 'https://raphaelstudio/music/come-together', '1969-09-26', 'music'),
       ('Stairway to Heaven - Led Zeppelin', 'https://raphaelstudio/music/stairway-to-heaven', '1971-11-08', 'music'),
       ('Like a Prayer - Madonna', 'https://raphaelstudio/music/like-a-prayer', '1989-03-03', 'music'),
       ('Bad Romance - Lady Gaga', 'https://raphaelstudio/music/bad-romance', '2009-10-26', 'music')
       ('Take the A Train - Duke Ellington', 'https://raphaelstudio/music/take-the-a-train', '1941-09-15', 'music'),
       ('So What - Miles Davis', 'https://raphaelstudio/music/so-what', '1959-08-17', 'music'),

insert into music(id) values (4);
insert into music(id) values (5);
insert into music(id) values (6);
insert into music(id) values (7);
insert into music(id) values (15);
insert into music(id) values (16);
insert into music(id) values (17);
insert into music(id) values (18);
insert into music(id) values (19);

-- Lägg till podcasts
INSERT INTO media (title, url, release_date, media_type)
VALUES ('The History of Ancient Rome', 'https://raphaelstudio/podcast/history-rome', '2024-01-01', 'podcast'),
       ('True Crime Stories', 'https://raphaelstudio/podcast/true-crime-stories', '2019-03-10', 'podcast'),
       ('The Science of Sleep', 'https://raphaelstudio/podcast/science-of-sleep', '2021-05-15', 'podcast'),
       ('Making a Murderer', 'https://raphaelstudio/podcast/making-a-murderer', '2015-12-18', 'podcast'),
       ('Serial', 'https://raphaelstudio/podcast/serial', '2014-10-03', 'podcast');
insert into podcast(id) values (8);
insert into podcast(id) values (9);
insert into podcast(id) values (10);
insert into podcast(id) values (22);
insert into podcast(id) values (23);

-- Koppla genrer till media
INSERT INTO media_genres (media_id, genre_id)
VALUES (1, 1),  -- Titanic (Drama)
       (2, 2),  -- The Social Dilemma (Documentary)
       (3, 3),  -- Planet Earth (Nature/Science)
       (4, 4),  -- Bohemian Rhapsody (Rock)
       (5, 5),  -- Thriller (Pop)
       (6, 6),  -- Take Five (Jazz)
       (7, 4),  -- Come Together (Rock)
       (8, 7),  -- The History of Ancient Rome (History)
       (9, 8),  -- True Crime Stories (True Crime)
       (10, 9),  -- The Science of Sleep (Science)
       (11, 1),  -- The Godfather (Drama)
       (12, 1),  -- Forrest Gump (Drama)
       (13, 2),  -- Blackfish (Documentary)
       (14, 2),  -- 13th (Documentary)
       (15, 4),  -- Stairway to Heaven (Rock)
       (16, 5),  -- Like a Prayer (Pop)
       (17, 5),  -- Bad Romance (Pop)
       (18, 6),  -- Take the A Train (Jazz)
       (19, 6),  -- So What (Jazz)
       (20, 7),  -- World War II Documentary (History)
       (21, 7),  -- The Rise and Fall of the Roman Empire (History)
       (22, 8),  -- Making a Murderer (True Crime)
       (23, 8),  -- Serial (True Crime)


-- Koppla artister till videor
INSERT INTO media_artists (media_id, artist_id)
VALUES (1, 4),  -- James Cameron för Titanic
       (2, 5),  -- Jeff Orlowski för The Social Dilemma
       (3, 6);  -- David Attenborough för Planet Earth


-- Koppla artister till musik
INSERT INTO media_artists (media_id, artist_id)
VALUES (4, 1),  -- Queen för Bohemian Rhapsody
       (4, 7),  -- Freddie Mercury för Bohemian Rhapsody
       (5, 2),  -- Michael Jackson för Thriller
       (6, 3),  -- Dave Brubeck för Take Five
       (7, 8),  -- Paul McCartney för Come Together
       (7, 9);  -- John Lennon för Come Together

-- Koppla album till musik
INSERT INTO media_albums (media_id, album_id)
VALUES (4, 1),  -- "Bohemian Rhapsody" till albumet "A Night at the Opera"
       (5, 2),  -- "Thriller" till albumet "Thriller"
       (6, 3),  -- "Take Five" till albumet "Time Out"
       (7, 4);  -- "Come Together" till albumet "Abbey Road"
*/

-- Lägg till artister
INSERT INTO artist (artist_name)
VALUES ('Queen'),
       ('Michael Jackson'),
       ('Dave Brubeck'),
       ('James Cameron'),  -- Director of Titanic
       ('Jeff Orlowski'),  -- Director of The Social Dilemma
       ('David Attenborough'),  -- Narrator of Planet Earth
       ('Freddie Mercury'),  -- Ytterligare artist för "Bohemian Rhapsody"
       ('Paul McCartney'),  -- Exempel för flera artister för The Beatles låtar
       ('John Lennon'),  -- Samma som ovan
       ('Miles Davis Quintet'),  -- För "So What" exempelvis
       ('Hans Zimmer & Lisa Gerrard'); -- För Gladiator Soundtrack

-- Lägg till album
INSERT INTO album (album_name, artist_id)
VALUES ('A Night at the Opera', 1),  -- Album för "Bohemian Rhapsody"
       ('Thriller', 2),  -- Album för "Thriller"
       ('Time Out', 3),  -- Album för "Take Five"
       ('Abbey Road', 8),  -- För The Beatles album
       ('Sketches of Spain', 9),  -- Ytterligare album för Miles Davis
       ('Gladiator Soundtrack', 10);  -- För Gladiator filmens soundtrack

-- Lägg till genrer
INSERT INTO genre (genre_name)
VALUES ('Drama'),
       ('Documentary'),
       ('Nature/Science'),
       ('Rock'),
       ('Pop'),
       ('Jazz'),
       ('History'),
       ('True Crime'),
       ('Science');

-- Lägg till all media
INSERT INTO media (title, url, release_date, media_type)
VALUES ('Titanic', 'https://raphaelstudio/video/titanic', '1997-12-19','video'),
       ('The Social Dilemma', 'https://raphaelstudio/video/social-dilemma', '2020-01-26','video'),
       ('Planet Earth', 'https://raphaelstudio/video/planet-earth', '2006-03-05','video'),
       ('The Godfather', 'https://raphaelstudio/video/the-godfather', '1972-03-24','video'),
       ('Forrest Gump', 'https://raphaelstudio/video/forrest-gump', '1994-07-06','video'),
       ('Blackfish', 'https://raphaelstudio/video/blackfish', '2013-07-01','video'),
       ('13th', 'https://raphaelstudio/video/13th', '2016-10-07','video'),
       ('World War II Documentary', 'https://raphaelstudio/video/ww2-documentary', '2001-05-15','video'),
       ('The Rise and Fall of the Roman Empire', 'https://raphaelstudio/video/roman-empire', '2018-09-10','video'),
       ('Cosmos: A Spacetime Odyssey', 'https://raphaelstudio/video/cosmos', '2014-03-12','video'),
       ('Bohemian Rhapsody - Queen', 'https://raphaelstudio/music/bohemian-rhapsody', '1975-10-31', 'music'),
       ('Thriller - Michael Jackson', 'https://raphaelstudio/music/thriller', '1982-11-30', 'music'),
       ('Take Five - Dave Brubeck', 'https://raphaelstudio/music/take-five', '1959-09-21', 'music'),
       ('Come Together - The Beatles', 'https://raphaelstudio/music/come-together', '1969-09-26', 'music'),
       ('Stairway to Heaven - Led Zeppelin', 'https://raphaelstudio/music/stairway-to-heaven', '1971-11-08', 'music'),
       ('Like a Prayer - Madonna', 'https://raphaelstudio/music/like-a-prayer', '1989-03-03', 'music'),
       ('Bad Romance - Lady Gaga', 'https://raphaelstudio/music/bad-romance', '2009-10-26', 'music'),
       ('Take the A Train - Duke Ellington', 'https://raphaelstudio/music/take-the-a-train', '1941-09-15', 'music'),
       ('So What - Miles Davis', 'https://raphaelstudio/music/so-what', '1959-08-17', 'music'),
       ('The History of Ancient Rome', 'https://raphaelstudio/podcast/history-rome', '2024-01-01', 'podcast'),
       ('True Crime Stories', 'https://raphaelstudio/podcast/true-crime-stories', '2019-03-10', 'podcast'),
       ('The Science of Sleep', 'https://raphaelstudio/podcast/science-of-sleep', '2021-05-15', 'podcast'),
       ('Making a Murderer', 'https://raphaelstudio/podcast/making-a-murderer', '2015-12-18', 'podcast'),
       ('Serial', 'https://raphaelstudio/podcast/serial', '2014-10-03', 'podcast'),
       ('The Elegant Universe', 'https://raphaelstudio/podcast/elegant-universe', '2003-01-01', 'podcast');

-- Lägg till videor med id
INSERT INTO video(id)
VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9), (10);

-- Lägg till musik med id
INSERT INTO music(id)
VALUES (11), (12), (13), (14), (15), (16), (17), (18), (19);

-- Lägg till podcasts med id
INSERT INTO podcast(id)
VALUES (20), (21), (22), (23), (24), (25);

-- Koppla genrer till media
INSERT INTO media_genres (media_id, genre_id)
VALUES (1, 1),  -- Titanic (Drama)
       (2, 2),  -- The Social Dilemma (Documentary)
       (3, 3),  -- Planet Earth (Nature/Science)
       (4, 4),  -- Bohemian Rhapsody (Rock)
       (5, 5),  -- Thriller (Pop)
       (6, 6),  -- Take Five (Jazz)
       (7, 4),  -- Come Together (Rock)
       (8, 7),  -- The History of Ancient Rome (History)
       (9, 8),  -- True Crime Stories (True Crime)
       (10, 9),  -- The Science of Sleep (Science)
       (11, 1),  -- The Godfather (Drama)
       (12, 1),  -- Forrest Gump (Drama)
       (13, 2),  -- Blackfish (Documentary)
       (14, 2),  -- 13th (Documentary)
       (15, 4),  -- Stairway to Heaven (Rock)
       (16, 5),  -- Like a Prayer (Pop)
       (17, 5),  -- Bad Romance (Pop)
       (18, 6),  -- Take the A Train (Jazz)
       (19, 6),  -- So What (Jazz)
       (20, 7),  -- World War II Documentary (History)
       (21, 7),  -- The Rise and Fall of the Roman Empire (History)
       (22, 8),  -- Making a Murderer (True Crime)
       (23, 8),  -- Serial (True Crime)
       (24, 9),  -- Cosmos: A Spacetime Odyssey (Science)
       (25, 9);  -- The Elegant Universe (Science)

-- Koppla artister till videor
INSERT INTO media_artists (media_id, artist_id)
VALUES (1, 4),  -- James Cameron för Titanic
       (2, 5),  -- Jeff Orlowski för The Social Dilemma
       (3, 6);  -- David Attenborough för Planet Earth

-- Koppla artister till musik
INSERT INTO media_artists (media_id, artist_id)
VALUES (11, 1),  -- Queen för Bohemian Rhapsody
       (11, 7),  -- Freddie Mercury för Bohemian Rhapsody
       (12, 2),  -- Michael Jackson för Thriller
       (13, 3),  -- Dave Brubeck för Take Five
       (14, 8),  -- Paul McCartney för Come Together
       (14, 9);  -- John Lennon för Come Together

-- Koppla album till musik
INSERT INTO media_albums (media_id, album_id)
VALUES (11, 1),  -- "Bohemian Rhapsody" till albumet "A Night at the Opera"
       (12, 2),  -- "Thriller" till albumet "Thriller"
       (13, 3),  -- "Take Five" till albumet "Time Out"
       (14, 4);  -- "Come Together" till albumet "Abbey Road"