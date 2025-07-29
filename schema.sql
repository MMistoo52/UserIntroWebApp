CREATE DATABASE user_webapp;

USE user_webapp;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE user_intro (
    intro_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    introduction TEXT,
    hobbies TEXT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);