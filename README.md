Store Management System
## Project Overview
This Store Management System is a Java-based application designed to simulate and manage the operations of a retail store. It handles product inventory, sales processes, cashier management, and financial tracking.
## Features

Product Management: Add and manage products with details such as name, price, category, expiry date, and stock levels.
Sales Processing: Handle sales transactions, including price calculation and stock updates.
Cashier Management: Manage store cashiers and their details.
Receipt Generation: Create and store detailed receipts for each sale.
Financial Tracking: Monitor revenue, expenses, and calculate profits.
Expiry Date Handling: Implement discounts for products nearing expiry and prevent sales of expired products.
File I/O: Save and read receipts from files.
Exception Handling: Custom exceptions for scenarios like insufficient stock or expired products.

## Project Structure
The project is organized into several packages:

entities: Contains classes for core objects (Product, Cashier, Receipt)
services: Includes the StoreManager class for business logic
exceptions: Custom exception classes
utils: Utility classes like ReceiptUtils for file operations
main: Contains the main application entry point

## Key Classes

Product: Represents store items with properties and methods for price calculation and stock management.
Cashier: Represents store employees who handle sales.
Receipt: Manages the details of each sale transaction.
StoreManager: Central class that orchestrates store operations.
ReceiptUtils: Handles reading and writing receipts to files.
