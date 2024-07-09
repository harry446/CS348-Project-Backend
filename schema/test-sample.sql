-- just for testing purpose, revert the changes feature 1 test brings to avoid duplicate insertino
DELETE FROM bookings WHERE bid = 7;

-- feature 1: make a booking
INSERT INTO bookings (bid, uid, lid, sid, create_time, start_time, end_time, price, status, liked) VALUES (7, 4, 1, 3, "2024-06-14 22:49:21", "2024-06-20 15:30:00", "2024-06-20 17:00:00", 0, 1,0);

UPDATE users SET booking_num = booking_num + 1 WHERE uid = 4;

-- feature 2: cancel a booking
-- assuming user with uid=4 made a valid booking with bid 7
UPDATE bookings SET status = 0 WHERE uid = 4 AND bid = 7;
UPDATE users SET booking_num = booking_num -  1 WHERE uid = 4;

-- feature 3: list booking history
SELECT l.area, l.address, l.lot_name, s.parking_type,
b.create_time, b.start_time, b.end_time, b.price, 
CASE WHEN (b.status=1 AND b.end_time < "2024-06-16 22:49:21") THEN "expired" ELSE
(CASE WHEN b.status=1 THEN "booked" ELSE "cancelled" END) END AS status, b.liked
FROM bookings b,  lots l, spots s 
WHERE b.uid = 00004 AND b.lid=l.lid AND b.lid=s.lid AND b.sid=s.sid;

-- feature 4: sorting search result/booking history
WITH temp AS ( 
SELECT l.area, l.address, l.lot_name, s.parking_type,
b.create_time, b.start_time, b.end_time, b.price, 
CASE WHEN (b.status=1 AND b.end_time < "2024-06-14 22:49:21") THEN "expired" ELSE
(CASE WHEN b.status=1 THEN "booked" ELSE "cancelled" END) END AS status, b.liked 
FROM bookings b,  lots l, spots s 
WHERE b.uid = 00004 AND b.lid=l.lid AND b.lid=s.lid AND b.sid=s.sid
)
SELECT *
FROM temp
ORDER BY temp.price DESC;

-- feature 5: liking a parking lot
UPDATE lots SET like_num = like_num+1 WHERE lid = 2 AND lid IN 
(SELECT lid FROM Bookings WHERE uid = 3 AND status = True AND end_time < CURRENT_TIMESTAMP AND liked = False);
UPDATE bookings SET liked = True WHERE uid = 3 AND lid = 2 AND status = True AND end_time < CURRENT_TIMESTAMP;

-- feature 6: user login
SELECT COUNT(*) FROM users WHERE username ='student1' AND password = 'iamstudent';
