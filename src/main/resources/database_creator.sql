CREATE TABLE IF NOT EXISTS Persons(

                                         first_name VARCHAR(100) NOT NULL,
                                         last_name VARCHAR(100) NOT NULL,
                                         email VARCHAR(150) UNIQUE NOT NULL,
                                         birth_date DATE,
                                         phone VARCHAR(20),
                                         address VARCHAR(255),
                                         user_name VARCHAR(100) UNIQUE NOT NULL,
                                         password VARCHAR(255) NOT NULL


);


CREATE TABLE IF NOT EXISTS Hotels(
                                        id INTEGER PRIMARY KEY ,
                                        name VARCHAR(100) NOT NULL,
                                        branch VARCHAR(100) NOT NULL
);



CREATE TABLE IF NOT EXISTS Branches(
                                        branch_id INTEGER NOT NULL REFERENCES Hotels(id) ON DELETE CASCADE,
                                        address VARCHAR(300) NOT NULL,
                                        room_list BIGINT ,
                                        employee_list BIGINT
);

CREATE TYPE room_status AS ENUM ('AVAILABLE', 'RESERVED', 'MAINTENANCE');
CREATE TABLE IF NOT EXISTS Rooms(
                                    room_id SERIAL PRIMARY KEY,
                                    type VARCHAR(100) NOT NULL,
                                    status room_status DEFAULT 'AVAILABLE',
                                    price_per_night INTEGER NOT NULL,
                                    capacity INTEGER NOT NULL
);

CREATE TYPE payment_status AS ENUM ('PAYMENT', 'PAID', 'CANCELED');
CREATE TYPE payment_paymentType AS ENUM ('CASH', 'CARD', 'ONLINE');
CREATE TABLE IF NOT EXISTS Payments(
                                       receipt_id SERIAL PRIMARY KEY,
                                       amount NUMERIC NOT NULL,
                                       payment_date DATE,
                                       status payment_status,
                                       payment_type payment_paymentType
);

CREATE TYPE reserve_status AS ENUM ('CONFIRMED', 'CANCELED', 'UPDATED','PENDING');
CREATE TABLE IF NOT EXISTS Reserves(
                                       reserve_id SERIAL PRIMARY KEY,
                                       check_in DATE,
                                       check_out DATE,
                                       number_of_guests INTEGER,
                                       status reserve_status DEFAULT 'PENDING',
                                       payment_id INT REFERENCES Payments(receipt_id)   /* برای اتصال مستقیم به جدول payments */
);

