-- Country table, as specified in the hands-on document.
-- MySQL:  create schema ormlearn; then run this against it.
DROP TABLE IF EXISTS country;

CREATE TABLE country (
    co_code VARCHAR(2) PRIMARY KEY,
    co_name VARCHAR(50)
);
