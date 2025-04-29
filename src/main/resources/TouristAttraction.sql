CREATE TABLE TouristAttraction (
                                   attraction_id INT PRIMARY KEY,
                                   name VARCHAR(255) NOT NULL,
                                   description TEXT,
                                   town VARCHAR(100),
                                   image VARCHAR(255)
);
CREATE TABLE AttractionTags (
                                tag_id INT PRIMARY KEY,
                                attraction_id INT NOT NULL,
                                tag VARCHAR(100) NOT NULL,
                                FOREIGN KEY (attraction_id) REFERENCES TouristAttraction(attraction_id)
);
