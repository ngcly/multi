DROP TABLE IF exists user_info;
CREATE TABLE user_info
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    username          VARCHAR(32) UNIQUE NOT NULL,
    password          VARCHAR(68) NOT NULL,
    first_name        VARCHAR(32),
    last_name         VARCHAR(32),
    birthday          DATE,
    email             VARCHAR(64),
    phone             VARCHAR(32),
    address           VARCHAR(255),
    created_by        VARCHAR(32),
    updated_by        VARCHAR(32),
    created_at        DATETIME,
    updated_at        DATETIME,
    version           INT
);