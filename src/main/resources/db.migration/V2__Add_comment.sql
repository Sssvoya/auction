
CREATE TABLE commentary
(
    id        BIGINT NOT NULL,
    text      VARCHAR(1024),
    date      DATETIME,
    author_id BIGINT NOT NULL ,
    LotInAu_id    BIGINT NOT NULL ,
    PRIMARY KEY (id)
);

ALTER TABLE commentary
    ADD CONSTRAINT commentary_User_fk
        FOREIGN KEY (author_id) REFERENCES User (id);

ALTER TABLE commentary
    ADD CONSTRAINT commentary_LotInAu_fk
        FOREIGN KEY (LotInAu_id) REFERENCES LotInAu (id);

