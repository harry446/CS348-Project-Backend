UPDATE lots SET like_num = like_num+1 WHERE lid = 4 AND lid IN 
(SELECT lid FROM Bookings WHERE uid = 20 AND status = True AND end_time < CURRENT_TIMESTAMP AND liked = False);
UPDATE bookings SET liked = True WHERE uid = 20 AND lid = 4 AND status = True AND end_time < CURRENT_TIMESTAMP;
-- just for testing purposes, not included in feature query
SELECT * FROM lots WHERE lid = 4;
SELECT * FROM bookings WHERE uid=20 AND lid=4;
