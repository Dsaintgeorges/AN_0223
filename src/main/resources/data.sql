CREATE TABLE IF NOT EXISTS users (
    id UUID DEFAULT RANDOM_UUID(),
    name VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

-- Create Animal table
CREATE TABLE IF NOT EXISTS animal (
    id UUID DEFAULT RANDOM_UUID(),
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    user_id UUID NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
    );
-- Insert data for User table
INSERT INTO users (id, name, firstname) VALUES
                                            (UUID(), 'John', 'Doe'),
                                            (UUID(), 'Jane', 'Smith'),
                                            (UUID(), 'Peter', 'Parker'),
                                            (UUID(), 'Sarah', 'Johnson'),
                                            (UUID(), 'Michael', 'Williams');

-- Insert data for Animal table
INSERT INTO animal (id, name, type, user_id) VALUES
                                                 (UUID(), 'Rex', 'chien', (SELECT id FROM users WHERE name = 'John')),
                                                 (UUID(), 'Garfield', 'chat', (SELECT id FROM users WHERE name = 'Jane')),
                                                 (UUID(), 'Nemo', 'poisson', (SELECT id FROM users WHERE name = 'Peter')),
                                                 (UUID(), 'Luna', 'chat', (SELECT id FROM users WHERE name = 'Jane')),
                                                 (UUID(), 'Buddy', 'chien', (SELECT id FROM users WHERE name = 'John')),
                                                 (UUID(), 'Nemo II', 'poisson', (SELECT id FROM users WHERE name = 'Peter')),
                                                 (UUID(), 'Simba', 'lion', (SELECT id FROM users WHERE name = 'Sarah')),
                                                 (UUID(), 'Shadow', 'chien', (SELECT id FROM users WHERE name = 'Michael')),
                                                 (UUID(), 'Whiskers', 'chat', (SELECT id FROM users WHERE name = 'Jane')),
                                                 (UUID(), 'Max', 'chien', (SELECT id FROM users WHERE name = 'John')),
                                                 (UUID(), 'Oliver', 'chat', (SELECT id FROM users WHERE name = 'Jane')),
                                                 (UUID(), 'Bubbles', 'poisson', (SELECT id FROM users WHERE name = 'Peter')),
                                                 (UUID(), 'Fluffy', 'chat', (SELECT id FROM users WHERE name = 'Sarah')),
                                                 (UUID(), 'Bella', 'chat', (SELECT id FROM users WHERE name = 'Michael')),
                                                 (UUID(), 'Gizmo', 'chat', (SELECT id FROM users WHERE name = 'Jane')),
                                                 (UUID(), 'Daisy', 'chien', (SELECT id FROM users WHERE name = 'John')),
                                                 (UUID(), 'Boris', 'poisson', (SELECT id FROM users WHERE name = 'Sarah')),
                                                 (UUID(), 'Rocky', 'chien', (SELECT id FROM users WHERE name = 'Michael')),
                                                 (UUID(), 'Bubbles II', 'poisson', (SELECT id FROM users WHERE name = 'Peter')),
                                                 (UUID(), 'Lucy', 'chat', (SELECT id FROM users WHERE name = 'Sarah')),
                                                 (UUID(), 'Finn', 'chien', (SELECT id FROM users WHERE name = 'Michael')),
                                                 (UUID(), 'Toby', 'chat', (SELECT id FROM users WHERE name = 'Jane')),
                                                 (UUID(), 'Mittens', 'chat', (SELECT id FROM users WHERE name = 'Sarah')),
                                                 (UUID(), 'Chloe', 'chat', (SELECT id FROM users WHERE name = 'Jane')),
                                                 (UUID(), 'Dexter', 'chat', (SELECT id FROM users WHERE name = 'John')),
                                                 (UUID(), 'Coco', 'chien', (SELECT id FROM users WHERE name = 'Michael')),
                                                 (UUID(), 'Leo', 'chat', (SELECT id FROM users WHERE name = 'Sarah')),
                                                 (UUID(), 'Misty', 'chat', (SELECT id FROM users WHERE name = 'Michael'));