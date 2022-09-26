INSERT INTO category (name)
VALUES ("CategoryTest1");
INSERT INTO subcategory (name, category_id)
VALUES ("subcategoryTest1",1);
INSERT INTO ingredient (name, level, buying_price, subcategory_id)
VALUES ("IngredientTest1", 1,20,1);
INSERT INTO player (email, nickname, password)
VALUES ("Test1@example.com", "TestPlayer1", "azerty");
INSERT INTO manager (name, reputation, chest, level, experience, player_id)
VALUES ("ManagerTest1", 10, 10, 1, 10, 1);