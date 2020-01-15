DELETE FROM user_roles;
DELETE FROM menus;
DELETE FROM users;
DELETE FROM RESTORANS;
DELETE FROM VOTES;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, vote_id)
VALUES ('User', 'user@yandex.ru', '$2a$10$8E9T8IrsfJllvBHDhsFR4Ovv6I6NsjKqbNss7LvTziVZwAvMKpuSW', null),
       ('Admin', 'admin@gmail.com', '$2a$10$DIweDRnnDQ43mtxsgbwfq.WjhOpYRZ39BKGBc8zso5mpEhATV.jZe', 100113),
       ('Cook', 'cook@gmail.com', '$2a$10$Ep78NcvgFIBd4HbnRQrkb.L0lYfeist9Q.erk.lGdgCQWnKiJNyZi', null);

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_USER', 100001);

INSERT INTO restorans (name, sum_vote)
VALUES ('Пельменная', 0),
       ('Суши-сити', 1);

INSERT INTO menus (name, price, registered, restoran_id)
VALUES ('Манка', 10.5, '2015-05-30 10:00:00', 100004),
       ('Гречка', 200, '2015-05-30 13:00:00', 100004),
       ('Ужин', 500, '2015-05-30 20:00:00', 100004),
       ('Завтрак', 500, '2015-05-31 10:00:00', 100004),
       ('Обед', 1000, '2015-05-31 13:00:00', 100003),
       ('Ужин', 510, '2015-05-31 20:00:00', 100004),
       ('Админ ланч', 510, '2015-06-01 14:00:00', 100003),
       ('Админ ужин', 1500, '2015-06-01 21:00:00', 100003);

INSERT INTO votes (ID, REGISTERED, USER_ID, RESTORAN_ID)
VALUES (100113, '2015-05-30 10:00:00', 100001, 100004);
