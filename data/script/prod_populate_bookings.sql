INSERT INTO bookings (bid, uid, lid, sid, create_time, start_time, end_time, price, status, liked)
VALUES 
(1, 11, 4, 1, '2024-06-24 17:35:00', '2024-06-25 15:30:00', '2024-06-25 19:00:00', 0, 1, 1),
(2, 11, 4, 5, '2024-06-24 18:35:40', '2024-06-25 15:30:00', '2024-06-27 16:30:00', 0, 1, 1),
(3, 11, 4, 6, '2024-06-24 18:35:57', '2024-06-25 15:30:00', '2024-06-25 16:30:00', 0, 1, 1);

UPDATE users SET booking_num = 3 WHERE uid = 11;
UPDATE lots SET like_num = like_num+1 WHERE lid = 4;

INSERT INTO bookings (bid, uid, lid, sid, create_time, start_time, end_time, price, status, liked)
VALUES 
(4, 20, 4, 2, '2024-06-24 17:35:00', '2024-06-25 15:30:00', '2024-06-25 19:00:00', 0, 1, 0),
(5, 20, 4, 3, '2024-06-24 18:35:40', '2024-06-25 15:30:00', '2024-06-25 16:30:00', 0, 0, 0),
(6, 20, 4, 3, '2024-06-24 19:35:00', '2024-06-25 15:30:00', '2024-06-25 18:00:00', 0, 1, 0);

UPDATE users SET booking_num = 3 WHERE uid = 20;


INSERT INTO bookings (bid, uid, lid, sid, create_time, start_time, end_time, price, status, liked)
VALUES 
(7, 24, 4, 4, '2024-06-24 17:35:00', '2024-06-25 15:30:00', '2024-06-25 19:00:00', 0, 1, 0);
UPDATE users SET booking_num = 3 WHERE uid = 24;


INSERT INTO bookings (bid, uid, lid, sid, create_time, start_time, end_time, price, status, liked)
VALUES 
(8, 6, 4, 7, '2024-06-24 17:55:00', '2024-06-29 15:30:00', '2024-06-29 19:00:00', 0, 1, 0),
(9, 6, 4, 8, '2024-06-24 17:58:00', '2024-06-25 15:30:00', '2024-06-25 19:00:00', 5.5, 1, 0);
UPDATE users SET booking_num = 3 WHERE uid = 6;

INSERT INTO bookings (bid, uid, lid, sid, create_time, start_time, end_time, price, status, liked)
VALUES 
(10, 22, 4, 9, '2024-06-24 17:35:00', '2024-06-25 15:30:00', '2024-06-25 19:00:00', 5.5, 1, 1),
(10, 22, 4, 10, '2024-06-24 17:55:00', '2024-06-25 15:30:00', '2024-06-25 19:00:00', 5.5, 1, 1);
UPDATE users SET booking_num = 2 WHERE uid = 22;
UPDATE lots SET like_num = like_num+1 WHERE lid = 4;
