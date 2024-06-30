drop table if exists statistics cascade;

CREATE TABLE IF NOT EXISTS statistics
(
    id        INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    app       VARCHAR(64) NOT NULL,
    uri       VARCHAR(64) NOT NULL,
    ip        VARCHAR(64) NOT NULL,
    timestamp TIMESTAMP   NOT NULL
);
