CREATE TABLE subscription
(
    LotInAu_id        BIGINT NOT NULL REFERENCES LotInAu(id),
    subscriber_id BIGINT NOT NULL REFERENCES User(id),
    PRIMARY KEY (LotInAu_id, subscriber_id)
);
