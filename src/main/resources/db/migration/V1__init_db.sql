CREATE TABLE user_status
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(45),
    buy_bonus  DOUBLE PRECISION,
    sell_bonus DOUBLE PRECISION
);
INSERT INTO user_status (name, buy_bonus, sell_bonus)
VALUES ('bronze', 0.01, 0.01);
INSERT INTO user_status (name, buy_bonus, sell_bonus)
VALUES ('silver', 0.03, 0.02);
INSERT INTO user_status (name, buy_bonus, sell_bonus)
VALUES ('gold', 0.05, 0.05);

CREATE TABLE user
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(45),
    surname        VARCHAR(45),
    login          VARCHAR(45),
    password       VARCHAR(45),
    pesel          VARCHAR(45),
    role           VARCHAR(45),
    user_status_id INT,
    FOREIGN KEY (user_status_id) REFERENCES user_status (id)
);
INSERT INTO user (name, surname, login, password, pesel, role, user_status_id)
VALUES ('Admin', 'Admin', 'admin', 'admin', null, '0', null);



CREATE TABLE account
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    currency VARCHAR(45),
    amount   DOUBLE PRECISION,
    user_id  INT,
    FOREIGN KEY (user_id) REFERENCES user (id)

);

CREATE TABLE transfer
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    amount        DOUBLE,
    transfer_date DATETIME,
    user_id       INT,
    currency      VARCHAR(45),
    FOREIGN KEY (user_id) REFERENCES user (id)
);
