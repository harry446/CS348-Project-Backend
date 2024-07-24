UPDATE bookings SET status = 0 WHERE bid = 3962; 
UPDATE users SET booking_num = booking_num -  1 WHERE uid = 11;
-- just for testing purposes, not included in feature query
SELECT * FROM bookings WHERE bid = 3962;
SELECT * FROM users WHERE uid = 11;
