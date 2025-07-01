INSERT INTO user_info(username, password, first_name, last_name, birthday, email, phone, address, created_by, updated_by,
                 created_at, updated_at, version)
VALUES ('niudaye', '{bcrypt}$2a$10$OL4Dwel48yLEKBqKN8cUEeNcPxCZ1fSTNHtNqaE6xrzTySIarYaT2', 'Jerry', 'Gun', '1993-01-02', '234234@qq.com', '34534522', 'New york', 'Lin', 'Lin',
        parsedatetime('2022-08-01 14:23:13', 'yyyy-MM-dd HH:mm:ss'),
        parsedatetime('2022-08-01 14:23:24', 'yyyy-MM-dd HH:mm:ss'), 0),
       ('jexika', '{bcrypt}$2a$10$OL4Dwel48yLEKBqKN8cUEeNcPxCZ1fSTNHtNqaE6xrzTySIarYaT2', 'Lisa', 'Wan', '2000-01-11', '3423@qq.com', '3452345234', 'None', 'Admin', 'Admin',
        parsedatetime('2022-08-02 15:23:11', 'yyyy-MM-dd HH:mm:ss'),
        parsedatetime('2022-08-02 15:23:35', 'yyyy-MM-dd HH:mm:ss'), 0),
       ('lucy', '{bcrypt}$2a$10$OL4Dwel48yLEKBqKN8cUEeNcPxCZ1fSTNHtNqaE6xrzTySIarYaT2', 'Tom', 'Green', '1993-11-23', '4523534@qq.com', '34234234', 'Man street', 'Admin', 'Admin',
        parsedatetime('2022-08-03 16:23:41', 'yyyy-MM-dd HH:mm:ss'),
        parsedatetime('2022-08-03 16:23:53', 'yyyy-MM-dd HH:mm:ss'), 0),
       ('annajie', '{bcrypt}$2a$10$OL4Dwel48yLEKBqKN8cUEeNcPxCZ1fSTNHtNqaE6xrzTySIarYaT2', 'Anna', 'Min', '1995-09-11', '24523423@qq.com', '345352342', 'Hello city', 'Admin',
        'Admin',
        parsedatetime('2022-08-03 16:23:41', 'yyyy-MM-dd HH:mm:ss'),
        parsedatetime('2022-08-03 16:23:53', 'yyyy-MM-dd HH:mm:ss'), 0);

