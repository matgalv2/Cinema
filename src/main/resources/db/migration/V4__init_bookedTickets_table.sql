DROP TABLE IF EXISTS booked_tickets;

CREATE TABLE booked_tickets(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    seat_number INT NOT NULL,
    code VARCHAR(32),
    assignment_id INT NOT NULL
);


ALTER TABLE booked_tickets ADD FOREIGN KEY (assignment_id) REFERENCES assignments(id);