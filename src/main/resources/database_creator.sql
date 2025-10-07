CREATE TABLE IF NOT EXISTS Persons(
                                         id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                         first_name VARCHAR(100) NOT NULL,
                                         last_name VARCHAR(100) NOT NULL,
                                         email VARCHAR(150) UNIQUE NOT NULL,
                                         birth_date DATE,
                                         phone VARCHAR(20),
                                         address VARCHAR(255),
                                         user_name VARCHAR(100) UNIQUE NOT NULL,
                                         password VARCHAR(255) NOT NULL


);

CREATE TABLE IF NOT EXISTS Guests (
                                      guest_id INTEGER PRIMARY KEY REFERENCES Persons(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Hotels(
                                        id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY ,
                                        name VARCHAR(100) NOT NULL,
                                        branch VARCHAR(100) NOT NULL
);



CREATE TABLE IF NOT EXISTS Branches(
                                        branch_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                        address VARCHAR(300) NOT NULL
);

CREATE TYPE room_status AS ENUM ('AVAILABLE', 'RESERVED', 'MAINTENANCE');
CREATE TABLE IF NOT EXISTS Rooms(
                                    room_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                    type VARCHAR(100) NOT NULL,
                                    status room_status DEFAULT 'AVAILABLE',
                                    price_per_night INTEGER NOT NULL,
                                    capacity INTEGER NOT NULL,
                                    branch_id INTEGER REFERENCES Branches(branch_id) ON DELETE CASCADE
);

CREATE TYPE payment_status AS ENUM ('PAYMENT', 'PAID', 'CANCELED');
CREATE TYPE payment_paymentType AS ENUM ('CASH', 'CARD', 'ONLINE');
CREATE TABLE IF NOT EXISTS Payments(
                                       receipt_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                       amount NUMERIC NOT NULL,
                                       payment_date DATE,
                                       status payment_status,
                                       payment_type payment_paymentType
);

CREATE TYPE reserve_status AS ENUM ('CONFIRMED', 'CANCELED', 'UPDATED','PENDING');
CREATE TABLE IF NOT EXISTS Reserves(
                                       guest_id INTEGER REFERENCES Persons(id),
                                       reserve_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                       check_in DATE,
                                       check_out DATE,
                                       number_of_guests INTEGER,
                                       status reserve_status DEFAULT 'PENDING',
                                       payment_id INTEGER REFERENCES Payments(receipt_id)   /* برای اتصال مستقیم به جدول payments */

);

CREATE TABLE IF NOT EXISTS Employees(
                                        employee_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                        first_name VARCHAR(100) NOT NULL,
                                        last_name VARCHAR(100) NOT NULL,
                                        phone VARCHAR(20),
                                        email VARCHAR(150) UNIQUE,
                                        branch_id INTEGER REFERENCES Branches(branch_id) ON DELETE CASCADE
);


CREATE TYPE task_status AS ENUM ('PENDING', 'IN_PROGRESS', 'DONE');
CREATE TABLE IF NOT EXISTS Tasks(
                                       task_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                       description VARCHAR(500) NOT NULL,
                                       due_date DATE,
                                       assigned_employee VARCHAR NOT NULL REFERENCES Employees(employee_id),
                                       status task_status DEFAULT 'PENDING'

);

CREATE TABLE IF NOT EXISTS Properties(
                                       name VARCHAR(50) NOT NULL,
                                       quantity INTEGER NOT NULL
);


CREATE TABLE IF NOT EXISTS Assignproperties(
                                               property_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                                               quantity INTEGER NOT NULL,
                                               room INTEGER NOT NULL REFERENCES Rooms(room_id),
                                               assigned_date DATE NOT NULL,
                                               assigned_by INTEGER NOT NULL REFERENCES Employees(employee_id)
);
