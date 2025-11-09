🛒 E-Commerce Product Review Platform

📘 Project Overview

The E-Commerce Product Review Platform is a Java-based desktop application that simulates a simple e-commerce review system.
Users can view products, add product reviews with star ratings, and view all reviews — all connected through a MySQL database using JDBC.

This project demonstrates modular programming, database integration, and GUI development using Java Swing — ideal for academic mini-projects or as a foundation for full-scale systems.

🧩 Modules
Module 1 – Database Setup

Creates the ecommerce database and necessary tables (products, reviews).

Connects to MySQL using JDBC.

Module 2 – Add Product

Inserts new product details into the database.

Displays confirmation once products are successfully added.

Module 3 – Database Connection Test

Verifies connection between the Java application and MySQL database.

Module 4 – Product Management GUI

Displays all products fetched from the database.

Allows adding new products dynamically through GUI.

Module 5 – Review Management GUI

Displays all products.

Lets the user:

View Products → Lists all products in DB.

Add Review → Enter product name, write a review, and select star rating (1–5).

View Reviews → Displays all reviews with their star ratings.

All reviews are stored in the MySQL database under the reviews table.

🧠 Technologies Used

| Category                  | Tools        |
| ------------------------- | ------------ |
| **Programming Language**  | Java         |
| **Database**              | MySQL        |
| **Database Connectivity** | JDBC         |
| **IDE**                   | Eclipse      |
| **GUI Framework**         | Swing        |
| **Version Control**       | Git & GitHub |

⚙️ Prerequisites

Before running the project, ensure you have:

JDK 17+

MySQL Server

Eclipse IDE (or any IDE supporting Java)

MySQL Connector/J (JDBC driver) — add it inside the lib folder.

🗄️ Database Setup
1. Create Database:
CREATE DATABASE ecommerce;
USE ecommerce;
2. Create Tables:
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    price DECIMAL(10,2)
);

CREATE TABLE reviews (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100),
    review_text VARCHAR(255),
    rating INT
);
🧰 How to Run
Step 1: Clone the Repository
git clone https://github.com/Aswinx05/ECommerce_Product_Review_Platform.git
cd ECommerce_Product_Review_Platform
Step 2: Open in Eclipse

Go to File → Import → Existing Projects into Workspace

Select the cloned folder and import.

Step 3: Add JDBC Library

Right-click project → Build Path → Configure Build Path → Add External JARs

Choose the mysql-connector-java.jar file inside lib/.

Step 4: Run Module 4

Adds products into the database.

Displays them in the GUI.

Step 5: Run Module 5

Expected Output:
Open GUI
→ Click “View Products” → shows all products from DB
→ Click “Add Review” → enter product + review + star rating (1–5)
→ Click “View Reviews” → shows all stored reviews

💾 Folder Structure
ECommerce_Product_Review_Platform/
│
├── src/
│   └── mini_project/
│       ├── Module1_DatabaseSetup.java
│       ├── Module2_AddProduct.java
│       ├── Module3_ConnectionTest.java
│       ├── Module4_ProductGUI.java
│       └── Module5_ReviewGUI.java
│
├── lib/
│   └── mysql-connector-java.jar
│
├── .gitignore
├── reviews.txt
└── README.md

📸 Output Preview

Main GUI Flow:

✅ View Products

⭐ Add Review with Star Rating

💬 View All Reviews

👨‍💻 Developer

👤 Name: Aswin Ananth S

🎓 Department: Artificial Intelligence & Data Science

🏫 Institution: [Rajalakshmi Institute Of Technology]

💡 Project Type: AI Lab Mini Project — E-Commerce Product Review Platform

📅 Year: 2025

🌐 GitHub: github.com/Aswinx05
