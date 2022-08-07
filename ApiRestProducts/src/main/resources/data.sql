-- roles
INSERT INTO roles(id_role, name) VALUES (nextval('role_id_seq'), 'ADMIN');
INSERT INTO roles(id_role, name) VALUES (nextval('role_id_seq'), 'USER');

-- users

INSERT INTO users (id_user , username, password ) VALUES (nextval('user_id_seq'), 'Pepe_Admin', '1234' );
INSERT INTO users (id_user , username, password )VALUES (nextval('user_id_seq'), 'Juan_User', '1234' );

-- roles

-- INSERT INTO users_roles(1, 1);
-- INSERT INTO users_roles(2, 2);

--products

INSERT INTO products(id_product, created_at, description, item_code, price, status, user_creator)
VALUES (nextval('product_id_seq'), LOCALTIME(), 'Taladro', 'XTY', 29.30, 'ACTIVE', 1);
INSERT INTO products(id_product, created_at, description, item_code, price, status, user_creator)
VALUES (nextval('product_id_seq'), LOCALTIME(), 'Destornillador', 'PIP', 2.50, 'ACTIVE', 1);
