
CREATE TABLE hibernate_sequence
(
    next_val BIGINT
);

INSERT INTO hibernate_sequence (next_val)
VALUES (1);

CREATE TABLE LotInAu
(
    id          BIGINT        NOT NULL,
    name        VARCHAR(255)  NOT NULL,
    description VARCHAR(2048),
    start_time  DATETIME      NOT NULL,
    end_time    DATETIME,
    time_step   INT,
    initial_rate BIGINT        NOT NULL,
    final_rate   BIGINT,
    filename    VARCHAR(255),
    status      VARCHAR(255),
    User_id     BIGINT,
    PRIMARY KEY (id)
);

create table Price
(
    id      BIGINT   NOT NULL,
    rate     BIGINT   NOT NULL,
    date    DATETIME NOT NULL,
    LotInAu_id  BIGINT   NOT NULL,
    User_id BIGINT   NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE User
(
    id              BIGINT       NOT NULL,
    activation_code VARCHAR(255),
    active          BIT,
    balance         BIGINT,
    email           VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    Username        VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE User_role
(
    User_id bigint NOT NULL,
    roles   VARCHAR(255)
);

ALTER TABLE LotInAu
    ADD CONSTRAINT LotInAu_User_fk
        FOREIGN KEY (User_id) REFERENCES User (id);

ALTER TABLE Price
    ADD CONSTRAINT Price_LotInAu_fk
        FOREIGN KEY (LotInAu_id) REFERENCES LotInAu (id);

ALTER TABLE Price
    ADD CONSTRAINT Price_User_fk
        FOREIGN KEY (User_id) REFERENCES User (id);

ALTER TABLE User_role
    ADD CONSTRAINT User_role_User_fk
        FOREIGN KEY (User_id) REFERENCES User (id);

INSERT INTO User (id, activation_code, active, balance, email, password, Username)
VALUES (0, null, true, 0, 'auctionambey@gmail.com', '$2y$08$Gu0OlNkXr8L5cjcvVBAl2uUgmqnbl.we3bdtvctdBrFLcn1ik3Sru',
        'root');

INSERT INTO User_role (User_id, roles)
VALUES (0, 'User'),
       (0, 'ADMIN');
