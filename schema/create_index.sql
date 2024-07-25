CREATE INDEX BookingUidLidSidIndex ON bookings(uid, lid, sid);
CREATE INDEX BookingUidLidHistoryIndex ON bookings(uid, lid, status, end_time, liked);
CREATE INDEX UserUsernamePasswordIndex ON users(username, password);
CREATE INDEX BookingTimeStatIndex ON bookings(end_time, start_time, status);
CREATE INDEX LotIDNameLikeIndex ON lots(lid, lot_name, like_num);
CREATE INDEX UserAccessIndex ON users(uid, is_accessible);
CREATE INDEX SpotIDTypeIndex ON spots(lid, sid, parking_type);
CREATE INDEX PermitUIDExpiryIndex ON permit_holders(uid, expiry_date);
