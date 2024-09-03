-- Lägg till artister
INSERT INTO artist (artist_name)
VALUES ('Queen'),
       ('Michael Jackson'),
       ('Dave Brubeck'),
       ('James Cameron'),
       ('Jeff Orlowski'),
       ('David Attenborough'),
       ('Freddie Mercury'),
       ('Paul McCartney'),
       ('John Lennon'),
       ('Miles Davis Quintet'),
       ('Hans Zimmer & Lisa Gerrard');

-- Lägg till album
INSERT INTO album (album_name, artist_id)
VALUES ('A Night at the Opera', 1),
       ('Thriller', 2),
       ('Time Out', 3),
       ('Abbey Road', 8),
       ('Sketches of Spain', 9),
       ('Gladiator Soundtrack', 10);

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
       ('The Elegant Universe', 'https://raphaelstudio/podcast/elegant-universe', '2003-01-01', 'podcast'),
       ('Gladiator', 'https://raphaelstudio/video/gladiator', '2000-05-05', 'video'),
       ('The Last Dance', 'https://raphaelstudio/video/the-last-dance', '2020-04-19', 'video'),
       ('Blue Planet II', 'https://raphaelstudio/video/blue-planet-ii', '2017-10-29', 'video'),
       ('Under Pressure - Queen & David Bowie', 'https://raphaelstudio/music/under-pressure', '1981-10-26', 'music'),
       ('Billie Jean - Michael Jackson', 'https://raphaelstudio/music/billie-jean', '1983-01-02', 'music'),
       ('Freddie Freeloader - Miles Davis', 'https://raphaelstudio/music/freddie-freeloader', '1959-03-02', 'music'),
       ('Guns, Germs, and Steel', 'https://raphaelstudio/podcast/guns-germs-steel', '2005-07-10', 'podcast'),
       ('The Jinx: The Life and Deaths of Robert Durst', 'https://raphaelstudio/podcast/the-jinx', '2015-02-08', 'podcast'),
       ('Nova: Einstein’s Quantum Riddle', 'https://raphaelstudio/video/einsteins-quantum-riddle', '2019-01-09', 'video');

-- Lägg till videor med id
INSERT INTO video(id)
VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9), (10), (26), (27), (28), (34);

-- Lägg till musik med id
INSERT INTO music(id)
VALUES (11), (12), (13), (14), (15), (16), (17), (18), (19), (29), (30), (31);

-- Lägg till podcasts med id
INSERT INTO podcast(id)
VALUES (20), (21), (22), (23), (24), (25), (32), (33);

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
       (25, 9),  -- The Elegant Universe (Science)
       (26, 1),  -- Gladiator (Drama)
       (27, 2),  -- The Last Dance (Documentary)
       (28, 3),  -- Blue Planet II (Nature/Science)
       (29, 4),  -- Under Pressure (Rock)
       (30, 5),  -- Billie Jean (Pop)
       (31, 6),  -- Freddie Freeloader (Jazz)
       (32, 7),  -- Guns, Germs, and Steel (History)
       (33, 8),  -- The Jinx (True Crime)
       (34, 9);  -- Nova: Einstein’s Quantum Riddle (Science)


-- Koppla artister till media
INSERT INTO media_artists (media_id, artist_id)
VALUES
    -- Videor
    (1, 4),
    (2, 5),
    (3, 6),
    (4, 10),
    (5, 5),
    (6, 6),
    (7, 10),
    (26, 10),
    (27, 5),
    (28, 6),
    (34, 10),
    -- Musik
    (11, 1),
    (11, 7),
    (12, 2),
    (13, 3),
    (14, 8),
    (14, 9),
    (15, 1),
    (15, 7),
    (16, 2),
    (17, 9),
    (29, 1),
    (29, 7),
    (30, 2),
    (31, 9);

-- Koppla album till musik
INSERT INTO media_albums (media_id, album_id)
VALUES
    (11, 1),
    (12, 2),
    (13, 3),
    (14, 4),
    (15, 1),
    (16, 2),
    (17, 3),
    (29, 1),
    (30, 2),
    (31, 3);