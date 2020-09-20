insert into USER_TABLE (email, username, roles, password) VALUES('ja@garwan.sk', 'ja', 'ADMIN','1');
insert into USER_TABLE (email, username, roles, password) VALUES('ty@garwan.sk', 'ty', 'ADMIN','1');
insert into USER_TABLE (email, username, roles, password) VALUES('on@garwan.sk', 'on', 'A','1');
insert into USER_TABLE (email, username, roles, password) VALUES('ona@garwan.sk', 'ona', 'A','1');
insert into USER_TABLE (email, username, roles, password) VALUES('ono@garwan.sk', 'ono', 'A','1');

insert into product_table (product_id, name, price, description) 
VALUES (1, 'kavovar', 1145.00, 'Super tuper tool for making coffee');

insert into product_table (product_id, name, price, description) 
VALUES (2, 'termoska', 50.00, 'Super tuper tool for making coffee');

insert into product_table (product_id, name, price, description) 
VALUES (3, 'monitor', 550.00, 'I want this staff!!');

insert into product_table (product_id, name, price, description) 
VALUES (4, 'keybaord', 22.00, 'Querty or not Querty');

insert into product_table (product_id, name, price, description) 
VALUES (5, 'mouse', 28.00, 'Caught me if you can');

insert into product_table (product_id, name, price, description) 
VALUES (6, 'knife', 18.00, 'I can slice almost everything');

insert into product_table (product_id, name, price, description) 
VALUES (7, 'BFG', 180000.00, 'How does it appear here???');

insert into order_table (order_id, userr, total_price, created) VALUES(1, 'ja', 50.00, '2019-02-23 20:02:21.550');
insert into order_table (order_id, userr, total_price, created) VALUES(2, 'ja',126.00, '2020-09-23 18:02:21.550');

insert into order_item (order_item_id, order_id, product_id, count, price) VALUES (1, 1, 1, 2, 10.8);
insert into order_item (order_item_id, order_id, product_id, count, price) VALUES (2, 1, 2, 2, 10.8);