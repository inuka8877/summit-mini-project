-- Drop the table and sequence if they exist
DROP TABLE IF EXISTS product;
DROP SEQUENCE IF EXISTS product_id_sequence;

-- Create the sequence
CREATE SEQUENCE product_id_sequence START 1000;

-- Create the table with additional columns
CREATE TABLE product (
    product_id VARCHAR(255) DEFAULT nextval('product_id_sequence') PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    price NUMERIC(7,2) NOT NULL,
    supplier_id TEXT NOT NULL,
    supplier_name VARCHAR(255) NOT NULL,
    image VARCHAR(255),
    is_approved BOOLEAN DEFAULT false,
    is_active BOOLEAN DEFAULT true,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert data into the table
INSERT INTO product (product_name, description, price, supplier_id, supplier_name, image, is_approved, is_active)
VALUES
    ('Product A', 'Description for Product A', 40.80, '4', 'ABC Supplier', 'https://example.com/imageA.jpg', true, true),
    ('Product B', 'Description for Product B', 30.50, '5', 'XYZ Supplier', 'https://example.com/imageB.jpg', false, true),
    ('Product C', 'Description for Product C', 25.50, '6', 'LMN Supplier', 'https://example.com/imageC.jpg', true, false);