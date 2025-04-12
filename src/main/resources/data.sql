--  Customers
INSERT INTO customers (full_name, email) VALUES ('Mohammed Lyazidi', 'mlyazidi@example.com');
INSERT INTO customers (full_name, email) VALUES ('Sara El Amrani', 'sara@example.com');
INSERT INTO customers (full_name, email) VALUES ('Youssef Bennani', 'youssef@example.com');

-- Orders
INSERT INTO orders (order_date, customer_id) VALUES (CURRENT_TIMESTAMP, 1); -- order 1
INSERT INTO orders (order_date, customer_id) VALUES (CURRENT_TIMESTAMP, 1); -- order 2
INSERT INTO orders (order_date, customer_id) VALUES (CURRENT_TIMESTAMP, 2); -- order 3
INSERT INTO orders (order_date, customer_id) VALUES (CURRENT_TIMESTAMP, 3); -- order 4

-- Order Products
INSERT INTO order_products (product_name, price, order_id) VALUES ('Casque Audio', 59.99, 1);
INSERT INTO order_products (product_name, price, order_id) VALUES ('Clavier Mécanique', 89.99, 1);
INSERT INTO order_products (product_name, price, order_id) VALUES ('Souris Gamer', 39.99, 2);
INSERT INTO order_products (product_name, price, order_id) VALUES ('Écran 27"', 219.99, 3);
INSERT INTO order_products (product_name, price, order_id) VALUES ('Webcam HD', 49.99, 3);
INSERT INTO order_products (product_name, price, order_id) VALUES ('Microphone USB', 79.99, 4);

-- Refund Requests
INSERT INTO refund_requests (reason, image_url, status, order_product_id)
VALUES ('Produit défectueux', 'http://example.com/proof1.jpg', 'PENDING', 1);

INSERT INTO refund_requests (reason, image_url, status, order_product_id)
VALUES ('Ne correspond pas à la description', 'http://example.com/proof2.jpg', 'APPROVED', 5);
