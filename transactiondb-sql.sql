drop database transactiondb;
Create database transactiondb;
use transactiondb;

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    amount DECIMAL(10, 2) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING'
);