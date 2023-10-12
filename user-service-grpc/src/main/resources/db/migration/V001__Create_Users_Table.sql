CREATE SEQUENCE "sequence_users"
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1;

CREATE TABLE ${flyway:defaultSchema}.users (
    "id" INT8 NOT NULL,
    "email" VARCHAR(64) NOT NULL,
    PRIMARY KEY ("id")
);