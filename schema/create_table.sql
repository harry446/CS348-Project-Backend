CREATE TABLE `users` (
    `uid` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(16) NOT NULL,
    `password` VARCHAR(16) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `phone` CHAR(10),
    `identity` CHAR(10) NOT NULL,
    `booking_num` INT NOT NULL DEFAULT 0,
    `is_accessible` BOOL NOT NULL,
    PRIMARY KEY (`uid`),
    CONSTRAINT `c_bookings` CHECK (`booking_num` <= 3 AND `booking_num` >= 0),
    CONSTRAINT `c_id_type` CHECK (`identity` IN ("admin", "staff", "visitor", "student"))
);

CREATE TABLE `permit_holders` (
    `uid` INT NOT NULL,
    `type` CHAR(20) NOT NULL,
    `expiry_date` DATE NOT NULL,
    PRIMARY KEY (`uid`),
    FOREIGN KEY (`uid`) REFERENCES `users`(`uid`),
    CONSTRAINT `c_type` CHECK (`type` IN ("staff", "student"))
);

CREATE TABLE `lots` (
    `lid` INT NOT NULL AUTO_INCREMENT,
    `area` CHAR(2) NOT NULL,
    `address` VARCHAR(64) NOT NULL,
    `lot_name` VARCHAR(50) NOT NULL,
    `capacity` INT NOT NULL,
    `like_num` INT, 
    PRIMARY KEY (`lid`),
    CONSTRAINT `c_area` CHECK (`area` IN ("uw", "cw"))
);

CREATE TABLE `spots` (
    `lid` INT NOT NULL,
    `sid` INT NOT NULL,
    `parking_type` CHAR(20) NOT NULL,
    `latitude` VARCHAR(50) NOT NULL,
    `longitude` VARCHAR(50) NOT NULL,
    `max_stay` INT,
    `price` FLOAT NOT NULL,
    PRIMARY KEY (`lid`, `sid`),
    FOREIGN KEY (`lid`) REFERENCES `lots`(`lid`),
    CONSTRAINT `c_park_type` CHECK (`parking_type` IN ("permit", "pay", "accessible", "free"))
);

CREATE TABLE `bookings` (
    `bid` INT NOT NULL AUTO_INCREMENT,
    `uid` INT NOT NULL,
    `lid` INT NOT NULL,
    `sid` INT NOT NULL,
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `start_time` DATETIME NOT NULL,
    `end_time` DATETIME NOT NULL,
    `price` FLOAT,
    `status` BOOL NOT NULL,
    PRIMARY KEY (`bid`),
    FOREIGN KEY (`uid`) REFERENCES `users`(`uid`),
    FOREIGN KEY (`lid`,`sid`) REFERENCES `spots`(`lid`,`sid`)
);
