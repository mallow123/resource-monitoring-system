# 🚀 Resource Monitoring System

A Java-based system that monitors CPU, Memory, and Disk usage using MySQL and JDBC.

---

## 🔥 Features

- 📊 View system resource logs
- ➕ Add new logs
- ⚠ Detect high CPU usage
- 🔍 Detailed logs with system names (JOIN queries)
- 🧠 Input validation with foreign key checks
- 💻 Menu-driven CLI interface

---

## 🛠 Tech Stack

- Java
- MySQL
- JDBC
- DBeaver

---

## 🧱 Project Structure
```bash
src/
├── model/
├── dao/
├── service/
├── util/
└── Main.java
```

---

## ⚙️ Setup

1. Install MySQL and Java
2. Import database
3. Run:
```bash
javac -cp .:mysql-connector.jar /.java Main.java
java -cp .:mysql-connector.jar Main
```
---

---

## 🚀 Future Scope

- GUI Dashboard (Swing / JavaFX)
- Real-time monitoring
- Email alerts
- Cloud integration


