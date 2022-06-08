DROP TABLE IF EXISTS assignments;

CREATE TABLE assignments(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    movie_id INT NOT NULL,
    auditorium_id INT NOT NULL,
    starts_at TIME NOT NULL
);

ALTER TABLE assignments ADD UNIQUE (movie_id, starts_at);
ALTER TABLE assignments ADD UNIQUE (auditorium_id, starts_at);

ALTER TABLE assignments ADD FOREIGN KEY (movie_id) REFERENCES movies(id);
ALTER TABLE assignments ADD FOREIGN KEY (auditorium_id) REFERENCES auditoriums(id);