INSERT INTO customer (user_name, email_adress)
VALUES ('Anna Andersson', 'anna.a@hotmail.com');
INSERT INTO customer (user_name, email_adress)
VALUES ('Karl Karlsson', 'kalle@gmail.com');
INSERT INTO customer (user_name, email_adress)
VALUES ('Per Persson', 'pelle.p@yahoo.se');
INSERT INTO customer (user_name, email_adress)
VALUES ('Erika Eriksson', 'e.e@spray.se');
INSERT INTO customer (user_name, email_adress)
VALUES ('Sven Svensson', 'svenne@outlook.com');


INSERT INTO media_interactions (media_id, like_status, times_listened_to, customer_id)
VALUES (1, 'like', 4, 1),
       (4, '', 2, 1),
       (7, 'dislike', 7, 1),
       (10, 'like', 5, 1),
       (14, 'like', 10, 1),
       (16, '', 8, 1),
       (11, '', 3, 1),
       (9, '', 1, 1),
       (1, 'like', 20, 2),
       (2, 'dislike', 1, 2),
       (4, '', 3, 2),
       (1, 'dislike', 3, 3),
       (4, 'like', 17, 3);