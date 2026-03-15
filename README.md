# Java In-Memory Database Engine

A modular, extensible, in-memory database engine built in Java. This project simulates core database functionalities (CRUD, schema management, querying, and cloning) while demonstrating advanced software engineering principles through the extensive use of **6 different Design Patterns**.

## Overview

This project was developed to practice object-oriented analysis, Clean Code, and the practical application of architectural and behavioral design patterns. It provides a flexible and well-structured foundation for a lightweight data engine operating entirely in memory.

## Key Features

-   **Modular Core:** Clean separation between data structures (`core`) and execution logic (`command`).
-   **In-Memory CRUD Operations:** Full support for Create, Read, Update, and Delete operations on database tables.
-   **Flexible Schemas:** Define table schemas with various data types (`INT`, `STRING`, `BOOL`, etc.) and enforce column validation.
-   **Generic Querying:** Perform detailed queries using generic `Condition` objects, allowing for complex and reusable filtering logic.
-   **Deep Copy Cloning:** Reliable table cloning with full deep copy logic, ensuring that cloned tables are independent of their originals.
-   **Automated Action Logging:** A built-in logging system that automatically records every data-modifying action (`INSERT`, `UPDATE`, `DELETE`) with details.

## Design Patterns Implemented

The architecture of this system is heavily influenced by the following design patterns, each chosen to solve a specific engineering challenge:

### 1. Singleton (Creational)
The **`DatabaseManager`** is implemented as a Singleton. This ensures there is a single, centralized instance managing all tables and system state, preventing inconsistencies.

### 2. Builder (Creational)
The **`TableBuilder`** provides a Fluent API for creating database tables. This approach makes table and schema definition intuitive and readable, allowing for step-by-step construction of complex objects.

### 3. Facade (Structural)
The **`DBFacade`** provides a simplified interface to the system's complex internal functionalities. Clients interact with the facade, which orchestrates various internal components (like commands and the database manager) behind the scenes.

### 4. Command (Behavioral)
All database operations (`InsertCommand`, `UpdateCommand`, `DeleteCommand`, `QueryCommand`) are encapsulated as objects inheriting from a common base. This decouples the invoker from the receiver and opens possibilities for feature expansions like transaction logging or undo/redo.

### 5. Template Method (Behavioral)
Implemented in the **`BaseDBCommand`** class. The `execute()` method defines the invariant skeleton of a database operation (Validation -> Execution -> Result Handling). Subclasses override specific hook methods (`validate()`, `applyLogic()`) to provide their unique logic without changing the overall flow.

### 6. Observer (Behavioral)
The automated logging system is built using the Observer pattern. The **`DatabaseManager`** acts as the Subject, and the **`DBLogger`** as the Observer. Any modification to table data notifies the logger, which automatically records the action, keeping the core logic decoupled from logging concerns.

## Getting Started

*(Optional: Insert instructions on how to run your project, for example:)*

1.  Clone the repository.
2.  Open in your preferred IDE (e.g., IntelliJ IDEA).
3.  Run the `Main.main()` method to see a complete demonstration of the system's capabilities.
