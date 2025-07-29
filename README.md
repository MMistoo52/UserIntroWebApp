# UserIntroWebApp

A simple Java dynamic web project built with JSP, Servlets, and Tomcat. The application allows users to:

- Register an account
- Login with credentials
- Enter introduction and hobbies on first login
- View saved introduction on subsequent logins

## 🛠 Technologies Used

- Java (Servlets + JSP)
- Apache Tomcat 9
- MySQL
- Docker (for deployment)
- Render (for cloud hosting)

## 🧾 Database Schema

Use the provided `schema.sql` file to create the database:

```
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
```

## 🚀 Deployment on Render

1. Push this project to a public GitHub repo
2. Go to https://render.com and create a new Web Service
3. Select:
   - **Environment**: Docker
   - **Start Command**: `catalina.sh run`
   - **Port**: 8080
4. Render will build and deploy your app using the included Dockerfile

## 👨‍💻 Local Development

To run locally using Docker:

```bash
docker build -t userintroapp .
docker run -p 8080:8080 userintroapp
```

Visit `http://localhost:8080` in your browser.
