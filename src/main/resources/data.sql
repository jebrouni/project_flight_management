-- Ensure the USERS table exists
CREATE TABLE IF NOT EXISTS USERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Insert sample users with properly encoded passwords
-- Using BCrypt hash for the password "password"
INSERT INTO USERS (username, password, role) VALUES
    ('admin', '$2y$10$sAaJg6U.lwnIYeIPY7zzWO1p0PgujNP6uCyFoEXykSRNkGlw9tZTO', 'ADMINISTRATOR'),
    ('user',  '$2y$10$sAaJg6U.lwnIYeIPY7zzWO1p0PgujNP6uCyFoEXykSRNkGlw9tZTO', 'USER');
 





-- Create Flight table if it doesn't exist
CREATE TABLE IF NOT EXISTS FLIGHT (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    carrier_code VARCHAR(2),
    flight_number VARCHAR(4),
    flight_date DATE,
    origin VARCHAR(3),
    destination VARCHAR(3)
);

-- Insert sample flights
INSERT INTO FLIGHT (carrier_code, flight_number, flight_date, origin, destination) VALUES
    ('AL', '2025', '2025-11-11', 'ESP', 'PLM'),
    ('BA', '2026', '2025-11-12', 'BEL', 'LAM'),
    ('AF', '2027', '2025-11-13', 'CAN', 'DXC'),
    ('EK', '2028', '2025-11-14', 'USL', 'SWD');
