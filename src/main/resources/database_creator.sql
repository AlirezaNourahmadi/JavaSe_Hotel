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