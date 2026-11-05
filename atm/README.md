# Java ATM Simulator

A simple, interactive console-based Automated Teller Machine (ATM) application written in Java. This program simulates essential banking operations including PIN authentication, balance checking, deposits, and withdrawals.

---

## Features

- **Secure PIN Authentication**: Allows up to 3 attempts to authenticate with a default PIN (`1234`). Automatically locks the account if 3 failed attempts are reached.
- **Check Balance**: Displays the user's current account balance.
- **Deposit Funds**: Allows users to deposit money with input validation (amounts must be greater than zero).
- **Withdraw Funds**: Allows users to withdraw money with input validation for negative amounts and insufficient funds.
- **Interactive Menu System**: Switch-based console menu for continuous navigation until explicit exit.

---

## Default Credentials & Initial State

- **Default PIN**: `1234`
- **Starting Balance**: `$1000.00`
- **Max Login Attempts**: `3`

---

## Prerequisites

- **Java Development Kit (JDK)**: Version 14 or higher (utilizes arrow syntax `->` in `switch` statements).

Check your installed Java version:
```bash
java -version
```

---

## How to Run

1. **Clone or Download the Repository**
   Save the source code into a file named `Atm.java`.

2. **Compile the Application**
   Open your terminal/command prompt and run:
   ```bash
   javac Atm.java
   ```

3. **Run the Application**
   Execute the compiled bytecode:
   ```bash
   java Atm
   ```

---

## Usage Example

```text
=== Welcome to Java ATM ===
Enter your PIN: 1234

1. Check Balance
2. Deposit
3. Withdraw
4. Exit
Choose an option: 1
Your balance: $1000.00

1. Check Balance
2. Deposit
3. Withdraw
4. Exit
Choose an option: 2
Enter deposit amount: 250
Deposited $250.00. New balance: $1250.00

1. Check Balance
2. Deposit
3. Withdraw
4. Exit
Choose an option: 4
Goodbye!
```

---

## Code Structure

- `main(String[] args)`: Program entry point; initializes balance and manages the main execution loop.
- `login(Scanner scanner)`: Handles authentication logic with fixed 3-attempt lockouts.
- `showMenu()`: Prints available menu choices to the console.
- `deposit(Scanner scanner, double balance)`: Validates and processes deposit transactions.
- `withdraw(Scanner scanner, double balance)`: Validates and processes withdrawal transactions against available balance.

---

## Future Improvements

- Add persistent storage (file handling or database connection) to save balances across runs.
- Support multiple user accounts and custom PIN creation.
- Add transaction history logs.