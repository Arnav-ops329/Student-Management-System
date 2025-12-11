📚 Student Management System – Java (File Handling Project)

A fully functional menu-driven Student Management System built in Java using File Handling, Collections, and Modular Programming.
This project performs complete CRUD operations on student records stored in .txt files.

🚀 Project Features
✔ 1. Add Student Names

Takes multiple names as input

Stores them in Data.txt

Prevents empty name entries

✔ 2. Add Student Details

Stores details in Details.txt as CSV

Fields include:

Name

Roll Number

Mobile Number

Course

Address

Prevents duplicates

Validates mobile numbers

✔ 3. Display All Names

Reads and prints all names from Data.txt

✔ 4. Display All Student Details

Reads Details.txt

Parses CSV format

Displays full details cleanly

✔ 5. Display Details of a Specific Student

Search by name

Prints only that student’s record

✔ 6. Delete Names

Delete any name from Data.txt

Uses Iterator to avoid ConcurrentModificationException

✔ 7. Delete Student Details

Remove a student’s entire record from Details.txt

✔ 8. Update Student Details

Search for a student

Update any or all fields

Save updated data back to file

🧠 Concepts Used

This project helped me learn and understand:

🔹 Java File Handling

File, FileWriter, FileReader

BufferedReader, BufferedWriter

🔹 Collections Framework

ArrayList for storing records

Iterator for safe deletion

🔹 String Manipulation

split(",") for parsing CSV

Validations (empty input, mobile length, duplicates)

🔹 Control Flow & Modular Design

Menu-driven program

Separate methods for each operation

Clean, reusable code structure

📁 File Structure
📦 Student-Management-System
 ┣ 📜 SMS.java
 ┣ 📜 Data.txt        // Stores student names
 ┣ 📜 Details.txt     // Stores student details (CSV format)
 ┗ 📜 README.md

🏃‍♂️ How to Run the Project
1️⃣ Compile the program:
javac SMS.java

2️⃣ Run the program:
java SMS

3️⃣ Follow the menu to perform operations.
🧩 Sample Record Format
Data.txt
XYZ
ABC

Details.txt
XYZ,101,9876543210,BCA,Delhi
ABC,102,9123456780,B.Tech,Mumbai

🛠 Improvements Planned

Convert storage from text files → JSON / CSV structured files

Add proper OOP model with a Student class

Add sorting & filtering features

Build a GUI version using Java Swing

Build a backend API using Spring Boot

🌟 Why This Project Matters

This project strengthened my understanding of:

Real-world CRUD operations

Persistent storage

Clean code architecture

Debugging skills

Java fundamentals (Collections + File I/O)

It represents my progress from learning basics to building a fully working application.

🙌 Feel Free to Contribute

If you want to suggest improvements, open a pull request or raise an issue.
Collaboration is always welcome!

