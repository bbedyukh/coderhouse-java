CREATE TABLE product (
	id INT AUTO_INCREMENT NOT NULL,
	deleted_at DATETIME NOT NULL,
	description VARCHAR(255) NOT NULL,
	price FLOAT NOT NULL,
	sku VARCHAR(50) NOT NULL,
	stock INT NOT NULL,
	title VARCHAR(255),
	PRIMARY KEY (id)
);
