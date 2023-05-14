# BankingTransaction

![GitHub license](https://img.shields.io/github/license/ornob011/BankingTransaction)
![GitHub issues](https://img.shields.io/github/issues/ornob011/BankingTransaction)
![GitHub forks](https://img.shields.io/github/forks/ornob011/BankingTransaction)
![GitHub stars](https://img.shields.io/github/stars/ornob011/BankingTransaction)

BankingTransaction is a comprehensive banking transaction management system that allows users to perform various banking operations such as account creation, balance inquiry, deposit, withdrawal, and fund transfer. The project is designed to be scalable, secure, and easy to use.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
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
4. Run the SQL server and create a database named **banking_app**. Then, import the [**banking_app.sql**](src/main/resources/db_script/banking_app.sql) file from the project directory to the database.

5. Run the application:
```bash
mvn spring-boot:run
```

6. The application will be available at **http://localhost:8070**

## Usage
After running the application, you can access the BankingTransaction system through the provided API endpoints. You can use tools like Postman to interact with the API.

