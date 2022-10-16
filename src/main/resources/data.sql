INSERT INTO player (id, email, nickname, password, enabled) VALUES 
(1, 'Test1@test.com', 'test1', 'azerty1', 1),
(2, 'Test2@test.com', 'test2', 'azerty2', 1);

INSERT INTO manager (id, name, reputation, chest, level, experience, player_id) VALUES 
(1,	'Théodebald',0,	200, 1, 0, 1),
(2,	'Enguéran',	3, 500, 1, 0, 1),
(3,	'Blancheflor', 0, 500, 1, 2, 2);

INSERT INTO customer (id, purse_of_gold, happiness, hunger, thirst, nausea_level, toilet, nausea_tolerance, 
alcohol_tolerance, gender, exp_given, alcohol_level ) VALUES
(1, 100, 100, 30, 20, 30, 20, 30, 20, 20, 20, 12),
(2, 100, 100, 30, 20, 30, 20, 30, 20, 20, 20, 12),
(3, 100, 100, 30, 20, 30, 20, 30, 20, 20, 20, 12),
(4, 100, 100, 30, 20, 30, 20, 30, 20, 20, 20, 12);

INSERT INTO category (id, name) VALUES
(1, 'Boissons'),
(2, 'Plats'),
(3,	'Entrées'),
(4, 'Desserts'),
(5, 'Autres');

INSERT INTO subcategory (id, name, category_id) VALUES
(1,	'Bières', 1),
(2,	'Vins',	1),
(3,	'Ragoûts', 2),
(4,	'Tartes', 2),
(5,	'Poissons, fruits de mer', 2),
(6,	'Viandes, volailles', 2),
(7,	'Fromages', 2),
(8,	'Soupes', 3),
(9,	'Fruits', 4),
(10, 'Patisseries',	4),
(11, 'Condiments, épices',	5),
(12, 'Légumes', 5),
(13, 'Champignons', 5),
(14, 'Pains', 2),
(15, 'Pates', 5);

INSERT INTO ingredient (id , name, level, buying_price, subcategory_id) VALUES
(1,	'Pâte',	3,	7,	15),
(2,	'Fruit', 1,	2,	1),
(3,	'Spaghetti Bolognaise',	2, 4, 1),
(4,	'Sikaru', 3, 6,	1),
(5,	'Vin Orge',	3, 9, 1),
(6,	'Pomme Verte', 1, 1, 9),
(7,	'Emmental',	4, 3, 7),
(8,	'Boulettes de Boeuf',1,	1, 6),
(10, 'Omelette aux Herbes',	1, 1,6);

INSERT INTO place (id, name, type, level, manager_id) VALUES
(1, 'Cuisine', 1, 1, 1),
(2, 'Bar1', 2, 1, 1),
(3, 'Bar2', 2, 2, 1),
(4, 'Cuisine', 1, 1, 2),
(5, 'Bar1', 2, 1, 2),
(6, 'Cuisine', 1, 1, 3),
(7, 'Bar1', 2, 1, 3);

INSERT INTO recipe (id, name, selling_price, level, consommation_time, preparation_time, peremption_date, 
exp_given , subcategory_id) VALUES
(1,	'Fruit', 3,	1, 10, 0, NULL, 0, 1),
(2,	'Cervoise',	5, 2, 10, 0, NULL, 0, 1),
(3,	'Sikaru', 7, 3, 10,	0, NULL, 0,	1),
(4,	'Vin Orge',	10,	3, 10,	0, NULL, 0,	1),
(5,	'Pomme', 2,	1, 13, 0, '2022-07-28', 0, 9),
(6,	'Tarte aux Pommes', 15,	3, 20, 12, '2022-07-28', 3, 10),
(7,	'Pain au fromage', 15, 4, 20, 10, '2022-07-28', 4, 14);

INSERT INTO table_rest (id, number_place, hygiene, pos_x, pos_y, place_id) VALUES
(1, 2, 0.0, 0.0, 0.0, 2),
(2, 3, 0.0, 0.0, 1.0, 2),
(3, 5, 0.0, 1.0, 0.0, 2),
(4, 5, 0.0, 1.0, 1.0, 3),
(5, 3, 0.0, 0.0, 0.0, 5),
(6, 2, 0.0, 0.0, 1.0, 5),
(7, 3, 0.0, 0.0, 0.0, 7),
(8, 4, 0.0, 0.0, 1.0, 7);

INSERT INTO recipe_ingredient (recipe_id, ingredient_id, quantity) VALUES
(1, 1, 2),
(1, 2, 3),
(1, 3, 4),
(1, 4, 5),
(1, 5, 6),
(1, 6, 1),
(3, 6, 6),
(1, 7, 1),
(3, 7, 7);

INSERT INTO recipe_customer (recipe_id, customer_id) VALUES
(2, 1),
(7, 2);

INSERT INTO inventory_ingredient(manager_id, ingredient_id, quantity) VALUES
(1,1,3),
(1,2,2),
(1,3,4);


INSERT INTO manager_customer (manager_id, customer_id) VALUES
(1,1);