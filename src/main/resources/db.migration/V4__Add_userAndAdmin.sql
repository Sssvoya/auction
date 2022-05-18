INSERT INTO User (id, activation_code, active, balance, email, password, Username)
VALUES (1, null, true, 0, 'defaultUser@gmail.com', '$2y$08$Gu0OlNkXr8L5cjcvVBAl2uUgmqnbl.we3bdtvctdBrFLcn1ik3Sru',
        'User');

INSERT INTO User_role (User_id, roles)
VALUES (1, 'User');

INSERT INTO User (id, activation_code, active, balance, email, password, Username)
VALUES (2, null, true, 0, 'defaultAdmin@gmail.com', '$2y$08$Gu0OlNkXr8L5cjcvVBAl2uUgmqnbl.we3bdtvctdBrFLcn1ik3Sru',
        'admin');

INSERT INTO User_role (User_id, roles)
VALUES (2, 'ADMIN');
