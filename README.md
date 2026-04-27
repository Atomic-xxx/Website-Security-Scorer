# 🔐 Website Security Scoring System

## 👥 Team Members

* Mayank Saini
* Shaurya Vardhan
* Akash Negi

---

## 📌 Project Overview

The **Website Security Scoring System** is a Java-based application that evaluates the security level of a given website URL and assigns a score based on multiple parameters.

This project is designed as a **capstone project** to demonstrate the application of **Object-Oriented Programming (OOP)** concepts along with **JDBC (Database Connectivity)** and **Swing GUI**.

---

## 🎯 Objectives

* To design a modular system using OOP principles
* To analyze websites based on basic security parameters
* To store scan results using a MySQL database
* To build a user-friendly interface using Java Swing

---

## ⚙️ Features

* Analyze website security using:

  * HTTPS Check
  * SSL Certificate Check
  * Domain Validation
  * IP Address Resolution
  * Network Reachability

* Generate a **security score (out of 100)**

* Display **individual check scores**

* Store results in database using JDBC

* View scan history (via database)

---

## 🧠 OOP Concepts Used

* **Abstraction** → `SecurityCheck` interface
* **Polymorphism** → Multiple checks executed using a common interface
* **Encapsulation** → `ScanResult` class
* **Modular Design** → Separation into model, service, database, and UI layers

---

## 🏗️ Project Structure

```bash
src/
 ┣ model/
 ┃ ┗ ScanResult.java
 ┣ checks/
 ┃ ┣ SecurityCheck.java (ITERFACE)
 ┃ ┣ HttpsCheck.java
 ┃ ┣ SSLCheck.java
 ┃ ┣ DomainCheck.java
 ┃ ┣ IPCheck.java
 ┃ ┗ NetworkCheck.java
 ┣ service/
 ┃ ┗ SecurityAnalyzer.java
 ┣ database/
 ┃ ┣ DatabaseManager.java (CONNECTION ESTABLISH)
 ┃ ┗ ResultDAO.java  (UPDATE TO DATABASE)
 ┣ ui/
 ┃ ┗ MainFrame.java (UI)
 ┗ Main.java  (MAIN)

lib/
 ┗ mysql-connector-j-9.6.0.jar ( driver local only) 

sql/
 ┗ schema.sql
```

---

## 🛠️ Technologies Used

* Java
* Swing (GUI)
* JDBC (MySQL)
* Git & GitHub

---

## 💾 Database Design

```sql
CREATE DATABASE security_scanner;
USE security_scanner;

CREATE TABLE results (
    sno INT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    https_score INT DEFAULT 0 CHECK (https_score >= 0),
    ssl_score INT DEFAULT 0 CHECK (ssl_score >= 0),
    domain_score INT DEFAULT 0 CHECK (domain_score >= 0),
    ip_score INT DEFAULT 0 CHECK (ip_score >= 0),
    network_score INT DEFAULT 0 CHECK (network_score >= 0),
    total_score INT DEFAULT 0 CHECK (total_score >= 0),
    scan_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## ⚙️ How It Works

1. User enters a website URL
2. System performs multiple security checks
3. Each check returns a score
4. Scores are combined to generate a final score
5. Result is displayed and stored in database

---

## 🚀 How to Run

1. Clone the repository
2. Download MySQL Connector/J
3. Place the `.jar` file inside the `lib/` folder
4. Compile:

```bash
javac -d . src/checks/*.java src/service/*.java src/model/*.java src/database/*.java src/ui/*.java src/Main.java
```

5. Run:

```bash
java -cp ".;lib/mysql-connector-j-9.6.0.jar" Main
```

---

## ⚠️ Limitations

* This is not a full cybersecurity tool
* Security checks are basic and heuristic-based
* Network and SSL checks may vary depending on system/network conditions

---

## 📎 Future Enhancements

* Advanced security analysis
* Improved GUI design
* Report generation (PDF)
* User authentication system

---

## 🔗 GitHub Repository

https://github.com/Atomic-xxx/Website-Security-Scorer

---

## 💬 Conclusion

This project demonstrates how OOP principles can be used to design a structured and scalable application while integrating database connectivity and a graphical user interface.
