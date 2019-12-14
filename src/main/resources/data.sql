DELETE FROM user_roles;
DELETE FROM menus;
DELETE FROM users;
DELETE FROM RESTORANS;
DELETE FROM VOTES;
-- ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin'),
  ('Cook', 'cook@gmail.com', 'cook');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO restorans (ID,name)
VALUES (100004,'Пельменная'),
       (100003,'Суши-сити');

INSERT INTO menus (name, price,registered,restoran_id)
VALUES ('Манка',10.5,'2015-05-30 10:00:00', 100004),
       ('Гречка',200,'2015-05-30 13:00:00', 100004),
       ('Ужин', 500,'2015-05-30 20:00:00',  100004),
       ('Завтрак', 500,'2015-05-31 10:00:00', 100004),
       ('Обед', 1000,'2015-05-31 13:00:00',  100004),
       ('Ужин', 510,'2015-05-31 20:00:00',  100004),
       ( 'Админ ланч', 510,'2015-06-01 14:00:00', 100003),
       ('Админ ужин', 1500,'2015-06-01 21:00:00',  100003);

INSERT INTO votes (REGISTERED,USER_ID,RESTORAN_ID)
VALUES ('2015-05-30 10:00:00',100001,100004);
