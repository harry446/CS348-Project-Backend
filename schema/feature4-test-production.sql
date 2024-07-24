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
