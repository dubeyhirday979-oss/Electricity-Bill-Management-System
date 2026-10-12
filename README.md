# Electricity Bill Management System (Java)

A beginner-friendly console-based Java project for managing electricity customer records and estimating bills.

## Features
- Add a customer with name, address, and units consumed
- View all customers and calculated bills
- Search by customer ID
- Update customer details and units
- Delete a customer record
- Input validation and exception handling

## Technologies
- Java (JDK 17 or later recommended)
- Object-Oriented Programming
- ArrayList and basic CRUD operations

## Sample tariff used
- 0–100 units: Rs. 3 per unit
- 101–200 units: Rs. 5 per additional unit
- Above 200 units: Rs. 7 per additional unit

**Note:** These are sample rates for this demo, not official electricity tariffs.

## Run the project
1. Install a JDK (JDK 17 or later recommended).
2. Open a terminal in this folder.
3. Compile:
   ```bash
   javac ElectricityBillManagementSystem.java
   ```
4. Run:
   ```bash
   java ElectricityBillManagementSystem
   ```

## Limitations
Records are stored in memory and are cleared when the program exits. Database persistence and a graphical interface are not included.
