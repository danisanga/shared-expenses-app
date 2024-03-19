/* Custom schema */
CREATE SCHEMA IF NOT EXISTS my_schema;
SET search_path TO my_schema;

/* Tables */
CREATE TABLE my_schema.parties
(
    id          uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    created_time  TIMESTAMP    NOT NULL
);
