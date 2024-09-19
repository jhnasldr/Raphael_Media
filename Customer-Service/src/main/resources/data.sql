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
VALUES (1, 'like', 3, 1), --(Drama)
       (4, '', 2, 1),       --(Action)
       (7, '', 1, 1), --(Pop, Bad Guy)
       --(5, 'dislike', 1, 1),
       (1, 'like', 20, 2),
       (2, 'dislike', 1, 2),
       (4, '', 3, 2),
       (1, 'dislike', 3, 3),
       (4, 'like', 17, 3);