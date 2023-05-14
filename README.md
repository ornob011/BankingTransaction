# Banking Transaction

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)     ![GitHub issues](https://img.shields.io/github/issues/ornob011/BankingTransaction)
![GitHub forks](https://img.shields.io/github/forks/ornob011/BankingTransaction)
![GitHub stars](https://img.shields.io/github/stars/ornob011/BankingTransaction)

<p align="justify">
Banking Transaction is a comprehensive banking transaction management system that allows users to perform various banking operations such as account creation, balance inquiry, deposit, withdrawal, and fund transfer. The project is designed to be scalable, secure, and easy to use.
</p>

## Table of Contents

- [Banking Transaction](#banking-transaction)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
  - [System Requirements](#system-requirements)
  - [Installation](#installation)
  - [Usage](#usage)
  - [Database Schema](#database-schema)
    - [Database Schema Diagram](#database-schema-diagram)
    - [Users Table](#users-table)
    - [Accounts Table](#accounts-table)
    - [Transaction History Table](#transaction-history-table)
    - [Payments Table](#payments-table)
    - [Views](#views)
  - [Technical Support](#technical-support)
  - [Contributing](#contributing)
  - [License](#license)

## Features

- Account creation with unique account numbers
- Balance inquiry
- Deposit and withdrawal transactions
- Fund transfer between accounts
- Transaction history
- Secure authentication and authorization
- RESTful API for easy integration with other systems

## System Requirements

- IDE: IntelliJ IDEA Ultimate/Netbeans/Eclipse
- OpenJDK 1.8
- MySQL Server
- Maven
## Installation

1. Clone the repository:

```bash
git clone https://github.com/ornob011/BankingTransaction.git
```
2. Change to the project directory:
```bash
cd BankingTransaction
```
3. Install the dependencies:
```bash
mvn clean install
```
4. Run the MySQL server and import the [**banking_app.sql**](src/main/resources/db_script/banking_app.sql) file from the project directory to the server.

5. Run the application:
```bash
mvn spring-boot:run
```

6. The application will be available at **http://localhost:8070**

## Usage
<p align="justify">
After running the application, you can access the BankingTransaction system through the provided API endpoints. You can use tools like Postman to interact with the API.
</p>

## Database Schema
<p align="justify">
This database schema is designed to store information related to a banking application. It consists of four entities: users, accounts, transaction_history, and payments.
</p>

### Database Schema Diagram

<p align="justify">
The database schema diagram provides a visual overview of the database structure and relationships between tables. It helps developers and stakeholders understand the data model and how different entities are related to each other within the system. It provides an easy-to-understand representation of the project's underlying data architecture, which can be helpful for new contributors or potential users who want to get a quick understanding of how the application works.

![Database Schema Diagram](assets/DBMS_Schema.png)
    <p align="center">Database Schema Diagram </p>
</p>

### Users Table
<p align="justify">

The `users` table stores information about each user of the application, including their name, email address, password, and verification status. 
</p>

<p align="justify">

| Column Name | Data Type | Description |
| --- | --- | --- |
| user_id | INT | Primary key that uniquely identifies each user. |
| first_name | VARCHAR(50) | User's first name. |
| last_name | VARCHAR(50) | User's last name. |
| email | VARCHAR(255) | User's email address (unique). |
| password | VARCHAR(255) | User's password. |
| token | VARCHAR(255) | Token generated during verification process. |
| code | INT | Code sent to user during verification process. |
| verified | INT | Indicates whether the user has been verified (default 0). |
| verified_at | DATETIME | Timestamp of when the user was verified. |
| created_at | TIMESTAMP | Timestamp of when the user account was created. |
| updated_at | TIMESTAMP | Timestamp of when the user account was last updated. |
</p>

### Accounts Table
<p align="justify">

The `accounts` table stores information about each account, including the account number, account name, account type, and current balance. 

</p>

<p align="justify">

| Column Name | Data Type | Description |
| --- | --- | --- |
| account_id | INT | Primary key that uniquely identifies each account. |
| user_id | INT | Foreign key that links each account to a user. |
| account_number | VARCHAR(100) | Account number assigned to the account. |
| account_name | VARCHAR(50) | Name of the account. |
| account_type | VARCHAR(50) | Type of the account. |
| balance | DECIMAL(18, 2) | Current balance in the account. |
| created_at | TIMESTAMP | Timestamp of when the account was created. |
| updated_at | TIMESTAMP | Timestamp of when the account was last updated. |

</p>

### Transaction History Table
<p align="justify">

The `transaction_history` table stores information about each transaction made on an account, including the type of transaction, amount, source, status, and reason code (if applicable).
</p>

<p align="justify">

| Column Name | Data Type | Description |
| --- | --- | --- |
| transaction_id | INT | Primary key that uniquely identifies each transaction. |
| account_id | INT | Foreign key that links each transaction to an account. |
| transaction_type | VARCHAR(50) | Type of the transaction (e.g. deposit, withdrawal). |
| amount | DECIMAL(18, 2) | Amount of the transaction. |
| source | VARCHAR(50) | Source of the transaction. |
| status | VARCHAR(50) | Status of the transaction (success or failed). |
| reason_code | VARCHAR(100) | Reason code for a failed transaction (e.g. insufficient funds). |
| created_at | TIMESTAMP | Timestamp of when the transaction was made. |
</p>

### Payments Table
<p align="justify">

The `payments` table stores information about each payment made from an account, including the beneficiary name and account number, amount, reference number, status, and reason code (if applicable).

</p>

<p align="justify">

| Column Name | Data Type | Description |
| --- | --- | --- |
| payment_id | INT | Primary key that uniquely identifies each payment. |
| account_id | INT | Foreign key that links each payment to an account. |
| beneficiary | VARCHAR(50) | Name of the beneficiary. |
| beneficiary_acc_no | VARCHAR(255) | Account number of the beneficiary. |
| amount | DECIMAL(18, 2) | Amount of the payment. |
| reference_no | VARCHAR(100) | Reference number of the payment. |
| status | VARCHAR(50) | Status of the payment (success or failed). |
| reason_code | VARCHAR(100) | Reason code for a failed payment (e.g. insufficient funds). |
| created_at | TIMESTAMP | Timestamp of when the payment was made. |
</p>

### Views
<p align="justify">
The schema includes two views that provide simplified access to certain data:
</p>

- `v_transaction_history`: Joins the `transaction_history` and `accounts` tables to show each transaction with its corresponding account and user information.
- `v_payments`: Joins the `payments` and `accounts` tables to show each payment with its corresponding account and user information.

<p align="justify">
This database schema is designed to efficiently and accurately store banking-related data, while providing easy access to important information through well-defined relationships and key fields.
</p>


## Technical Support

For technical support, please email me at [ornob011@gmail.com](mailto:ornob011@gmail.com). I will assist you promptly.

## Contributing

I welcome contributions to this project! If you have an idea for a feature or improvement, or if you have found a bug, please feel free to open an issue in the [issue tracker](https://github.com/ornob011/E-Learning-Platform/issues).

Before submitting a pull request, please make sure to:

- Read and follow the [contribution guidelines](CONTRIBUTING.md).
- Test your changes thoroughly.

Thank you for your contribution!

## License

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)    


This software is licensed under the GNU General Public License (GPL) version 3.

The full text of the GPL can be found in the `LICENSE` file, or online at <https://www.gnu.org/licenses/gpl-3.0.en.html>


