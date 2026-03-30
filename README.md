# • Java Employee Management System

A console-based Employee Management System built using Java.  
This project allows users to manage employee data through full CRUD operations (Create, Read, Update, Delete).

---

## • Features

- Add new employees
- View employee details
- Update employee information
- Remove employees
- Input validation and error handling
- Automatic data shifting after deletion

---

## • How It Works

The system stores employee data using arrays:

- `name[]`
- `age[]`
- `monthlySalary[]`

Users interact with the system through a menu-driven interface in the console.

---

## • Technologies Used

- Java
- Console (CLI)
- Arrays and basic data structures

---

## • Background & Reflection
This project was developed during my **Stage 1 (Freshman year)** studies in 2023-2024. It was one of my first independent programming projects, built entirely from scratch without the use of AI tools. 

I've kept this repository in its original form to document my early journey in Java and my passion for building functional tools from the ground up.

A particular challenge was the **Remove** functionality. Since arrays have a fixed size in Java, I implemented a manual shifting algorithm to ensure that when an employee is deleted, the subsequent records move up to fill the empty slot. This taught me a great deal about how data is handled at a low level before moving on to higher-level collections like `ArrayList`.

---

## • How to Run

1. Clone the repository

2. Open the project in your IDE

3. Run the `EmployeeManagementSystem.java` file

---

## • License

This project is licensed under the MIT License.