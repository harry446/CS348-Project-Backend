LOAD DATA LOCAL INFILE '/path/to/your/user.txt'
INTO TABLE users
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
(uid,username,password,email,phone,identity,booking_num,is_accessible)
SET phone = NULLIF(phone, 'NULL');

LOAD DATA LOCAL INFILE '/path/to/your/p_holders.txt'
INTO TABLE permit_holders
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
(uid,type,expiry_date);

LOAD DATA LOCAL INFILE '/path/to/your/lots.txt'
INTO TABLE lots
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
(lid,area,address,lot_name,capacity,like_num);

LOAD DATA LOCAL INFILE '/path/to/your/allspots.txt'
INTO TABLE spots
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
(lid,sid,parking_type,latitude,longitude,max_stay,price)
SET max_stay = NULLIF(max_stay, 'NULL');

LOAD DATA LOCAL INFILE '/path/to/your/allspots.txt'
INTO TABLE bookings
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
(bid, uid, lid, sid, create_time, start_time, end_time, price, status, liked)
SET price = NULLIF(price, 'NULL');
