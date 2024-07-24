-- for testing purpose
UPDATE users SET booking_num = 0 WHERE uid = 6;

INSERT INTO bookings (uid, lid, sid, create_time, start_time, end_time, price, status) VALUES (6, 1, 29, '2024-06-14 22:49:21', '2024-06-20 15:30:00', '2024-06-20 17:00:00', 0, 1);
UPDATE users SET booking_num = booking_num + 1 WHERE uid = 6;
-- just for testing purposes, not included in feature query
SELECT * FROM bookings WHERE uid=6 AND lid = 6 AND sid = 1;
SELECT * FROM users WHERE uid = 6;
