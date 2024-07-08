LOAD DATA LOCAL INFILE '/path/to/your/user.txt'
INTO TABLE users
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
(uid,username,password,email,phone,identity,booking_num,is_accessible);

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
(lid,sid,parking_type,latitude,longitude,max_stay,price);
