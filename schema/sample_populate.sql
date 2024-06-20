INSERT INTO users (`uid`,`username`,`password`,`email`,`phone`,`identity`,`booking_num`,`is_accessible`) 
VALUES (00001, "admin01", "iamadmin", "admin@gmail.com", "1231231234", "admin", 0, 0);
INSERT INTO users (`uid`,`username`,`password`,`email`,`phone`,`identity`,`booking_num`,`is_accessible`) 
VALUES (00002, "adfjhk", "123123", "adjgkhj@gmail.com", "1234567890", "student", 0, 0);
INSERT INTO users (`uid`,`username`,`password`,`email`,`phone`,`identity`,`booking_num`,`is_accessible`) 
VALUES (00003, "djkahgkla", "123123", "sueueb@gmail.com", "1234567890", "staff", 0, 1);
INSERT INTO users (`uid`,`username`,`password`,`email`,`phone`,`identity`,`booking_num`,`is_accessible`) 
VALUES (00004, "dhgbdb", "password", "yfufhf293@gmail.com", "2342383434", "visitor", 0, 0);
INSERT INTO users (`uid`,`username`,`password`,`email`,`phone`,`identity`,`booking_num`,`is_accessible`) 
VALUES (00005, "randomvisitor", "password111", "32409djf@outlook.com", "5196853958", "visitor", 0, 1);
INSERT INTO users (`uid`,`username`,`password`,`email`,`phone`,`identity`,`booking_num`,`is_accessible`) 
VALUES (00006, "student1", "iamstudent", "student@uwaterloo.ca", "2020202020", "student", 0, 0);
INSERT INTO users (`uid`,`username`,`password`,`email`,`phone`,`identity`,`booking_num`,`is_accessible`) 
VALUES (00007, "student2", "anotherstud", "astudent@uwaterloo.ca", "3030303030", "student", 0, 1);
INSERT INTO users (`uid`,`username`,`password`,`email`,`phone`,`identity`,`booking_num`,`is_accessible`) 
VALUES (00008, "staff2", "iamstaff", "staff@uwaterloo.ca", "4040404040", "staff", 0, 0);

-- valid student, not accessible
INSERT INTO permit_holders (`uid`,`type`,`expiry_date`) 
VALUES (00002, "student", '2024-08-30');
-- valid staff and accessible
INSERT INTO permit_holders (`uid`,`type`,`expiry_date`) 
VALUES (00003, "staff", '2026-01-30');
-- expired permit, treat as visitor
INSERT INTO permit_holders (`uid`,`type`,`expiry_date`) 
VALUES (00006, "student", '2023-01-30');
-- expired permit but accessible
INSERT INTO permit_holders (`uid`,`type`,`expiry_date`) 
VALUES (00007, "student", '2023-01-30'); 



INSERT INTO lots (lid, area, address, lot_name, capacity, like_num) 
VALUES (1, 'UW', 'XXX', 'DC', 30, 4);
INSERT INTO lots (lid, area, address, lot_name, capacity, like_num) 
VALUES (2, 'UW', 'XXX', 'OPT', 30, 3);
INSERT INTO lots (lid, area, address, lot_name, capacity, like_num) 
VALUES (3, 'UW', 'XXX', 'DWE', 20, 2);
INSERT INTO lots (lid, area, address, lot_name, capacity, like_num) 
VALUES (4, 'UW', 'XXX', 'SCH', 10, 0);

INSERT INTO spots (lid, sid, parking_type, latitude, longitude, max_stay, price) 
VALUES (1,  1, 'permit', 43.47373, -80.5404, NULL, 0);
INSERT INTO spots (lid, sid, parking_type, latitude, longitude, max_stay, price) 
VALUES (1,  2, 'pay', 43.47373, -80.5404, 3.0, 5);
INSERT INTO spots (lid, sid, parking_type, latitude, longitude, max_stay, price) 
VALUES (1,  3, 'free', 43.47373, -80.5404, 1.5, 0);
INSERT INTO spots (lid, sid, parking_type, latitude, longitude, max_stay, price) 
VALUES (2,  1, 'accessible', 43.47672, -80.5471, 1.5, 0);
INSERT INTO spots (lid, sid, parking_type, latitude, longitude, max_stay, price) 
VALUES (3,  1, 'free', 43.46963001, -80.53989, 1.5, 0);
INSERT INTO spots (lid, sid, parking_type, latitude, longitude, max_stay, price) 
VALUES (4,  1, 'pay', 43.46836, -80.53989, 1.5, 3);

-- full booking for one person
INSERT INTO bookings (bid, uid, lid, sid, create_time, start_time, end_time, price, status)
VALUES (00001, 00003, 1, 1, '2024-06-14 12:32:21', '2024-06-20 15:30:00', '2024-06-20 17:00:00', 0, 1);
INSERT INTO bookings (bid, uid, lid, sid, create_time, start_time, end_time, price, status)
VALUES (00002, 00003, 1, 1, '2024-06-14 12:32:57', '2024-06-18 9:00:00', '2024-06-20 14:00:00', 0, 1);
INSERT INTO bookings (bid, uid, lid, sid, create_time, start_time, end_time, price, status)
VALUES (00003, 00003, 2, 1, '2024-06-14 12:35:00', '2024-06-15 15:30:00', '2024-06-15 16:00:00', 0, 1);
-- update corresponding user's booking_num to match insertion
UPDATE users SET booking_num = 3 WHERE uid = 00003;

-- one cancelled booking, two valid for one person
INSERT INTO bookings (bid, uid, lid, sid, create_time, start_time, end_time, price, status)
VALUES (00004, 00004, 1, 2, '2024-06-14 17:35:00', '2024-06-15 15:30:00', '2024-06-15 18:30:00', 15, 1);
INSERT INTO bookings (bid, uid, lid, sid, create_time, start_time, end_time, price, status)
VALUES (00005, 00004, 3, 1, '2024-06-14 18:35:40', '2024-06-15 15:30:00', '2024-06-15 16:30:00', 0, 1);
INSERT INTO bookings (bid, uid, lid, sid, create_time, start_time, end_time, price, status)
VALUES (00006, 00004, 4, 1, '2024-06-14 18:35:57', '2024-06-15 15:30:00', '2024-06-15 16:30:00', 3, 0);
UPDATE users SET booking_num = 2 WHERE uid = 00004;

