DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS votes CASCADE;
DROP TABLE IF EXISTS restorans CASCADE;
DROP TABLE IF EXISTS menus CASCADE;
-- DROP SEQUENCE IF EXISTS GLOBAL_SEQ CASCADE;

CREATE SEQUENCE global_seq START WITH 100000;
CREATE TABLE users
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name       VARCHAR(50)                       NOT NULL,
    email      VARCHAR(255)                      NOT NULL,
    password   VARCHAR(255)                      NOT NULL,
    registered TIMESTAMP           DEFAULT NOW() NOT NULL,
    enabled    BOOLEAN             DEFAULT TRUE  NOT NULL,
    vote_id    INTEGER             DEFAULT NULL
);
CREATE UNIQUE INDEX users_unique_name_password_idx
    ON USERS (name, password);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(50),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restorans
(
    id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name     VARCHAR(50)                   NOT NULL,
    sum_vote INTEGER             DEFAULT 0 NOT NULL
);
CREATE UNIQUE INDEX restorans_unique_name_idx
    ON restorans (name);

CREATE TABLE menus
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name        VARCHAR(50)                       NOT NULL,
    price       VARCHAR                           NOT NULL,
    enabled     BOOLEAN             DEFAULT TRUE  NOT NULL,
    registered  TIMESTAMP           DEFAULT NOW() NOT NULL,
    restoran_id INTEGER,
    FOREIGN KEY (restoran_id) REFERENCES RESTORANS (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    registered  TIMESTAMP           DEFAULT NOW() NOT NULL,
    user_id     INTEGER,
    restoran_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (restoran_id) REFERENCES RESTORANS (id) ON UPDATE CASCADE ON DELETE CASCADE
);
