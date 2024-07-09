INSERT INTO bookings (uid, lid, sid, create_time, start_time, end_time, price, status) VALUES (6, 1, 29, '2024-06-14 22:49:21', '2024-06-20 15:30:00', '2024-06-20 17:00:00', 0, 1);
UPDATE users SET booking_num = booking_num + 1 WHERE uid = 6;

UPDATE bookings SET status = 0 WHERE bid = 2; 
UPDATE users SET booking_num = booking_num -  1 WHERE uid = 11;

SELECT l.area, l.address, l.lot_name, s.parking_type,
b.create_time, b.start_time, b.end_time, b.price, 
CASE WHEN (b.status=1 AND b.end_time < "2024-06-25 22:49:21") THEN "expired" ELSE
(CASE WHEN b.status=1 THEN "booked" ELSE "cancelled" END) END AS status, b.liked
FROM bookings b,  lots l, spots s 
WHERE b.uid = 11 AND b.lid=l.lid AND b.lid=s.lid AND b.sid=s.sid;

WITH temp AS ( 
SELECT l.area, l.address, l.lot_name, s.parking_type,
b.create_time, b.start_time, b.end_time, b.price, 
CASE WHEN (b.status=1 AND b.end_time < "2024-06-25 22:49:21") THEN "expired" ELSE
(CASE WHEN b.status=1 THEN "booked" ELSE "cancelled" END) END AS status, b.liked
FROM bookings b,  lots l, spots s 
WHERE b.uid = 11 AND b.lid=l.lid AND b.lid=s.lid AND b.sid=s.sid
)
SELECT *
FROM temp
ORDER BY temp.price DESC;

UPDATE lots SET like_num = like_num+1 WHERE lid = 4 AND lid IN 
(SELECT lid FROM Bookings WHERE uid = 20 AND status = True AND end_time < CURRENT_TIMESTAMP AND liked = False);
UPDATE bookings SET liked = True WHERE uid = 20 AND lid = 4 AND status = True AND end_time < CURRENT_TIMESTAMP;

SELECT COUNT(*) FROM users WHERE username = ‘bellrobert’ AND password = 'S&#dA15tkgMLnx#a';

