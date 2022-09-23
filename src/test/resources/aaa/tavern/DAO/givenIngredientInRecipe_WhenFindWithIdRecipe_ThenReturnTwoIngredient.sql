INSERT INTO category (name)
VALUES
('testCategory');

INSERT INTO subcategory (name, id_category)
VALUES
('testSubCategory',1);

INSERT INTO recipe (name, selling_price, level, consommation_time, preparation_time, peremption_date, exp_given, id_subcategory)
VALUES
('recipe1', 10, 1, '11:22:33', '11:22:33', '2022-01-02',1,1),
('recipe2', 10, 1, '11:22:33', '11:22:33', '2022-01-02',1,1),
('recipe3', 10, 1, '11:22:33', '11:22:33', '2022-01-02',1,1);

INSERT INTO ingredient(name, level, buying_price,id_subcategory)
VALUES
('testIngredient1',1,2,1),
('testIngredient2',1,2,1),
('testIngredient3',1,2,1),
('testIngredient4',1,2,1),
('testIngredient5',1,2,1);

INSERT INTO recipe_ingredient (id_ingredient,id_recipe,quantity)
VALUES
(1,1,1),
(2,1,2),
(1,2,1);