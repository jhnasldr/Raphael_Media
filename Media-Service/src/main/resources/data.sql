INSERT INTO artist (artist_name)
VALUES
    ('Leonardo DiCaprio'),
    ('Quentin Tarantino'),
    ('Hans Zimmer'),
    ('David Attenborough'),
    ('Emma Watson'),
    ('Billie Eilish'),
    ('Finneas O\Connell'),
    ('Adele'),
    ('Ed Sheeran'),
    ('Beyoncé'),
    ('Joe Rogan'),
    ('Ricky Gervais'),
    ('Dave Chappelle'),
    ('Lex Fridman'),
    ('Foo Fighters'),
    ('Queen'),
    ('The Rolling Stones'),
    ('Malcolm Gladwell'),
    ('Sarah Koenig'),
    ('Tim Ferriss'),
    ('Bruce Willis'),
    ('Keanu Reeves');

INSERT INTO album (album_name, artist_id)
VALUES
    ('When We All Fall Asleep, Where Do We Go?', 6),
    ('25', 8),
    ('Divide', 9),
    ('Lemonade', 10),
    ('Happier Than Ever', 6),
    ('Greatest Hits', 15),
    ('A Night at the Opera', 16),
    ('Sticky Fingers', 17);

INSERT INTO genre (genre_name)
VALUES
    ('Drama'),
    ('Action'),
    ('Pop'),
    ('Rock'),
    ('Documentary'),
    ('Comedy');

INSERT INTO media (title, url, release_date, media_type)
VALUES
    -- Drama
    ('Inception', 'https://raphaelstudio/video/inception', '2010-07-16', 'video'),
    ('Harry Potter and the Philosophers Stone', 'https://raphaelstudio/video//harry-potter', '2001-11-16', 'video'),
    ('The Revenant', 'https://raphaelstudio/video/the-revenant', '2015-12-25', 'video'),

    -- Action
    ('Once Upon a Time in Hollywood', 'https://raphaelstudio/video/hollywood', '2019-07-26', 'video'),
    ('Die Hard', 'https://raphaelstudio/video/die-hard', '1988-07-15', 'video'),
    ('John Wick', 'https://raphaelstudio/video/john-wick', '2014-10-24', 'video'),

    -- Pop
    ('Bad Guy', 'https://raphaelstudio/music/bad-guy', '2019-03-29', 'music'),
    ('Someone Like You', 'https://raphaelstudio/music/someone-like-you', '2011-01-24', 'music'),
    ('Shape of You', 'https://raphaelstudio/music/shape-of-you', '2017-01-06', 'music'),

    -- Rock
    ('Everlong', 'https://raphaelstudio/music/everlong', '1997-08-18', 'music'),
    ('Bohemian Rhapsody', 'https://raphaelstudio/music/bohemian-rhapsody', '1975-10-31', 'music'),
    ('Brown Sugar', 'https://raphaelstudio/music/brown-sugar', '1971-04-16', 'music'),

    -- Documentary
    ('Planet Earth II', 'https://raphaelstudio/video/planet-earth-ii', '2016-11-06', 'video'),
    ('The Joe Rogan Experience', 'https://raphaelstudio/podcast/jre', '2021-03-01', 'podcast'),
    ('Serial', 'https://raphaelstudio/podcast/serial', '2014-10-03', 'podcast'),

    -- Comedy
    ('The Office', 'https://raphaelstudio/podcast/the-office', '2005-03-24', 'video'),
    ('Ricky Gervais Show', 'https://raphaelstudio/podcast/ricky-gervais', '2005-11-25', 'podcast'),
    ('The Tim Ferriss Show', 'https://raphaelstudio/podcast/tim-ferriss', '2016-07-05', 'podcast');

INSERT INTO video(id)
VALUES (1), (2), (3), (4), (5), (6), (13), (16);

INSERT INTO music(id)
VALUES (7), (8), (9), (10), (11), (12);

INSERT INTO podcast(id)
VALUES (14), (15), (17), (18);

INSERT INTO media_genres (media_id, genre_id)
VALUES
    -- Drama
    (1, 1), -- Inception - Drama
    (2, 1), -- Harry Potter - Drama
    (3, 1), -- The Revenant - Drama

    -- Action
    (4, 2), -- Once Upon a Time in Hollywood - Action
    (5, 2), -- Die Hard - Action
    (6, 2), -- John Wick - Action

    -- Pop
    (7, 3), -- Bad Guy - Pop
    (8, 3), -- Someone Like You - Pop
    (9, 3), -- Shape of You - Pop

    -- Rock
    (10, 4), -- Everlong - Rock
    (11, 4), -- Bohemian Rhapsody - Rock
    (12, 4), -- Brown Sugar - Rock

    -- Documentary
    (13, 5), -- Planet Earth II - Documentary
    (14, 5), -- Joe Rogan - Documentary
    (15, 5), -- Serial - Documentary

    -- Comedy
    (16, 6), -- The Office - Comedy
    (17, 6), -- Ricky Gervais Show - Comedy
    (18, 6); -- Tim Ferriss Show - Comedy

INSERT INTO media_artists (media_id, artist_id)
VALUES
    -- Drama
    (1, 1), -- Inception - Leonardo DiCaprio
    (2, 5), -- Harry Potter - Emma Watson
    (3, 1), -- The Revenant - Leonardo DiCaprio

    -- Action
    (4, 2), -- Once Upon a Time in Hollywood - Quentin Tarantino
    (4, 1), -- Once Upon a Time in Hollywood - Leonardo Dicaprio
    (5, 21), -- Die Hard - Hans Zimmer (kompositör)
    (6, 22), -- John Wick - Hans Zimmer (kompositör)

    -- Pop
    (7, 6), -- Bad Guy - Billie Eilish
    (8, 8), -- Someone Like You - Adele
    (9, 9), -- Shape of You - Ed Sheeran

    -- Rock
    (10, 15), -- Everlong - Foo Fighters
    (11, 16), -- Bohemian Rhapsody - Queen
    (12, 17), -- Brown Sugar - The Rolling Stones

    -- Documentary
    (13, 4), -- Planet Earth II - David Attenborough
    (14, 11), -- Joe Rogan - Joe Rogan
    (15, 19), -- Serial - Sarah Koenig

    -- Comedy
    (16, 12), -- The Office - Ricky Gervais
    (17, 12), -- Ricky Gervais Show - Ricky Gervais
    (18, 20); -- Tim Ferriss Show - Tim Ferriss

INSERT INTO media_albums (media_id, album_id)
VALUES
    (7, 1), -- Bad Guy - When We All Fall Asleep, Where Do We Go?
    (8, 2), -- Someone Like You - 25
    (9, 3), -- Shape of You - Divide
    (10, 6), -- Everlong - Greatest Hits (Foo Fighters)
    (11, 7), -- Bohemian Rhapsody - A Night at the Opera (Queen)
    (12, 8); -- Brown Sugar - Sticky Fingers (The Rolling Stones)