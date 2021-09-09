INSERT INTO gateway (serial_number, ipv4, name) VALUES('g_test_1', '334.23.23.232', 'test_1 name');
INSERT INTO gateway (serial_number, ipv4, name) VALUES('g_test_2', '134.123.123.132', 'test_2 name');
INSERT INTO gateway (serial_number, ipv4, name) VALUES('g_test_3', '234.223.223.232', 'test_3 name');

INSERT INTO device (uid, vendor, gateway_id, creation_date, status) VALUES('-1000', 'vendor_name_test1', 'g_test_2', CURRENT_TIMESTAMP, 'OFFLINE');
INSERT INTO device (uid, vendor, gateway_id, creation_date, status) VALUES('-1001', 'vendor_name_test2', 'g_test_2', CURRENT_TIMESTAMP, 'OFFLINE');
INSERT INTO device (uid, vendor, gateway_id, creation_date, status) VALUES('-1002', 'vendor_name_test3', 'g_test_2', CURRENT_TIMESTAMP, 'OFFLINE');
INSERT INTO device (uid, vendor, gateway_id, creation_date, status) VALUES('-1003', 'vendor_name_test4', 'g_test_2', CURRENT_TIMESTAMP, 'OFFLINE');
INSERT INTO device (uid, vendor, gateway_id, creation_date, status) VALUES('-1004', 'vendor_name_test5', 'g_test_2', CURRENT_TIMESTAMP, 'OFFLINE');
INSERT INTO device (uid, vendor, gateway_id, creation_date, status) VALUES('-1005', 'vendor_name_test6', 'g_test_2', CURRENT_TIMESTAMP, 'OFFLINE');
INSERT INTO device (uid, vendor, gateway_id, creation_date, status) VALUES('-1006', 'vendor_name_test7', 'g_test_2', CURRENT_TIMESTAMP, 'OFFLINE');
INSERT INTO device (uid, vendor, gateway_id, creation_date, status) VALUES('-1007', 'vendor_name_test8', 'g_test_2', CURRENT_TIMESTAMP, 'OFFLINE');
INSERT INTO device (uid, vendor, gateway_id, creation_date, status) VALUES('-1008', 'vendor_name_test9', 'g_test_2', CURRENT_TIMESTAMP, 'OFFLINE');
INSERT INTO device (uid, vendor, gateway_id, creation_date, status) VALUES('-1009', 'vendor_name_test0', 'g_test_2', CURRENT_TIMESTAMP, 'OFFLINE');

INSERT INTO device (uid, vendor, gateway_id, creation_date, status) VALUES('-1011', 'vendor_name_test02', 'g_test_1', CURRENT_TIMESTAMP, 'OFFLINE');
