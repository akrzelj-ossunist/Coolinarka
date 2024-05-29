CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS Users (
    id char(36) NOT NULL PRIMARY KEY DEFAULT (UUID_GENERATE_V4()),
    username VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    bio VARCHAR(255) NOT NULL,
    birthday TIMESTAMP NOT NULL,
    role VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Recipe (
    id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID_GENERATE_V4()),
    image VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    user_id VARCHAR(100) NOT NULL,
    country VARCHAR(100),
    season VARCHAR(100),
    difficulty VARCHAR(100) NOT NULL,
    people INT NOT NULL,
    prep_time INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Recipe_Ingredient (
    id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID_GENERATE_V4()),
    recipe_id VARCHAR(100) NOT NULL,
    ingredient VARCHAR(100) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    unit VARCHAR(50) NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES Recipe(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Recipe_Phase (
    id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID_GENERATE_V4()),
    recipe_id VARCHAR(100) NOT NULL,
    phase_number INT NOT NULL,
    description VARCHAR(1000) NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES Recipe(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Recipe_Meal_Group (
    id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID_GENERATE_V4()),
    recipe_id VARCHAR(100) NOT NULL,
    meal_group VARCHAR(100) NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES Recipe(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Recipe_Event (
    id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID_GENERATE_V4()),
    recipe_id VARCHAR(100) NOT NULL,
    event VARCHAR(100) NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES Recipe(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Favorite (
    id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID_GENERATE_V4()),
    user_id VARCHAR(100) NOT NULL,
    recipe_id VARCHAR(100) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (recipe_id) REFERENCES Recipe(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Review (
    id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID_GENERATE_V4()),
    user_id VARCHAR(100) NOT NULL,
    recipe_id VARCHAR(100) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (recipe_id) REFERENCES Recipe(id) ON DELETE CASCADE,
    rating INT NOT NULL,
    comment VARCHAR(500) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);