/* Custom schema */
CREATE SCHEMA IF NOT EXISTS my_schema;
SET
search_path TO my_schema;

/* Tables */
CREATE TABLE my_schema.parties
(
    id           uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    creation_time TIMESTAMP    NOT NULL
);

-- CREATE TABLE my_schema.friends
-- (
--     id           uuid DEFAULT gen_random_uuid() PRIMARY KEY,
--     name         VARCHAR(255) NOT NULL,
--     creation_time TIMESTAMP    NOT NULL
-- );

CREATE TABLE my_schema.expenses
(
    id           uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    party_id     uuid           NOT NULL,
--     friend_id    uuid           NOT NULL REFERENCES friends (id),
    quantity     DECIMAL(10, 8) NOT NULL,
    description  VARCHAR(255)   NOT NULL,
    creation_time TIMESTAMP      NOT NULL,

    FOREIGN KEY (party_id) REFERENCES parties(id)

);
