INSERT INTO player (id, email, nickname, password) 
VALUES (1, 'Test1@test.com', 'Test1', 'azerty');
INSERT INTO player (id, email, nickname, password) 
VALUES (2, 'Test2@test.com', 'Test2', 'azerty');
INSERT INTO manager (id, name, reputation, chest, level, experience, player_id)
VALUES  (1, 'Manager1' , 100, 10, 1, 0, 1),
        (2, 'Manager2' , 100, 10, 1, 0, 1),
        (3, 'Manager3' , 100, 10, 1, 0, 1),
        (4, 'Manager4' , 100, 10, 1, 0, 1),
        (5, 'Manager5' , 100, 10, 1, 0, 1);