-- Create table for Company Registration
CREATE TABLE IF NOT EXISTS Company (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         cnpj VARCHAR(18) NOT NULL,
                         address TEXT NOT NULL,
                         phone VARCHAR(15) NOT NULL UNIQUE,
                         motorcycle_spots INT NOT NULL,
                         car_spots INT NOT NULL
);

-- Create table for Vehicle Registration
CREATE TABLE IF NOT EXISTS Vehicle (
                         id SERIAL PRIMARY KEY,
                         brand VARCHAR(255) NOT NULL,
                         model VARCHAR(255) NOT NULL,
                         color VARCHAR(50) NOT NULL,
                         license_plate VARCHAR(10) NOT NULL UNIQUE,
                         type VARCHAR(50) NOT NULL
);
