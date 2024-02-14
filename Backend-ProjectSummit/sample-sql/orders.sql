drop table if exists orders;
drop table if exists product_order;

CREATE TABLE product_order(
    order_id SERIAL PRIMARY KEY,
    consumer_id INT,
    consumer_name VARCHAR(255),
    supplier_id INT,
    supplier_name VARCHAR(255),
    product_id INT,
    product_name VARCHAR(255),
    delivery_address VARCHAR(255),
    quantity INT,
    total_price DECIMAL(10, 2), -- Assuming total_price is a decimal value, adjust precision and scale as needed
	is_completed BOOLEAN DEFAULT false,
    is_active BOOLEAN DEFAULT true,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO product_order (consumer_id, consumer_name, supplier_id, supplier_name, product_id, product_name, delivery_address, quantity, total_price)
VALUES
    (1, 'John Doe', 4, 'ABC Supplier', 1, 'Product A', '123 Main St, Cityville', 5, 50.00),
    (2, 'Jane Smith', 5, 'XYZ Supplier', 2, 'Product B', '456 Oak St, Townsville', 10, 120.00),
    (3, 'Bob Johnson', 6, 'LMN Supplier', 3, 'Product C', '789 Maple St, Villagetown', 8, 80.00);
