create database promotiondb;
use promotiondb;
CREATE TABLE coupons (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    discount_percentage DECIMAL(5,2) NOT NULL,
    expiration_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO coupons (code, discount_percentage, expiration_date) VALUES
('WELCOME10', 10.00, '2025-12-31'),
('SUMMER15', 15.00, '2025-08-30'),
('FREESHIP5', 5.00, '2025-06-10');
