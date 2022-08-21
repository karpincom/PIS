DROP TABLE questions;
DROP TABLE packs;
DROP TABLE users;

CREATE TABLE users(
    id BIGSERIAL NOT NULL PRIMARY KEY UNIQUE,
    username TEXT NOT NULL,
--     email TEXT NOT NULL,
--     name TEXT,
--     surname TEXT,
    password_hash TEXT
);

CREATE TABLE packs(
    id BIGSERIAL NOT NULL PRIMARY KEY UNIQUE,
    name TEXT NOT NULL,
    created_on TIMESTAMP NOT NULL,
    last_update TIMESTAMP NOT NULL,
    user_id BIGINT NOT NULL,

    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE questions(
    id BIGSERIAL NOT NULL PRIMARY KEY UNIQUE,
    question TEXT NOT NULL,
    pack_id BIGINT NOT NULL,
    pack_order BIGINT NOT NULL,

    FOREIGN KEY(pack_id) REFERENCES packs(id)
);

-- CREATE TABLE game_statuses(
--   id BIGSERIAL NOT NULL PRIMARY KEY UNIQUE,
--   name TEXT NOT NULL UNIQUE
-- );
--
-- CREATE TABLE game(
--     id BIGSERIAL NOT NULL PRIMARY KEY UNIQUE,
--
--     start TIMESTAMP NOT NULL,
--     status TEXT NOT NULL,
--     pack_id BIGINT NOT NULL,
--     user_id BIGINT NOT NULL,
--     status_id BIGINT NOT NULL,
--
--     FOREIGN KEY(pack_id) REFERENCES packs(id)
--     FOREIGN KEY(user_id) REFERENCES users(id),
--     FOREIGN KEY(status_id) REFERENCES game_statuses(id)
-- );

CREATE OR REPLACE PROCEDURE user_insert_procedure (
    _username TEXT,
--     _email TEXT,
--     _name TEXT,
--     _surname TEXT,
    _password_hash TEXT
)
AS $$
BEGIN
    INSERT INTO users (username, password_hash)
    VALUES (_username, _password_hash);
-- INSERT INTO users (username, email, name, surname, password_hash)
-- VALUES (_username, _email, _name, _surname, _password_hash);
END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE PROCEDURE pack_insert_procedure (
    _name TEXT,
    _user_id BIGINT
)
AS $$
BEGIN
INSERT INTO packs (name, created_on, last_update, user_id)
VALUES (_name, now(), now(), _user_id);
END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE PROCEDURE question_insert_procedure (
    _question TEXT,
    _pack_id BIGINT,
    _pack_order BIGINT
)
AS $$
BEGIN
INSERT INTO questions (question, pack_id, pack_order)
VALUES (_question, _pack_id, _pack_order);
END;
$$
LANGUAGE 'plpgsql';















