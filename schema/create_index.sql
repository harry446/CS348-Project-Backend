CREATE INDEX BookingUidLidSidIndex ON bookings(uid, lid, sid);
CREATE INDEX BookingUidLidHistoryIndex ON bookings(uid, lid, status, end_time, liked);
CREATE INDEX UserUsernamePasswordIndex ON users(username, password);
