/* Custom schema */
CREATE SCHEMA IF NOT EXISTS my_schema;
SET
search_path TO my_schema;

/* Tables */
CREATE TABLE my_schema.parties
(
    id            uuid PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    creation_time TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE my_schema.friends
(
    id            uuid PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    email          VARCHAR(255) NOT NULL,
    party_id      uuid         NOT NULL,
    creation_time TIMESTAMP DEFAULT current_timestamp,

    FOREIGN KEY (party_id) REFERENCES parties (id)
);

CREATE TABLE my_schema.expenses
(
    id            uuid PRIMARY KEY,
    party_id      uuid           NOT NULL,
    friend_id     uuid           NOT NULL,
    quantity      DECIMAL(10, 8) NOT NULL,
    description   VARCHAR(255)   NOT NULL,
    creation_time TIMESTAMP DEFAULT current_timestamp,

    FOREIGN KEY (friend_id) REFERENCES friends (id),
    FOREIGN KEY (party_id) REFERENCES parties (id)

);
