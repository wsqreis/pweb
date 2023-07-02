INSERT INTO tb_carts (ID) VALUES (10);
INSERT INTO tb_carts (ID) VALUES (11);
INSERT INTO tb_users (ID, NAME, LOGIN, PASSWORD, ROLE, CART_ID) VALUES (10, 'Admin', 'admin', '$2a$10$uwQ8Ij.P8/Dz97lIbZrCD.rifW65YuvzyAbf3FkiLU6M2zz3Ho7GG', 'admin', 10);
INSERT INTO tb_users (ID, NAME, LOGIN, PASSWORD, ROLE, CART_ID) VALUES (11, 'User', 'user', '$2a$10$uwQ8Ij.P8/Dz97lIbZrCD.rifW65YuvzyAbf3FkiLU6M2zz3Ho7GG', 'user', 11);
INSERT INTO tb_categories (ID,NAME) VALUES (10,'hamburguer');
INSERT INTO tb_products (ID,NAME,PRICE,INGREDIENTS,category_id) VALUES (10,'hamburguer',20,'hamburguer,carne, queijo',10);
INSERT INTO tb_offers (ID,NAME,PRICE,DETAILS) VALUES (10,'combo 1',20,'hamburguer + refri');