CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS Users (
    id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID_GENERATE_V4()),
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    date_of_birth DATE,
    bio TEXT,
    profile_picture BYTEA,
    superuser BOOLEAN DEFAULT FALSE, -- New column for indicating superuser status
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP
);
CREATE TABLE IF NOT EXISTS Recipe (
    id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID_GENERATE_V4()),
    name TEXT NOT NULL,
    description TEXT,
    instructions TEXT[],
    ingredients TEXT[],
    amounts TEXT[],
    user_id CHAR(36) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS Favorites (
    id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID_GENERATE_V4()),
    user_id CHAR(36) NOT NULL,
    recipe_id CHAR(36) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (recipe_id) REFERENCES Recipe(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS Reviews (
    id CHAR(36) NOT NULL PRIMARY KEY DEFAULT (UUID_GENERATE_V4()),
    user_id CHAR(36) NOT NULL,
    recipe_id CHAR(36) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (recipe_id) REFERENCES Recipe(id) ON DELETE CASCADE,
    rating INT NOT NULL,
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);