# Java Console To-Do List Application

A light-weight, console-based Task Management application written in Java. This application lets users manage tasks with priority levels and automatically persists all data to a local text file.

## Features

* **Task Management**: Add, view, complete, and delete tasks easily.
* **Priority Levels**: Assign priorities (`LOW`, `MEDIUM`, `HIGH`) to each task.
* **Priority Filtering**: Filter and list only `HIGH` priority tasks for quick focus.
* **File Persistence**: Tasks are saved automatically to `tasks.txt` on every change and loaded when the program starts.
* **Input Validation & Error Handling**: Gracefully handles empty inputs, invalid selection numbers, and corrupted file entries.

## Project Structure

```
├── Priority.java    # Enum defining task priority levels (LOW, MEDIUM, HIGH)
├── Task.java        # Class representing individual task objects
├── TodoApp.java     # Main entry point, UI loop, and file storage logic
└── tasks.txt        # Local data store (generated automatically on runtime)
```

## Data Persistence Format

Tasks are automatically saved in `tasks.txt` in a custom pipe-delimited format:

```text
isDone|Priority|Task Title
```

### Example `tasks.txt`:
```text
false|HIGH|Finish homework assignment
true|MEDIUM|Buy groceries
false|LOW|Read a book chapter
```

## Prerequisites

* **Java Development Kit (JDK)**: Version 14 or higher (required for switch arrow syntax `->`).

Check your installed Java version:
```bash
java -version
```

## Compilation and Execution

1. **Clone or Download** all three source files (`Priority.java`, `Task.java`, `TodoApp.java`) into the same folder.

2. **Compile the Java files**:
   ```bash
   javac Priority.java Task.java TodoApp.java
   ```
   *(Or compile all files using `javac *.java`)*

3. **Run the Application**:
   ```bash
   java TodoApp
   ```

## Usage Example

```text
Loaded 1 task(s).

=== TO-DO LIST ===
1. Add task
2. List tasks
3. Complete task
4. Delete task
5. List HIGH priority only
6. Exit
Choose: 1

Task: Complete Java Project
Priority (1=LOW, 2=MEDIUM, 3=HIGH): 3
Added. Total: 2

=== TO-DO LIST ===
1. Add task
2. List tasks
3. Complete task
4. Delete task
5. List HIGH priority only
6. Exit
Choose: 2

1. [ ] (MEDIUM) Buy groceries
2. [ ] (HIGH) Complete Java Project

=== TO-DO LIST ===
1. Add task
2. List tasks
3. Complete task
4. Delete task
5. List HIGH priority only
6. Exit
Choose: 3

Complete which number? 
2
Completed: Complete Java Project
```

## Future Enhancements

* Add ability to edit task descriptions.
* Sort tasks by priority or status.
* Support custom file locations or command-line arguments.