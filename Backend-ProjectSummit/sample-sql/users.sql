-- Create ENUM type for user type
CREATE TYPE user_type AS ENUM ('Supplier', 'Steward', 'Consumer');

-- Create User table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE,
    name VARCHAR(255),
    type user_type
);

INSERT INTO users (email, name, type)
VALUES
    ('john@example.com', 'John Doe', 'Consumer'),
    ('jane@example.com', 'Jane Smith', 'Consumer'),
    ('bob@example.com', 'Bob Johnson', 'Consumer'),
    ('abc@example.com', 'ABC Supplier', 'Supplier'),
    ('xyz@example.com', 'XYZ Supplier', 'Supplier'),
    ('lmn@example.com', 'LMN Supplier', 'Supplier'),
    ('sysco@example.com', 'Example Employee', 'Steward');
    