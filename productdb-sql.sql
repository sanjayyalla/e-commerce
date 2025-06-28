drop database productdb;
CREATE DATABASE productdb;
USE productdb;

CREATE TABLE product_categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO product_categories (category_name) VALUES 
('Electronics'), 
('Clothing'), 
('Furniture');

CREATE TABLE product_tags (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tag_name VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO product_tags (tag_name) VALUES 
('New Arrival'), 
('Discount'), 
('Limited Edition');

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE product_category_rel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES product_categories(id) ON DELETE CASCADE
);

CREATE TABLE product_tag_rel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    tag_id INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES product_tags(id) ON DELETE CASCADE
);


CREATE TABLE product_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL UNIQUE,
    weight DECIMAL(10, 2),
    dimensions VARCHAR(100),
    color VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
